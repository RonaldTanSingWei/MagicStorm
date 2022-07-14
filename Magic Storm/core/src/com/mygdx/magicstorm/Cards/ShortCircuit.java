package com.mygdx.magicstorm.Cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Touchable;


//Effect: Enemies deal random (0, Atk/3) damage next turn
public class ShortCircuit extends Attack {
    private Sprite sprite = new Sprite(new Texture(Gdx.files.internal("ShortCircuit.png")));
    private int manaCost = 2;
    public ShortCircuit(int value) {
        super(value);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
        setName("Short Circuit");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setColor(getColor());
        sprite.draw(batch, parentAlpha);
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

    public String getDescription() {return "Halve attack damage of targeted enemy";}
}