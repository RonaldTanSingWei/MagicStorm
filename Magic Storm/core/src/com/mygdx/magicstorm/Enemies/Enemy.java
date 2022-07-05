package com.mygdx.magicstorm.Enemies;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.magicstorm.hero.Hero;

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
    private int attackValue;
    private boolean boss;


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
                currentHp = 0;
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
    public void attack(Hero hero) {
        this.addAction(Actions.moveBy(-100,0,0.2f));
        this.addAction(Actions.moveBy(100,0,0.4f));


    }

    public void setCurrentHpString(String string) {
        this.currentHpString = string;
    }
    public boolean isDead() {
        return currentHp <= 0;
    }

    public void setHpBarPos(float xPos, float yPos) {
        hpBar.setPosition(xPos, yPos);
    }

    public String getAttackValue() {
        return String.valueOf(this.attackValue);
    }

    public void setAttackValue(int value) {
        this.attackValue = value;
    }

    public boolean isBoss() {
        return boss;
    }
}
