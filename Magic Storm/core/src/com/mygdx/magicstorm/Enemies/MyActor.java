package com.mygdx.magicstorm.Enemies;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class MyActor extends Enemy {
    private Sprite sprite = new Sprite(new Texture(Gdx.files.internal("Goblin.png")));
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Rectangle hpBar;
    private int currentHp;
    private int maxHp;
    private String currentHpString;
    private BitmapFont font = new BitmapFont();
    private SpriteBatch hpBatch = new SpriteBatch();


    public MyActor() {
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
        hpBar = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth(),10);
        maxHp = 10;
        currentHp = maxHp;
        currentHpString = currentHp + "/" + maxHp;
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
        batch.end();
        hpBatch.begin();
        font.draw(hpBatch, currentHpString, hpBar.getX(), hpBar.getY());
        hpBatch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(hpBar.x, hpBar.y, hpBar.width, hpBar.height);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.end();
        batch.begin();

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
    public void takeDamage(int damage) {
        if (currentHp > 0) {
            currentHp -= damage;

            currentHpString = currentHp + "/" + maxHp;

            hpBar.setWidth(184 * currentHp / maxHp);
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


    public void setHpBarPos(int xPos, int yPos) {
        hpBar.setPosition(xPos, yPos);
    }

    public void setCurrentHpString(String string) {
        this.currentHpString = string;
    }
}
