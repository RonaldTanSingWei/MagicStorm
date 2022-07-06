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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Deck extends Actor {
    private String currentDeckSizeString;
    private int maxDeckSize;
    private int currentDeckSize;
    private BitmapFont font = new BitmapFont();
    private SpriteBatch deckBatch = new SpriteBatch();
    private ArrayList<Card> cards;
    private Random rand = new Random();

    public Deck(int currentDeckSize, int maxDeckSize) {
        this.currentDeckSize = currentDeckSize;
        this.maxDeckSize = maxDeckSize;
        this.currentDeckSizeString = String.valueOf(currentDeckSize);
        cards = new ArrayList<Card>();
        //this only works for even deck size, figure out randomisation of makeup if deck size is odd
        for (int i = 0; i < maxDeckSize; i++) {
            Card tempactor;
            if (i == 0) tempactor = new MementoMori(1);
            else if (i == 1) tempactor = new Elucidate();
            else if (i == 2) tempactor = new Ataxia();
            else if (i % 2 == 0) tempactor = new Attack(10);
            else tempactor = new Defence(10);
            cards.add(tempactor);
        }
    }


    public void draw(SpriteBatch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
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

   /* @Override
    public Object clone() {
        Deck copyDeck = null;
        try {
            copyDeck = (Deck) super.clone();
        } catch (CloneNotSupportedException e) {
            copyDeck = new Deck(
                    this.currentDeckSize, this.maxDeckSize
            );
        }
        ArrayList<Card> newList = new ArrayList<>(cards);
        copyDeck.cards = newList;
        return copyDeck;
    }*/

    public int getCurrentDeckSize() {
        return this.currentDeckSize;
    }
    public String getCurrentDeckSizeString() {
        return this.currentDeckSizeString;
    }
    public int getMaxDeckSize() {
        return this.maxDeckSize;
    }

    public void setCards(ArrayList<Card> newDeck) {
        this.cards = newDeck;
    }

    public ArrayList<Card> getCards() {
        ArrayList<Card> newCards = new ArrayList<>(cards);
        return newCards;
    }

    public void setCurrentDeckSizeString(String string) {
        this.currentDeckSizeString = string;
    }
}
