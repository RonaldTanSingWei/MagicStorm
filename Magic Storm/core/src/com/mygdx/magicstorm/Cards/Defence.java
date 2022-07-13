package com.mygdx.magicstorm.Cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.magicstorm.hero.Hero;

public class Defence extends Card {
    private Sprite sprite = new Sprite(new Texture(Gdx.files.internal("Defend.png")));
    private int manaCost = 1;
    private int originalValue;

    public Defence(int value) {
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
        setName("defence");
        setDefence(value);
        this.originalValue = value;
    }

    public void increaseDefence(int value) {
        setDefence(getDefence() + value);
    }

    public void addDefence(Hero hero, Defence defence, Attack attack) {
        hero.gainArmor(getDefence());
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

    public String getDescription() {return "Add " + getDefence() + " armor";}

    public void resetCard() {
        setDefence(originalValue);
    }

    public void increaseOriginalValue(int value) {
        this.originalValue += value;
    }

}
