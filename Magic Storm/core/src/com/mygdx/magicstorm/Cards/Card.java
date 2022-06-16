package com.mygdx.magicstorm.Cards;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.magicstorm.Enemies.Enemy;
import com.mygdx.magicstorm.hero.Hero;

public class Card extends Actor {
    int attack;
    int defence;

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

    public void addDefence(Hero hero) {
        hero.gainArmor(defence);
    }

    public void changeAttack(int value) {
        attack += value;
    }

    public void changeDefence(int value) {
        defence += value;
    }
}
