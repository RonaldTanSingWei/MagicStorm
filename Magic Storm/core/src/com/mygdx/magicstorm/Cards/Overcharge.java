package com.mygdx.magicstorm.Cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.magicstorm.hero.Hero;

public class Overcharge extends Defence {
    private Sprite sprite = new Sprite(new Texture(Gdx.files.internal("Overcharge.png")));
    private int manaCost = 0;

    public Overcharge(int value) {
        super(value);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
        setName("Overcharge");
        setDefence(value);
    }

    public void increaseDefence(int value) {
        setDefence(getDefence() + value);
    }

    public void addDefence(Hero hero) {
        hero.takeDamage(getDefence());
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

    public String getDescription() {return "Draw 3 cards. Gain 3 mana. Take " + getDefence() + "damage.";}

}
