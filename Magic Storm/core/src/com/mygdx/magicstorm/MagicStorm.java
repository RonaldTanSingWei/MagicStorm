package com.mygdx.magicstorm;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.magicstorm.Screens.MainMenuScreen;


public class MagicStorm extends Game {

    public SpriteBatch batch;
    public BitmapFont font;
    private MainMenuScreen mainMenuScreen;

    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(); // use libGDX's default Arial font
        mainMenuScreen = new MainMenuScreen(this);
        setScreen(mainMenuScreen);
    }

    public void render() {
        super.render(); // important!
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

}