package com.mygdx.magicstorm.Enemies;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.magicstorm.MagicStorm;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class Enemy extends Actor {
    private MagicStorm game;
    private int currentHp;
    private int maxHp;
    private Rectangle hpBar;
    private String currentHpString;

    @Override
    public void act(float delta) {
        super.act(delta);
    }


    public void draw(SpriteBatch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public void takeDamage(int damage) {
        currentHp -= damage;
    }

    public int getCurrentHp() {
        return this.currentHp;
    }

    public void setCurrentHp(int hp) {
        this.currentHp = hp;
    }

    public void setMaxHp(int hp) {
        this.maxHp = hp;
    }

    public String getCurrentHpString() {
        return this.currentHpString;
    }

    public void die() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                addAction(sequence(fadeOut(1f)));
            }
        }, 2);
    }

}
