package com.mygdx.magicstorm.Rewards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.magicstorm.Cards.Attack;
import com.mygdx.magicstorm.Cards.Card;
import com.mygdx.magicstorm.Cards.Deck;
import com.mygdx.magicstorm.Cards.Defence;
import com.mygdx.magicstorm.hero.Hero;

import java.util.ArrayList;

public class DefenceReward extends Reward {

    private Sprite sprite = new Sprite(new Texture(Gdx.files.internal("Defend.png")));

    public DefenceReward() {
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
    }
    public void rewardEffect(Hero hero, Group group) {
        ArrayList<Card> cards = hero.getDeck().getCards();
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i) instanceof Defence) {
                cards.get(i).increaseDefence(5);
            }
        }
        Card defence = group.findActor("defence");
        defence.increaseDefence(5);
    }

    public void draw(Batch batch, float parentAlpha) {
        sprite.setColor(getColor());
        sprite.draw(batch, parentAlpha);
    }

    public void positionChanged() {
        sprite.setPosition(getX(),getY());
        super.positionChanged();
    }
}