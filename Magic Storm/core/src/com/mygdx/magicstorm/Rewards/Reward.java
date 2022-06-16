package com.mygdx.magicstorm.Rewards;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.magicstorm.Cards.Card;

public class Reward extends Actor {
    private Card card;
    private Sprite sprite;

    public Reward(Card card) {
        this.card = card;
    }

}
