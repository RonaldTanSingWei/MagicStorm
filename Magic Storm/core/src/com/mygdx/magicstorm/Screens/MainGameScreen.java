package com.mygdx.magicstorm.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.magicstorm.Enemies.Goblin;
import com.mygdx.magicstorm.Enemies.MyActor;
import com.mygdx.magicstorm.MagicStorm;

import java.util.ArrayList;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class MainGameScreen implements Screen {
    final MagicStorm game;
    Stage stage;
    private ArrayList<Actor> cards = new ArrayList<Actor>();
    boolean touched = false;
    private Vector2 touchPos;
    MainPauseScreen pauseScreen;
    SpriteBatch batch = new SpriteBatch();
    boolean cardSelected = false;
    private ScreenViewport viewport;

    private Group group = new Group();

    private Goblin goblin;
    Actor card1;
    Actor card2;
    Actor card3;
    Group group2;
    Actor selectedCard;
    int cardNo = 3;
    int handSize = cardNo * 120;
    int spaceAtEachSide = (800 - handSize) / 2;
    int selectedCardX;
    int selectedCardY;

    public enum State {
        PAUSE,
        RUN,
        RESUME
    }
    private State state = State.RUN;

    public MainGameScreen(MagicStorm game){
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Group group1 = new Group();
        group2 = new Group();

        MyActor monster = new MyActor();
        card1 = new Image(new Texture(Gdx.files.internal("attack.png")));
        Image player = new Image(new Texture(Gdx.files.internal("Player.png")));
        card2 = new Image(new Texture(Gdx.files.internal("defend.png")));
        card3 = new Image(new Texture(Gdx.files.internal("defend.png")));
        Image background = new Image(new Texture(Gdx.files.internal("background.jpg")));
        goblin = new Goblin(20,20, 1200, 800);
background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        player.setName("player");
        card1.setName("card1");
        card2.setName("card2");
        card3.setName("card2");
        background.setName("background");
        monster.setName("monster");
        // order actors are drawn in
        group1.addActor(background);
        group1.addActor(player);
        group1.addActor(card1);
        group1.addActor(card2);
        group1.addActor(card3);
        group1.addActor(monster);
        group1.addActor(goblin);


        stage.addActor(group1);
        player.setPosition(0,110);
        card1.setPosition(220,0);
        card2.setPosition(340,0);
        card3.setPosition(460,0);
        monster.setPosition(600,110);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        Gdx.input.setInputProcessor(this.game);

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        pauseScreen = new MainPauseScreen(this.game, this);

        switch (state) {
            case RUN:
                Group group = (Group) stage.getActors().first();
                MyActor monster = (MyActor) group.findActor("monster");
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                stage.act(Gdx.graphics.getDeltaTime());
                stage.draw();

                //mouse click
                if (Gdx.input.isTouched()) {
                    Vector2 coord = stage.screenToStageCoordinates(new Vector2((float) Gdx.input.getX(), (float) Gdx.input.getY()));
                    final Actor hitActor = stage.hit(coord.x, coord.y, true);
                    if (hitActor != null) {
                        if ((hitActor.getName().equals("card1") || hitActor.getName().equals("card2")) && !cardSelected) {// set touchable in class initialisation instead
                            cardSelected = true;
                            selectedCard = hitActor;
                            selectedCardX = (int) selectedCard.getX();
                            selectedCardY = (int) selectedCard.getY();
                            RunnableAction ra = new RunnableAction();
                            ra.setRunnable(new Runnable() {
                                @Override
                                public void run() {
                                    hitActor.setPosition(310, 150);
                                    hitActor.setScale(1.5f);
                                }
                            });
                            hitActor.addAction(ra);

                        }
                        else if ((hitActor.getName().equals("monster")  && cardSelected)) {
                            cardSelected = false;
                            selectedCard.remove();
                            monster.takeDamage(5);
                            cardNo -= 1;
                            cards.remove(selectedCard);
                            handSize = cardNo * 120;
                            spaceAtEachSide = (800 - handSize) / 2;
                            for (int i = 0; i < cards.size(); i ++) {
                                final Actor tempActor = cards.get(i);
                                tempActor.setPosition(spaceAtEachSide,0);
                                spaceAtEachSide += 120;
                            }


                        }
                    }
                }

                if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                    selectedCard.setPosition(selectedCardX,selectedCardY);
                    selectedCard.setScale(1f);
                    cardSelected = false;
                }
            case PAUSE:
                break;
            case RESUME:
                Gdx.graphics.setContinuousRendering(true);

                this.state = State.RUN;
                break;
            default:
                this.state = State.RUN;
                break;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            resume();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
            pause();
        }

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        this.state = State.PAUSE;
        game.setScreen(pauseScreen);
    }

    @Override
    public void resume() {
        this.state = State.RUN;
        game.setScreen(game.getScreen());
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
