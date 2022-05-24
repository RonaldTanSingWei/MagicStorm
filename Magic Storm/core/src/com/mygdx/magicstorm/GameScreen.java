package com.mygdx.magicstorm;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.magicstorm.MagicStorm;

import java.util.Iterator;

public class GameScreen implements Screen {
    final MagicStorm game;

    Texture dropImage;
    Texture bucketImage;
    Sound dropSound;
    Music rainMusic;
    OrthographicCamera camera;
    Rectangle bucket;
    Array<Rectangle> raindrops;
    long lastDropTime;
    int dropsGathered;
    PauseScreen pauseScreen;




    public enum State {
        PAUSE,
        RUN,
        RESUME
    }
    private State state = State.RUN;

    private void spawnRaindrop() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800 - 64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }

    public GameScreen(final MagicStorm game) {
        this.game = game;

        // load the images for the droplet and the bucket, 64x64 pixels each
        dropImage = new Texture(Gdx.files.internal("droplet.png"));
        bucketImage = new Texture(Gdx.files.internal("bucket.png"));

        // load the drop sound effect and the rain background "music"
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        rainMusic.setLooping(true);
        rainMusic.play();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        bucket = new Rectangle();
        bucket.x = 800 / 2 - 64 / 2;
        bucket.y = 20;
        bucket.width = 64;
        bucket.height = 64;

        raindrops = new Array<Rectangle>();
        spawnRaindrop();

    }

    @Override
    public void render(float delta) {
        pauseScreen = new PauseScreen(this.game, this);
        switch (state) {
            case RUN:
                ScreenUtils.clear(0, 0.5f, 0.2f, 1);
                camera.update();
                game.batch.setProjectionMatrix(camera.combined);
                game.batch.begin();
                game.batch.draw(bucketImage, bucket.x, bucket.y);
                for (Rectangle raindrop : raindrops) {
                    game.batch.draw(dropImage, raindrop.x, raindrop.y);

                }
                game.batch.end();

                if (Gdx.input.isTouched()) {
                    Vector3 touchPos = new Vector3();
                    touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                    camera.unproject(touchPos);
                    bucket.x = touchPos.x - 64 / 2;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= 1000 * Gdx.graphics.getDeltaTime();
                if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += 1000 * Gdx.graphics.getDeltaTime();

                if (bucket.x < 0) bucket.x = 0;
                if (bucket.x > 800 - 64) bucket.x = 800 - 64;

                if (TimeUtils.nanoTime() - lastDropTime > 500000000) spawnRaindrop();
                for (Iterator<Rectangle> iter = raindrops.iterator(); iter.hasNext(); ) {
                    Rectangle raindrop = iter.next();
                    raindrop.y -= 500 * Gdx.graphics.getDeltaTime();
                    if (raindrop.y + 64 < 0) iter.remove();
                    if (raindrop.overlaps(bucket)) {
                        dropSound.play();
                        iter.remove();
                    }
                }
                break;
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

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            pause();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            resume();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            pause();
        }

    }


    @Override
    public void show() {

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
        dropImage.dispose();
        bucketImage.dispose();
        dropSound.dispose();
        rainMusic.dispose();
        game.batch.dispose();
    }
}
