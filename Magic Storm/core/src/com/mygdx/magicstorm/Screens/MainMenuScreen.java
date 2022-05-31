package com.mygdx.magicstorm.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.magicstorm.MagicStorm;
import com.badlogic.gdx.scenes.scene2d.Stage;
public class MainMenuScreen implements Screen{

    final MagicStorm game;

    OrthographicCamera camera;

    public MainMenuScreen(final MagicStorm game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(2f,0,0,1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Magic Storm!!! ", 400, 150);
        game.font.draw(game.batch, "Tap anywhere to begin!", 400, 100);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            GameScreen gameScreen = new GameScreen(this.game);
            game.setScreen(gameScreen);

            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}