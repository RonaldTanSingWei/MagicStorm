package com.mygdx.magicstorm.Rewards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.magicstorm.Cards.Deck;
import com.mygdx.magicstorm.hero.Hero;

public class HpReward extends Reward {

    private Sprite sprite = new Sprite(new Texture(Gdx.files.internal("HP.png")));

    public HpReward() {
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
    }
    public void rewardEffect(Hero hero) {
        hero.gainHp(10);
        hero.setMaxHp(hero.getMaxHp() + 5);
    }

    public void draw(Batch batch, float parentAlpha) {
        sprite.setColor(getColor());
        sprite.draw(batch, parentAlpha);
    }

    public void positionChanged() {
        sprite.setPosition(getX(),getY());
        super.positionChanged();
    }

    public String getDescription() {
        return "Restore current HP by 10, increase max HP by 5";
    }
}
