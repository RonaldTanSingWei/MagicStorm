package com.mygdx.magicstorm.Cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.magicstorm.hero.Hero;

//Buff defends
public class ConcentrateDefence extends Defence {
    private Sprite sprite = new Sprite(new Texture(Gdx.files.internal("ConcentrateDefence.png")));
    private int manaCost = 1;

    public ConcentrateDefence(int value) {
        super(value);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
        setName("Concentrate Defence");
        setDefence(value);
    }

    public void increaseDefence(int value) {
        setDefence(getDefence() + value);
    }

    public void addDefence(Hero hero, Defence defence, Attack attack) {
        defence.increaseDefence(getDefence());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setColor(getColor());
        sprite.draw(batch, parentAlpha);
    }

    @Override
    public void positionChanged() {
        sprite.setPosition(getX(),getY());
        super.positionChanged();
    }

    @Override
    protected void scaleChanged() {
        sprite.setScale(getScaleX(),getScaleY());
        super.scaleChanged();
    }

    public int getManaCost() {return manaCost;}

    public String getDescription() {return "Increase the armor granted by defences this combat by  " + getDefence() + ".";}
}
