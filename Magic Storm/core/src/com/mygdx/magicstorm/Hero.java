package com.mygdx.magicstorm;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Hero extends Actor {
    private Sprite sprite = new Sprite(new Texture(Gdx.files.internal("Player.png")));
    private BitmapFont font = new BitmapFont();
    private SpriteBatch hpBatch = new SpriteBatch();
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Rectangle hpBar;
    private int currentHp;
    private int maxHp;
    private String currentHpString;
    private int currentArmor;
    private String currentArmorString;
    private int currentMana;
    private int maxMana;
    private int widthCheck = (int) sprite.getWidth();

    public Hero() {
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
        maxHp = 100;
        currentHp = 90;
        hpBar = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth() * currentHp / maxHp,10);
        currentHpString = currentHp + "/" + maxHp;
        currentArmor = 0;
    }
    public boolean isDead() {
        return currentHp <= 0;
    }

    public void gainArmor(int armor) {
        currentArmor += armor;
    }

    public void gainHp(int hp) {
        currentHp += hp;
        if (currentHp > maxHp) {
            currentHp = maxHp;
        }
        currentHpString = currentHp + "/" + maxHp;

        hpBar.setWidth(widthCheck * currentHp / maxHp);
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

    public void setHpBarPos(int xPos, int yPos) {
        hpBar.setPosition(xPos, yPos);
    }
    public void positionChanged() {
        sprite.setPosition(getX(),getY());
        super.positionChanged();
    }
}
