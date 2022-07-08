package com.mygdx.magicstorm.UltimateSkills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.magicstorm.Enemies.Enemy;
import com.mygdx.magicstorm.hero.Hero;

public class UltimateArmorGain  extends UltimateSkill{
    private int progress = 10;

    private Sprite sprite = new Sprite(new Texture(Gdx.files.internal("ultArmorGain.png")));

    private int maxCounter;
    private int ultValue;


    private String progressString = progress + "/" + maxCounter;

    private String ultimateDescription = "Effect: Gain " + ultValue + " armor.";

    private String ultimateCondition = "Condition: Gain " + maxCounter + " armor.";


    public UltimateArmorGain(int maxCounter, int ultValue) {
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
        this.maxCounter = maxCounter;
        this.ultValue = ultValue;
        ultimateCondition = "Condition: Gain " + maxCounter + " armor.";
        ultimateDescription = "Effect: Gain " + ultValue + " armor.";
    }
    public void positionChanged() {
        sprite.setPosition(getX(),getY());
        super.positionChanged();
    }

    public void draw(Batch batch, float parentAlpha) {
        sprite.setColor(getColor());
        sprite.draw(batch, parentAlpha);
    }

    @Override
    public void heroEffect(Hero hero) {
        hero.gainArmor(ultValue);
    };

    public void enemyEffect(Enemy enemy){};

    public String getUltimateDescription() {
        return this.ultimateDescription;
    }

    public String getUltimateCondition() {
        return this.ultimateCondition;
    }
}

