package com.mygdx.magicstorm.Screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.magicstorm.Enemies.Enemy;
import com.mygdx.magicstorm.Enemies.Goblin;
import com.mygdx.magicstorm.MagicStorm;

import java.util.ArrayList;
import java.util.Iterator;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class GameScreen implements Screen, GestureListener {
    private Stage stage;
    private MagicStorm game;
    private SpriteBatch batch = new SpriteBatch();
    private Goblin goblin;
    private Goblin goblin2;
    private BitmapFont font = new BitmapFont();
    private int check = 100;
    private ArrayList<Enemy> enemies = new ArrayList();
    private PauseScreen pauseScreen;

    public enum State {
        PAUSE,
        RUN,
        RESUME
    }
    private State state = State.RUN;

    public GameScreen(MagicStorm game) {
        stage = new Stage();
        this.game = game;
        goblin = new Goblin(20,20);
        goblin2 = new Goblin(30,30);
        goblin2.setBounds(600,500,150,150);
        stage.addActor(goblin);
        stage.addActor(goblin2);
        enemies.add(goblin);
        enemies.add(goblin2);
        pauseScreen = new PauseScreen(game, this);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new GestureDetector(this));
    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0.5f, 0.2f, 1);
        stage.act(delta);
        stage.draw();
        for (int i = 0; i < enemies.size(); i++) {
            Enemy TempEnemy = enemies.get(i);
            TempEnemy.draw(batch, delta);
            TempEnemy.act(delta);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            goblin.takeDamage(1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.B)) {
            goblin2.takeDamage(1);
        }
        for (int i = 0; i < enemies.size(); i++) {
            final Enemy TempEnemy = enemies.get(i);
            if (TempEnemy.getCurrentHp() <= 0) {
                enemies.remove(TempEnemy);
                i--;
            }
        }

            if (Gdx.input.isKeyPressed(Input.Keys.L)) {
                this.pause();

            }

        }



    @Override
    public void resize(int width, int height) {
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

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
