package com.mygdx.magicstorm.Rewards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.magicstorm.Cards.Attack;
import com.mygdx.magicstorm.Cards.Card;
import com.mygdx.magicstorm.Cards.Deck;
import com.mygdx.magicstorm.hero.Hero;

import java.util.ArrayList;

public class UltimateReward2 extends UltimateReward {

    private Sprite sprite = new Sprite(new Texture(Gdx.files.internal("Attack.png")));

    public UltimateReward2() {
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
    }
    public void rewardEffect(Hero hero, Group group) {
        hero.getUltimateSkill().setMaxCounter(hero.getUltimateSkill().getMaxCounter() - 5);
    }

    public void draw(Batch batch, float parentAlpha) {
        sprite.setColor(getColor());
        sprite.draw(batch, parentAlpha);
    }

    public void positionChanged() {
        sprite.setPosition(getX(),getY());
        super.positionChanged();
    }

    protected void scaleChanged() {
        sprite.setScale(getScaleX(),getScaleY());
        super.scaleChanged();
    }

    public String getDescription() {
        return "Decrease cost of ultimate ability by 5";
    }
}
