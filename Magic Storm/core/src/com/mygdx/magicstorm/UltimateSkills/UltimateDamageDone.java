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

    private int maxCounter;
    private int ultValue;



    private String progressString = progress + "/" + maxCounter;

    private String ultimateDescription = "Effect: Deal " + ultValue + " damage to all enemies.";

    private String ultimateCondition = "Condition: Deal " + maxCounter + " damage.";

    public UltimateDamageDone(int maxCounter, int ultValue) {
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
        this.maxCounter = maxCounter;
        this.ultValue = ultValue;
        ultimateCondition = "Condition: Deal " + maxCounter + " damage.";
        ultimateDescription = "Effect: Deal " + ultValue + " damage to all enemies.";

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
        enemy.takeDamage(ultValue);
    };
    public String getUltimateDescription() {
        return this.ultimateDescription;
    }

    public String getUltimateCondition() {
        return this.ultimateCondition;
    }
}
