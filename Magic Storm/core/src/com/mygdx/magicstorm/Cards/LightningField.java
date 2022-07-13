package com.mygdx.magicstorm.Cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.magicstorm.Enemies.Enemy;

//deal 10 dmg, reduce dmg dealt by 5
public class LightningField extends Attack {
    private Sprite sprite = new Sprite(new Texture(Gdx.files.internal("MementoMori.jpg")));
    private int manaCost = 2;

    private boolean aoe = true;

    public LightningField(int value) {
        super(value);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setName("Lightning Field");
    }

    public void increaseAttack(int value) {
        setAttack(getAttack() + value);
    }

    public void dealDamage(Enemy enemy) {
        enemy.takeDamage(getAttack());
        enemy.setAttackValue(enemy.getIntAttackValue() - getAttack()/2);
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

    public String getDescription() {return "Deal " + getAttack() + " damage to an enemy and reduce its attack damage by half the damage dealt";}
}

