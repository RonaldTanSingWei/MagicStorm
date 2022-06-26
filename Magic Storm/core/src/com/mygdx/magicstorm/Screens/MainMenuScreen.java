package com.mygdx.magicstorm.Screens;


import com.mygdx.magicstorm.Screens.MainGameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.magicstorm.MagicStorm;
import com.mygdx.magicstorm.hero.Hero;

public class MainMenuScreen implements Screen {

    final MagicStorm game;

    private OrthographicCamera camera;

    private Stage stage;
    private Skin skin;
    private Table table;
    private TextButton startButton;

    private SpriteBatch batch;
    private Sprite sprite;


    public MainMenuScreen(final MagicStorm game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1600, 900);

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage(new ScreenViewport());

        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0,Gdx.graphics.getHeight()); // table starts from top of screen

        startButton = new TextButton("Start Game", skin);
        //set transform allows button size to be modified
        startButton.setTransform(true);
        startButton.setScale(2f);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new UltimateSelectScreen(game));
                dispose();
            }
        });
        table.padTop(730);
        table.padRight(100);
        table.add(startButton);

        stage.addActor(table);

        batch = new SpriteBatch();
        sprite = new Sprite(new Texture(Gdx.files.internal("menu.png")));
        sprite.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()); // sets to screen dimensions

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        batch.begin();
        sprite.draw(batch);
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

            /*game.batch.begin();
            game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
            game.batch.end();

            if (Gdx.input.isTouched()) {
                game.setScreen(new MainGameScreen(game));
                dispose();
            }*/
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

