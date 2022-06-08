package com.mygdx.magicstorm;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Card extends Actor {
    private Sprite sprite;

    public Card(String type) {
        if (type.equals("attack")) sprite = new Sprite(new Texture(Gdx.files.internal("attack.png")));
        else sprite = new Sprite(new Texture(Gdx.files.internal("defend.png")));
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setColor(getColor());
        sprite.draw(batch, parentAlpha);
    }
    @Override
    public void act(float delta) {
        super.act(delta);
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
}
