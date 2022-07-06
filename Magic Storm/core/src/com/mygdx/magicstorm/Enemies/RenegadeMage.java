package com.mygdx.magicstorm.Enemies;

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
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.magicstorm.hero.Hero;

public class RenegadeMage extends Enemy {
    private Sprite sprite = new Sprite(new Texture(Gdx.files.internal("Mage.png")));
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private float widthCheck = sprite.getWidth();
    private Rectangle hpBar = new Rectangle(sprite.getX(), sprite.getY(), widthCheck, 10);
    private int currentHp;
    private int maxHp;
    private String currentHpString;
    private BitmapFont font = new BitmapFont();
    private SpriteBatch hpBatch = new SpriteBatch();
    private int attackValue = 30;
    private int phase = 1;
    private boolean boss = false;


    public RenegadeMage(int currentHp, int maxHp) {
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
        this.currentHp = currentHp;
        this.maxHp = maxHp;
        this.currentHpString = currentHp + "/" + maxHp;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
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
        shapeRenderer.rect(hpBar.x, hpBar.y, widthCheck * currentHp / maxHp, hpBar.height);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.end();
        batch.begin();

    }

    @Override
    //Revives with more hp but less damage
    public void takeDamage(int damage) {
        currentHp -= damage;
        currentHpString = currentHp + "/" + maxHp;
        hpBar.setWidth(widthCheck * currentHp / maxHp);
        if (currentHp <=0 & phase == 1) {
            maxHp = 20;
            currentHp = maxHp;
            attackValue = 10;
            currentHpString = currentHp + "/" + maxHp;
            sprite = new Sprite(new Texture(Gdx.files.internal("MagePhaseTwo.png")));
            sprite.setPosition(hpBar.x, hpBar.y + 30);
            phase = 2;
        }
        else if (currentHp <=0 && phase == 2) {
            this.die();
            setCurrentHpString("");
            currentHp = 0;
        }



    }

    public int getCurrentHp() {
        return this.currentHp;
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

    public void setHpBarPos(float xPos, float yPos) {
        hpBar.setPosition(xPos, yPos);
    }
    public void positionChanged() {
        sprite.setPosition(getX(),getY());
        super.positionChanged();
    }

    public void dispose() {
    }
    public boolean isDead() {
        return currentHp <= 0;
    }

    public void die() {
        this.sprite = new Sprite(new Texture(Gdx.files.internal("deadMage.png")));
        sprite.setPosition(hpBar.x, hpBar.y + 30);
        super.die();
    }


    public void doDamage(int damage) {

    }
    public void setCurrentHpString(String string) {
        this.currentHpString = string;
    }

    @Override
    public void attack(Hero hero) {
        super.attack(hero);
        hero.takeDamage(attackValue);

    }
    public String getAttackValue() {
        return String.valueOf(this.attackValue);
    }

    public int getIntAttackValue() {
        return this.attackValue;
    }

    public void setAttackValue(int value) {
        this.attackValue = value;
    }
}