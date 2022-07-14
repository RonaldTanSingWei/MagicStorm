package com.mygdx.magicstorm.Cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.magicstorm.Enemies.Enemy;

import java.util.Random;

//deal 12 dmg
public class MagicBolt extends Attack {
    private Sprite sprite = new Sprite(new Texture(Gdx.files.internal("MagicBolt.png")));
    private int manaCost = 1;

    public MagicBolt(int value) {
        super(value);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setName("Magic Bolt");
    }

    public void increaseAttack(int value) {
        setAttack(getAttack() + value);
    }

    public void dealDamage(Enemy enemy) {
        enemy.takeDamage(getAttack());
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

    public String getDescription() {return "Deal " + getAttack() + " damage to an enemy";}
}
