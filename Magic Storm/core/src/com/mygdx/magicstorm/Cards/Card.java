package com.mygdx.magicstorm.Cards;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.magicstorm.Enemies.Enemy;
import com.mygdx.magicstorm.hero.Hero;

public class Card extends Actor {
    private int attack;
    private int defence;
    private int manaCost;
    private int originalValue;

    private boolean aoe;

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public void dealDamage(Enemy enemy) {
        enemy.takeDamage(attack);
    }

    public void addDefence(Hero hero, Defence defence, Attack attack) {
        hero.gainArmor(this.defence);
    }

    public void increaseAttack(int value) {
        attack += value;
    }

    public void increaseDefence(int value) {
        defence += value;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getDefence() {
        return this.defence;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getManaCost() {return manaCost;}

    public String getDescription() {return "Default";}

    public boolean isAoe() {
        return aoe;
    }

    public void increaseOriginalValue(int value) {
        originalValue += value;
    }
}