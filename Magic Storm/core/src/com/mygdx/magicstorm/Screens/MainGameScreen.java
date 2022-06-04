package com.mygdx.magicstorm.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
import com.mygdx.magicstorm.Hero;
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
    private Hero player;
    Actor card1;
    Actor card2;
    Actor card3;
    Group group2;
    Actor selectedCard;
    int cardNo = 3;
    int handSize;
    int spaceAtEachSide;
    int cardWidth;
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

        Goblin goblin = new Goblin(10, 10);
        card1 = new Image(new Texture(Gdx.files.internal("attack.png")));
        Hero hero = new Hero();
        card2 = new Image(new Texture(Gdx.files.internal("defend.png")));
        card3 = new Image(new Texture(Gdx.files.internal("defend.png")));
        Image background = new Image(new Texture(Gdx.files.internal("background.jpg")));
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        hero.setName("hero");
        card1.setName("card1");
        card2.setName("card2");
        card3.setName("card2");
        background.setName("background");
        goblin.setName("goblin");
        // order actors are drawn in
        group1.addActor(background);
        group1.addActor(hero);
        group1.addActor(card1);
        group1.addActor(card2);
        group1.addActor(card3);
        group1.addActor(goblin);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        stage.addActor(group1);
        hero.setPosition(0,stage.getHeight() / 3);
        hero.setHpBarPos(0, (int) ((stage.getHeight() / 3) - 30));

        cardWidth = (int)card1.getWidth();
        handSize = (int) (cardNo * cardWidth);
        spaceAtEachSide = (int) ((stage.getWidth() - handSize) / 2);
        for (int i = 0; i < cards.size(); i ++) {
            final Actor tempActor = cards.get(i);
            tempActor.setPosition(spaceAtEachSide,0);
            spaceAtEachSide += 120;
        }
        goblin.setPosition(stage.getWidth() - goblin.getWidth(),stage.getHeight() / 3);
        goblin.setHpBarPos((int) (stage.getWidth() - goblin.getWidth()), (int) ((stage.getHeight() / 3) - 30));

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
                Hero hero = group.findActor("hero");
                Goblin goblin = group.findActor("goblin");
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
                        else if ((hitActor.getName().equals("goblin")  && cardSelected)) {
                            cardSelected = false;
                            selectedCard.remove();
                            goblin.takeDamage(5);
                            cardNo -= 1;
                            cards.remove(selectedCard);
                            handSize = (int) (cardNo * cardWidth);
                            spaceAtEachSide = (int) ((stage.getWidth() - handSize) / 2);
                            for (int i = 0; i < cards.size(); i ++) {
                                final Actor tempActor = cards.get(i);
                                tempActor.setPosition(spaceAtEachSide,0);
                                spaceAtEachSide += 120;
                            }


                        }
                        else if ((hitActor.getName().equals("hero")  && cardSelected)) {
                            cardSelected = false;
                            selectedCard.remove();
                            hero.gainArmor(5);
                            hero.gainHp(5);
                            cardNo -= 1;
                            cards.remove(selectedCard);
                            handSize = (int) (cardNo * cardWidth);
                            spaceAtEachSide = (int) ((stage.getWidth() - handSize) / 2);
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
