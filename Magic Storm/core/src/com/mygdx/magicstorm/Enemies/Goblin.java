package com.mygdx.magicstorm.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.mygdx.magicstorm.MagicStorm;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import sun.jvm.hotspot.utilities.BitMap;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class Goblin extends Enemy {
    private MagicStorm game;
    private int currentHp;
    private int maxHp;
    private Rectangle hpBar;
    private Rectangle bounds;
    private Texture texture = new Texture(Gdx.files.internal("Goblin.png"));
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private BitmapFont font = new BitmapFont();
    private String currentHpString;
    ;

    public Goblin(int currentHp, int maxHp, int posX, int posY) {
        this.currentHp = currentHp;
        this.maxHp = maxHp;
         bounds = new Rectangle(posX,posY,150,150);
         hpBar = new Rectangle(posX,posY - 50,184,10);
        this.currentHpString = currentHp + "/" + maxHp;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        moveBy(100,100);
    }

    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {

        Color color = getColor();
        this.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.begin();
        batch.draw(texture, bounds.x, bounds.y);
        font.draw(batch, currentHpString, hpBar.getX(), hpBar.getY());
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(hpBar.x, hpBar.y, hpBar.width, hpBar.height);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.end();


    }

    @Override
    public void takeDamage(int damage) {
        currentHp -= damage;
        if (currentHp <=0) {
            currentHp = 0;
        }
        currentHpString = currentHp + "/" + maxHp;

        hpBar.setWidth(184 * currentHp / maxHp);


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

    public void dispose() {
    }
public boolean isDead() {
    return currentHp <= 0;
}
    public void die() {
        this.texture = new Texture(Gdx.files.internal("deadGoblin.png"));
        currentHpString = "";
        this.setVisible(false);
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    public void doDamage(int damage) {

    }
}
