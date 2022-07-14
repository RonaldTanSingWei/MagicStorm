package com.mygdx.magicstorm.Cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.magicstorm.Enemies.Enemy;

import java.util.Random;

//Effect: Random (Atk, Atk + 10) x 5 dmg to enemies (works with multiple enemies)
public class WildLightning extends Attack {
    private Sprite sprite = new Sprite(new Texture(Gdx.files.internal("WildLightning.png")));
    private int manaCost = 3;
    private Random rand = new Random();
    private boolean aoe = true;

    public WildLightning(int value) {
        super(value);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setName("Wild Lightning");
    }

    public void increaseAttack(int value) {
        setAttack(getAttack() + value);
    }

    public void dealDamage(Enemy enemy) {
        int random = rand.nextInt(getAttack() + 10);
        enemy.takeDamage(random);
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
    public boolean isAoe() {
        return aoe;
    }

    public int getManaCost() {return manaCost;}

    public String getDescription() {return "Deal randomised 0 - " + (getAttack() + 10)+ " to random enemy target";}
}