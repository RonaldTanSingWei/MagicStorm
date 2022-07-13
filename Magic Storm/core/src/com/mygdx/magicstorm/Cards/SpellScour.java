package com.mygdx.magicstorm.Cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.magicstorm.hero.Hero;


//Effect: Draws 2 cards
public class SpellScour extends Defence {
    private Sprite sprite = new Sprite(new Texture(Gdx.files.internal("Elucidate.jpg")));
    private int manaCost = 1;
    public SpellScour(int value) {
        super(value);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
        setName("Spell Scour");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setColor(getColor());
        sprite.draw(batch, parentAlpha);
    }
    public void addDefence(Hero hero, Defence defence, Attack attack) {
        for (int i = 0; i < 2; i++) {
            hero.getDeck().drawCard();
        }
    }

    @Override
    public void positionChanged() {
        sprite.setPosition(getX(),getY());
        super.positionChanged();
    }

    @Override
    protected void scaleChanged() {
        sprite.setScale(getScaleX(),getScaleY());
        super.scaleChanged();
    }

    public int getManaCost() {return manaCost;}

    public String getDescription() {return "Draw 2 cards";}
}