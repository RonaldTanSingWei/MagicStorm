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
import com.mygdx.magicstorm.MagicStorm;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class MainGameScreen implements Screen {
    final MagicStorm game;
    Stage stage;
    boolean touched = false;
    private Vector2 touchPos;
    MainPauseScreen pauseScreen;
    SpriteBatch batch = new SpriteBatch();
    private ScreenViewport viewport;

    private Group group = new Group();

    public enum State {
        PAUSE,
        RUN,
        RESUME
    }
    private State state = State.RUN;

    public MainGameScreen(MagicStorm game){
        this.game = game;
        viewport = new ScreenViewport();
        stage = new Stage(viewport);


        Image player = new Image(new Texture(Gdx.files.internal("Player.png")));
        Image card1 = new Image(new Texture(Gdx.files.internal("attack.png")));
        Image card2 = new Image(new Texture(Gdx.files.internal("defend.png")));
        Image monster = new Image(new Texture(Gdx.files.internal("monster 1.png")));
        Image background = new Image(new Texture(Gdx.files.internal("background.jpg")));

        Goblin goblin =  new Goblin(20,20, 1200,400);
        player.setName("player");
        card1.setName("card1");
        card2.setName("card2");
        monster.setName("monster");
        background.setName("background");
        goblin.setName("goblin");

        // order actors are drawn in
        group.addActor(background);
        group.addActor(player);
        group.addActor(card1);
        group.addActor(card2);
        group.addActor(monster);
        group.addActor(goblin);


        stage.addActor(group);
        player.setPosition(0,300);
        card1.setPosition(700,0);
        card2.setPosition(900,0);
        monster.setPosition(1200,300);
        goblin.setPosition(1200,300);
        background.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        pauseScreen = new MainPauseScreen(this.game, this);

        switch (state) {
            case RUN:
            group.act(delta);
            Image card1 = (Image) group.findActor("card1");
            Image card2 = (Image) group.findActor("card2");
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            stage.act();
            stage.draw();
                Goblin goblin = group.findActor("goblin");
                goblin.draw(batch, delta);


            //mouse click
            if (Gdx.input.isTouched()) {
                Vector2 coord = stage.screenToStageCoordinates(new Vector2((float) Gdx.input.getX(), (float) Gdx.input.getY()));
                final Actor hitActor = stage.hit(coord.x, coord.y, true);
                if (hitActor != null) {
                    if ((hitActor.getName().equals("card1") || hitActor.getName().equals("card2")) && !touched) {
                        touched = true;
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
                    if ((hitActor.getName().equals("goblin"))) {
                        goblin.takeDamage(1);
                    }
                }
            }
if (Gdx.input.isKeyPressed(Input.Keys.A)) {
    goblin.takeDamage(1);
    if (goblin.isDead()) {
        goblin.die();
        goblin.setVisible(false);
    }
}
            if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                card1.setPosition(280, 0);
                card2.setPosition(400, 0);
                card1.setScale(1f);
                card2.setScale(1f);
                touched = false;
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
