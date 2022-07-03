package com.mygdx.magicstorm.UltimateSkills;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.magicstorm.Enemies.Enemy;
import com.mygdx.magicstorm.hero.Hero;

public class UltimateSkill extends Actor {

    private int progress;


    private int maxCounter;

    private boolean ultimateCondition;

    private String progressString = progress + "/" + maxCounter;

    private String ultimateDescription;

    public void setProgressString(String string) {
        this.progressString = string;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public void setMaxCounter(int maxCounter) {
        this.maxCounter = maxCounter;
    }

    public int getProgress() {
        return this.progress;
    }

    public int getMaxCounter() {
        return this.maxCounter;
    }

    public void increaseProgress(int progress) {
        this.progress += progress;
    }

    public boolean isReady() {
        return progress >= maxCounter;
    }

    public void effect(Hero hero, Enemy enemy) {};

    public String getProgressString() {
        return this.progressString;
    }

    public void enemyEffect(Enemy enemy) {

    }

    public void heroEffect(Hero hero){}
}
