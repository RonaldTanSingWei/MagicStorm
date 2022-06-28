package com.mygdx.magicstorm.UltimateSkills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.magicstorm.Enemies.Enemy;
import com.mygdx.magicstorm.hero.Hero;

public class UltimateArmorGain  extends UltimateSkill{
    private int progress = 10;

    private Sprite sprite = new Sprite(new Texture(Gdx.files.internal("ultArmorGain.png")));

    private int maxCounter = 50;

    private boolean ultimateCondition;

    private String progressString = progress + "/" + maxCounter;

    private String ultimateDescription;

    public UltimateArmorGain() {
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
    }
    public void positionChanged() {
        sprite.setPosition(getX(),getY());
        super.positionChanged();
    }

    public void draw(Batch batch, float parentAlpha) {
        sprite.setColor(getColor());
        sprite.draw(batch, parentAlpha);
    }

    @Override
    public void effect(Hero hero, Enemy enemy) {
        hero.gainArmor(50);
    };
}

