package com.mygdx.magicstorm.Cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.magicstorm.Cards.Card;
import com.mygdx.magicstorm.Enemies.Enemy;

public class Attack extends Card {
    private Sprite sprite = new Sprite(new Texture(Gdx.files.internal("Attack.png")));
    private int manaCost = 1;
    public Attack(int value) {
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
        setName("attack");
        setAttack(value);
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

}
