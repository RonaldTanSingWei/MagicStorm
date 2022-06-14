package com.mygdx.magicstorm.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.magicstorm.MagicStorm;

public class VictoryScreen implements Screen {
    final MagicStorm game;

    OrthographicCamera camera;
    MainGameScreen gameScreen;

    public VictoryScreen(final MagicStorm game, final MainGameScreen gameScreen) {
        this.game = game;
        this.gameScreen = gameScreen;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1600, 900);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Victory comes and victory goes...", 400,450 );
        game.batch.end();
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
