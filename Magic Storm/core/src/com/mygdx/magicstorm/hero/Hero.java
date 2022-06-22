package com.mygdx.magicstorm.hero;

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
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.magicstorm.Cards.Deck;

public class Hero extends Actor {
    private Sprite sprite = new Sprite(new Texture(Gdx.files.internal("Player.png")));
    private BitmapFont font = new BitmapFont();
    private SpriteBatch hpBatch = new SpriteBatch();
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Rectangle hpBar;
    private Rectangle armorBar;
    private int currentHp;
    private int maxHp;
    private String currentHpString;
    private int currentArmor;
    private int maxArmor = 0;
    private String currentArmorString;
    private Mana mana;
    private float widthCheck = sprite.getWidth();

    private Deck deck = new Deck(6, 6);


    public Hero() {
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
        maxHp = 100;
        currentHp = 90;
        hpBar = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth() * currentHp / maxHp,10);
        currentHpString = currentHp + "/" + maxHp;
        currentArmor = 5;
        maxArmor = 10;
        armorBar = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth() * currentArmor/ maxArmor,10);
        currentArmorString = Integer.toString(currentArmor);
        mana = new Mana(5,5);
        mana.setCurrentManaString(mana.getCurrentMana() + "/" + mana.getMaxMana());

    }
    public boolean isDead() {
        return currentHp <= 0;
    }

    public void gainArmor(int armor) {
        currentArmor += armor;
        if (currentArmor > maxArmor) {
            maxArmor = currentArmor;
        }
        currentArmorString = Integer.toString(currentArmor);
        armorBar.setWidth(widthCheck * currentArmor / maxArmor);
    }


    public void gainHp(int hp) {
        currentHp += hp;
        if (currentHp > maxHp) {
            currentHp = maxHp;
        }
        currentHpString = currentHp + "/" + maxHp;

        hpBar.setWidth(widthCheck * currentHp / maxHp);
    }

    public int getMaxHp() {
        return this.maxHp;
    }

    public void setMaxHp(int hp) {
        this.maxHp = hp;
        currentHpString = currentHp + "/" + maxHp;
    }

    public void setArmor(int armor) {
        this.currentArmor = armor;
        armorBar.setWidth(0);
        currentArmorString = "";
    }

    public void takeDamage(int damage) {
        if (damage <= currentArmor) {
            currentArmor -= damage;
            armorBar.setWidth(widthCheck * currentArmor / maxArmor);
            currentArmorString = Integer.toString(currentArmor);
            if (currentArmor == 0) {
                currentArmorString = "";
            }
        } else {
            int damageTaken = damage - currentArmor;
            currentArmor = 0;
            currentHp -= damageTaken;
            currentArmorString = "";
            currentHpString = currentHp + "/" + maxHp;
            hpBar.setWidth(widthCheck * currentHp / maxHp);

        }
        this.addAction(Actions.moveBy(-30,0,0.1f));
        this.addAction(Actions.moveBy(30,0,0.2f));
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setColor(getColor());
        sprite.draw(batch, parentAlpha);
        batch.end();
        hpBatch.begin();
        font.draw(hpBatch, currentHpString, hpBar.getX(), hpBar.getY());
        font.draw(hpBatch, currentArmorString, armorBar.getX(),armorBar.getY());
        font.draw(hpBatch, mana.getCurrentManaString(), getStage().getWidth()  * 51/200, getStage().getHeight() * 1/4);
        hpBatch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(hpBar.x, hpBar.y, hpBar.width, hpBar.height);
        shapeRenderer.setColor(Color.GRAY);
        shapeRenderer.rect(armorBar.x, armorBar.y, armorBar.width, armorBar.height);
        shapeRenderer.end();
        batch.begin();

    }
    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public void setHpBarPos(float xPos, float yPos) {
        hpBar.setPosition(xPos, yPos);
    }
    public void setArmorBarPos(float xPos, float yPos) {
        armorBar.setPosition(xPos, yPos);
    }
    public void positionChanged() {
        sprite.setPosition(getX(),getY());
        super.positionChanged();
    }

    public Rectangle getHpBar() {
        return this.hpBar;
    }

    public void setMana(int mana) {
        this.mana.setCurrentMana(mana);
    }

    public Deck getDeck() {
        return this.deck;
    }


}
