package com.mygdx.magicstorm.Enemies;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.magicstorm.MagicStorm;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class Enemy extends Actor {
    private Sprite sprite;
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Rectangle hpBar;
    private int currentHp;
    private int maxHp;
    private String currentHpString;
    private BitmapFont font = new BitmapFont();
    private SpriteBatch hpBatch = new SpriteBatch();

    public void draw(SpriteBatch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }


    public void setCurrentHp(int hp) {
        this.currentHp = hp;
    }

    public void setMaxHp(int hp) {
        this.maxHp = hp;
    }

    public String getCurrentHpString() {
        return this.currentHpString;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public void takeDamage(int damage) {
        if (currentHp > 0) {
            currentHp -= damage;

            currentHpString = currentHp + "/" + maxHp;

            hpBar.setWidth(sprite.getX() * currentHp / maxHp);
            if (currentHp <= 0) {
                //fades to transparent
                this.die();
                setCurrentHpString("");
            }
        }
    }
    public int getCurrentHp() {
        return this.currentHp;
    }

    public void die() {
        ColorAction colorAction = new ColorAction();
        colorAction.setEndColor(Color.CLEAR);
        colorAction.setDuration(2f);
        this.addAction(colorAction);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                addAction(sequence(fadeOut(1f)));
            }
        }, 1);
    }

    public void setHpBarPos(int xPos, int yPos) {
        hpBar.setPosition(xPos, yPos);
    }

    public void setCurrentHpString(String string) {
        this.currentHpString = string;
    }
}
