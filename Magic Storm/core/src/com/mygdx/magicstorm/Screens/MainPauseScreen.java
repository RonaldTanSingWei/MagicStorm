package com.mygdx.magicstorm.Screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.magicstorm.MagicStorm;
import com.mygdx.magicstorm.hero.Hero;

public class MainPauseScreen implements Screen {
    final MagicStorm game;

    private Hero hero;

    private OrthographicCamera camera;
    private MainGameScreen gameScreen;
    private Stage stage;
    private Skin skin;

    private Table table;
    private TextButton resumeButton;


    private SpriteBatch batch;
    private Sprite sprite;


    public MainPauseScreen(final MagicStorm game, final MainGameScreen gameScreen) {
        this.game = game;
        this.gameScreen = gameScreen;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage(new ScreenViewport());

        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0,Gdx.graphics.getHeight()); // table starts from top of screen

        resumeButton = new TextButton("Press Enter to resume", skin);
        resumeButton.setTransform(true);
        resumeButton.setScale(2f);
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(gameScreen);
                dispose();
            }
        });
        table.padTop(400);
        table.padRight(175);
        table.add(resumeButton);

        stage.addActor(table);

        batch = new SpriteBatch();
        sprite = new Sprite(new Texture(Gdx.files.internal("Background.jpg")));
        sprite.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()); // sets to screen dimensions


        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        sprite.draw(batch);
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
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
        game.setScreen(gameScreen);
        dispose();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
