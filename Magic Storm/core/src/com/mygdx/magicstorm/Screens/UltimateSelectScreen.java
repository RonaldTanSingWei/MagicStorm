package com.mygdx.magicstorm.Screens;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
import com.mygdx.magicstorm.UltimateSkills.UltimateArmorGain;
import com.mygdx.magicstorm.UltimateSkills.UltimateDamageDone;
import com.mygdx.magicstorm.UltimateSkills.UltimateSkill;
import com.mygdx.magicstorm.hero.Hero;

public class UltimateSelectScreen implements Screen {

    final MagicStorm game;

    private OrthographicCamera camera;

    private Stage stage;
    private Skin skin;
    private Table table;
    private TextButton startButton;

    private SpriteBatch batch;
    private Sprite sprite;

    private Hero hero = new Hero();


    public UltimateSelectScreen(final MagicStorm game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1600, 900);

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage(new ScreenViewport());


        batch = new SpriteBatch();
        sprite = new Sprite(new Texture(Gdx.files.internal("menu.png")));
        sprite.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()); // sets to screen dimensions

        Gdx.input.setInputProcessor(stage);

        UltimateDamageDone ultimateSkillOne = new UltimateDamageDone();
        UltimateArmorGain ultimateSkillTwo = new UltimateArmorGain();
        stage.addActor(ultimateSkillOne);
        stage.addActor(ultimateSkillTwo);

        ultimateSkillOne.setPosition(stage.getWidth() * 1/5, stage.getHeight()* 2/5);
        ultimateSkillTwo.setPosition(stage.getWidth()*3/5, stage.getHeight()*2/5);
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

            if (Gdx.input.isTouched()) {
                Vector2 coord = stage.screenToStageCoordinates(new Vector2((float) Gdx.input.getX(), (float) Gdx.input.getY()));
                final Actor hitActor = stage.hit(coord.x, coord.y, true);
                    if (hitActor instanceof UltimateDamageDone) {
                        UltimateDamageDone chosenSkill = (UltimateDamageDone) hitActor;
                        chosenSkill.setMaxCounter(10);
                        hero.setUltimateSkill(chosenSkill);
                        hero.getUltimateSkill().setProgressString(hero.getUltimateSkill().getProgress() + "/" + hero.getUltimateSkill().getMaxCounter());
                        game.setScreen(new MainGameScreen(game, hero));
                    } else if (hitActor instanceof UltimateArmorGain) {
                        UltimateArmorGain chosenSkill = (UltimateArmorGain) hitActor;
                        chosenSkill.setMaxCounter(10);
                        hero.setUltimateSkill(chosenSkill);
                        hero.getUltimateSkill().setProgressString(hero.getUltimateSkill().getProgress() + "/" + hero.getUltimateSkill().getMaxCounter());
                        game.setScreen(new MainGameScreen(game, hero));
                    }
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