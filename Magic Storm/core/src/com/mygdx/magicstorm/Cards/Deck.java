package com.mygdx.magicstorm.Cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;
import java.util.Random;

public class Deck extends Actor {
    private Sprite sprite = new Sprite(new Texture(Gdx.files.internal("Cardback.png")));
    private String currentDeckSizeString;
    private int maxDeckSize;
    private int currentDeckSize;
    private BitmapFont font = new BitmapFont();
    private SpriteBatch deckBatch = new SpriteBatch();
    private ArrayList<Card> cards;
    private Random rand = new Random();

    public Deck(int currentDeckSize, int maxDeckSize) {
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
        this.currentDeckSize = currentDeckSize;
        this.maxDeckSize = maxDeckSize;
        this.currentDeckSizeString = String.valueOf(currentDeckSize);
        cards = new ArrayList<Card>();
        //this only works for even deck size, figure out randomisation of makeup if deck size is odd
        for (int i = 0; i < maxDeckSize; i++) {
            Card tempactor;
            if (i % 2 == 0) tempactor = new Attack(5);
            else tempactor = new Defence(5);
            cards.add(tempactor);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setColor(getColor());
        sprite.draw(batch, parentAlpha);
        batch.end();
        deckBatch.begin();
        font.draw(deckBatch, currentDeckSizeString, sprite.getX(), sprite.getY());
        deckBatch.end();
        batch.begin();

    }

    public void positionChanged() {
        sprite.setPosition(getX(), getY());
        super.positionChanged();
    }

    public Card drawCard() {
        int random = rand.nextInt(currentDeckSize);
        Card cardDrawn = cards.remove(random);
        currentDeckSize -= 1;
        currentDeckSizeString = String.valueOf(currentDeckSize);
        return cardDrawn;
    }
    public int getSize() {
        return cards.size();
    }
}
