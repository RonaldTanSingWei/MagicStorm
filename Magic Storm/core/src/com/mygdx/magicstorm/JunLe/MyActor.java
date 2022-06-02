package com.badlogic.drop;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.utils.Timer;

public class MyActor extends Actor {
    Sprite sprite = new Sprite (new Texture(Gdx.files.internal("monster 1.png")));
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Rectangle hpBar;
    private int currentHp;
    private int maxHP;
    private String currentHpString;
    private BitmapFont font = new BitmapFont();


    public MyActor() {
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
        hpBar = new Rectangle(600,110 - 50,184,10);
        maxHP = 10;
        currentHp = maxHP;
        currentHpString = currentHp + "/" + maxHP;
        /*addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.RIGHT) {
                    MoveByAction mba = new MoveByAction();
                    mba.setAmount(100f,0f);
                    mba.setDuration(5f);
                    MyActor.this.addAction(mba);
                }
                return true;
            }
        });*/
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setColor(getColor());
        sprite.draw(batch, parentAlpha);
        font.draw(batch, currentHpString, hpBar.getX(), hpBar.getY());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(hpBar.x, hpBar.y, hpBar.width, hpBar.height);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.end();

    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    protected void positionChanged() {
        sprite.setPosition(getX(),getY());
        super.positionChanged();
    }
    public void takeDamage(int damage) {
        currentHp -= damage;
        if (currentHp <=0) {
            currentHp = 0;
            //fades to transparent
            ColorAction colorAction = new ColorAction();
            colorAction.setEndColor(Color.CLEAR);
            colorAction.setDuration(2f);
            MyActor.this.addAction(colorAction);
        }
        currentHpString = currentHp + "/" + maxHP;

        hpBar.setWidth(184 * currentHp / maxHP);
    }
    public int getCurrentHp() {
        return this.currentHp;
    }

    public void die() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                addAction(sequence(fadeOut(1f)));
            }
        }, 2);
    }
}