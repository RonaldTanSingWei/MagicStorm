package com.mygdx.magicstorm.UltimateSkills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.magicstorm.Enemies.Enemy;
import com.mygdx.magicstorm.hero.Hero;

public class UltimateDamageDone extends UltimateSkill {
    private int progress = 5;

    private Sprite sprite = new Sprite(new Texture(Gdx.files.internal("ultDamageDone.png")));

    private int maxCounter = 30;

    private boolean ultimateCondition;

    private String progressString = progress + "/" + maxCounter;

    private String ultimateDescription;

    public UltimateDamageDone() {
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
    }

    public void draw(Batch batch, float parentAlpha) {
        sprite.setColor(getColor());
        sprite.draw(batch, parentAlpha);
    }

    public void positionChanged() {
        sprite.setPosition(getX(),getY());
        super.positionChanged();
    }
    public void heroEffect(Hero hero) { };
    @Override
    public void enemyEffect(Enemy enemy) {
        enemy.takeDamage(50);
    };
}
