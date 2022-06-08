package com.mygdx.magicstorm.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.magicstorm.Card;
import com.mygdx.magicstorm.Deck;
import com.mygdx.magicstorm.Enemies.Goblin;
import com.mygdx.magicstorm.hero.Hero;
import com.mygdx.magicstorm.MagicStorm;

import java.util.ArrayList;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class MainGameScreen implements Screen {
    final MagicStorm game;
    Stage stage;
<<<<<<< HEAD
    private final MainGameScreen mainGameScreen = this;
    private ArrayList<Actor> cards = new ArrayList<Actor>();
=======
    private ArrayList<Card> cards = new ArrayList<Card>();
    private ArrayList<Actor> cardsDrawn;
>>>>>>> 440a032ec0110bc67370953bfc82a72715c2337d
    boolean touched = false;
    private Vector2 touchPos;
    MainPauseScreen pauseScreen;
    SpriteBatch batch = new SpriteBatch();
    boolean cardSelected = false;
    private ScreenViewport viewport;

    private Group group = new Group();

    private Goblin goblin;
<<<<<<< HEAD
    private Hero hero;
    Actor card1;
    Actor card2;
    Actor card3;
    Group group2;
    Actor selectedCard;
    int cardNo = 3;
=======
    private Hero player;
    Card card1;
    Card card2;
    Card card3;
    Card selectedCard;
    Card drawnCard;
    int cardNo;
>>>>>>> 440a032ec0110bc67370953bfc82a72715c2337d
    int handSize;
    int spaceAtEachSide;
    int cardWidth;
    int cardHeight;
    int selectedCardX;
    int selectedCardY;
<<<<<<< HEAD
    boolean enemyTurn;
    boolean startOfBattle;
    boolean startOfTurn;
=======
    Deck deck;
>>>>>>> 440a032ec0110bc67370953bfc82a72715c2337d

    public enum State {
        PAUSE,
        PLAYERTURN,

        STARTTURN,

        ENEMYTURN,
        RESUME
    }
    private State state = State.STARTTURN;

    public MainGameScreen(MagicStorm game){
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Group group1 = new Group();

<<<<<<< HEAD
        enemyTurn = false;
        startOfTurn = true;

        Goblin goblin = new Goblin(15, 15);
        Goblin goblin1 = new Goblin(10, 10);
        card1 = new Image(new Texture(Gdx.files.internal("attack.png")));
=======
        Goblin goblin = new Goblin(10, 10);
>>>>>>> 440a032ec0110bc67370953bfc82a72715c2337d
        Hero hero = new Hero();
        card1 = new Card("attack");
        card2 = new Card("attack");
        card3 = new Card("defend");
        Image background = new Image(new Texture(Gdx.files.internal("background.jpg")));
        Image endTurnButton = new Image(new Texture(Gdx.files.internal("endTurn.png")));
        Image mana = new Image(new Texture(Gdx.files.internal("Mana.png")));
        Image enemyTurn = new Image(new Texture(Gdx.files.internal("EnemyTurn.png")));
        Image startTurn = new Image(new Texture(Gdx.files.internal("startTurn.png")));
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        hero.setName("hero");
        card1.setName("card1");
        card2.setName("card2");
        card3.setName("card2");
        background.setName("background");
        goblin.setName("goblin");
<<<<<<< HEAD
        goblin1.setName("goblin1");
        endTurnButton.setName("endTurnButton");
        mana.setName("mana");
        enemyTurn.setName("enemyTurn");
        startTurn.setName("startTurn");
=======
        deck = new Deck(10,10);
        deck.setName("deck");
>>>>>>> 440a032ec0110bc67370953bfc82a72715c2337d
        // order actors are drawn in

        group1.addActor(background);
        group1.addActor(mana);
        group1.addActor(hero);
        group1.addActor(card1);
        group1.addActor(card2);
        group1.addActor(card3);
        group1.addActor(goblin);
<<<<<<< HEAD
        group1.addActor(endTurnButton);
        group1.addActor(goblin1);
        group1.addActor(enemyTurn);
        group1.addActor(startTurn);
=======
        group1.addActor(deck);
>>>>>>> 440a032ec0110bc67370953bfc82a72715c2337d

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cardNo = cards.size();

        stage.addActor(group1);
        hero.setPosition(0,stage.getHeight() / 3);
<<<<<<< HEAD
        hero.setHpBarPos(hero.getX(), ((stage.getHeight() / 3) - 30));
        hero.setArmorBarPos(hero.getX(),  ((stage.getHeight() / 3) - 60));

        cardWidth = (int)card1.getWidth();
        handSize = (int) (cardNo * cardWidth);
        spaceAtEachSide = (int) ((stage.getWidth() - handSize) / 2);
        for (int i = 0; i < cards.size(); i ++) {
            final Actor tempActor = cards.get(i);
            tempActor.setPosition(spaceAtEachSide,0);
            spaceAtEachSide += 120;
        }
        goblin.setPosition(stage.getWidth() * 4/5,stage.getHeight() / 3);
        goblin.setHpBarPos( (stage.getWidth() * 4/5), ((stage.getHeight() / 3)));
        goblin1.setPosition(stage.getWidth() * 4/5,stage.getHeight() * 2/ 3);
        goblin1.setHpBarPos( (stage.getWidth() * 4/5),  ((stage.getHeight() * 2/ 3)));

        endTurnButton.setPosition((stage.getWidth() * 4/5), stage.getHeight()* 1/10);
        mana.setPosition((stage.getWidth() * 1/5), stage.getHeight() * 1/10);
        enemyTurn.setPosition(stage.getWidth()*1/4, stage.getHeight()*3/4);
        enemyTurn.addAction(sequence(fadeOut(0f)));
        startTurn.setPosition(stage.getWidth()*1/4, stage.getHeight()*3/4);
        startTurn.addAction(sequence(fadeOut(0f)));
=======
        hero.setHpBarPos(0, (int) ((stage.getHeight() / 3) - 30));
        deck.setPosition(50,50);

        cardWidth = (int)card1.getWidth();
        cardHeight = (int)card1.getHeight();


        goblin.setPosition(stage.getWidth() - goblin.getWidth(),stage.getHeight() / 3);
        goblin.setHpBarPos((int) (stage.getWidth() - goblin.getWidth()), (int) ((stage.getHeight() / 3) - 30));
>>>>>>> 440a032ec0110bc67370953bfc82a72715c2337d

        Gdx.input.setInputProcessor(this.game);

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        pauseScreen = new MainPauseScreen(this.game, this);
        Group group = (Group) stage.getActors().first();
        final Hero hero = group.findActor("hero");
        final Goblin goblin = group.findActor("goblin");
        Goblin goblin1 = group.findActor("goblin1");
        Actor endTurn = group.findActor("enemyTurn");
        final Actor startTurn = group.findActor("startTurn");
        switch (state) {
<<<<<<< HEAD
            case PLAYERTURN:

=======
            case RUN:
                Group group = (Group) stage.getActors().first();
                Hero hero = group.findActor("hero");
                Goblin goblin = group.findActor("goblin");
                Deck deck = group.findActor("deck");
>>>>>>> 440a032ec0110bc67370953bfc82a72715c2337d
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                stage.act(Gdx.graphics.getDeltaTime());
                stage.draw();

                //mouse click
                if (Gdx.input.isTouched() && !enemyTurn && !startOfTurn) {
                    Vector2 coord = stage.screenToStageCoordinates(new Vector2((float) Gdx.input.getX(), (float) Gdx.input.getY()));
                    final Actor hitActor = stage.hit(coord.x, coord.y, true);
                    if (hitActor != null) {
                        if ((hitActor instanceof Card && !cardSelected)) {
                            cardSelected = true;
                            selectedCard = (Card) hitActor;
                            selectedCardX = (int) selectedCard.getX();
                            selectedCardY = (int) selectedCard.getY();
                            RunnableAction ra = new RunnableAction();
                            ra.setRunnable(new Runnable() {
                                @Override
                                public void run() {
                                    hitActor.setPosition((float) ((stage.getWidth() / 2) - ((cardWidth * 1.5) / 2)), (float) ((stage.getHeight()) / 2 - ((cardHeight * 1.5) / 2)));
                                    hitActor.setScale(1.5f);
                                }
                            });
                            hitActor.addAction(ra);

                        }
<<<<<<< HEAD
                        else if ((hitActor.getName().equals("goblin") || hitActor.getName().equals("goblin1"))  && cardSelected) {
=======

                        else if(hitActor instanceof Card && !hitActor.equals(selectedCard) && cardSelected) {
                            selectedCard.setPosition(selectedCardX,selectedCardY);
                            selectedCard.setScale(1f);
                            selectedCard = (Card) hitActor;
                            selectedCardX = (int) selectedCard.getX();
                            selectedCardY = (int) selectedCard.getY();
                            RunnableAction ra = new RunnableAction();
                            ra.setRunnable(new Runnable() {
                                @Override
                                public void run() {
                                    hitActor.setPosition((float) ((stage.getWidth() / 2) - ((cardWidth * 1.5) / 2)), (float) ((stage.getHeight()) / 2 - ((cardHeight * 1.5) / 2)));
                                    hitActor.setScale(1.5f);
                                }
                            });
                            hitActor.addAction(ra);
                        }

                        else if ((hitActor instanceof Goblin  && cardSelected)) {
>>>>>>> 440a032ec0110bc67370953bfc82a72715c2337d
                            cardSelected = false;
                            selectedCard.remove();
                            goblin.takeDamage(5);
                            goblin1.takeDamage(10);
                            cardNo -= 1;
                            cards.remove(selectedCard);
                            shuffle();


                        }
                        else if ((hitActor instanceof Hero  && cardSelected)) {
                            cardSelected = false;
                            selectedCard.remove();
                            hero.gainArmor(5);
                            hero.gainHp(5);
                            cardNo -= 1;
                            cards.remove(selectedCard);
<<<<<<< HEAD
                            handSize = (int) (cardNo * cardWidth);
                            spaceAtEachSide = (int) ((stage.getWidth() - handSize) / 2);
                            for (int i = 0; i < cards.size(); i ++) {
                                final Actor tempActor = cards.get(i);
                                tempActor.setPosition(spaceAtEachSide,0);
                                spaceAtEachSide += 120;
                            }
                            goblin.attack(hero);
                        } else if (hitActor.getName().equals("endTurnButton")) {
                            enemyTurn = true;
                            this.state = State.ENEMYTURN;

=======
                            shuffle();
>>>>>>> 440a032ec0110bc67370953bfc82a72715c2337d
                        }
                    }
                }

                if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                    selectedCard.setPosition(selectedCardX,selectedCardY);
                    selectedCard.setScale(1f);
                    cardSelected = false;
                }
                break;
            case ENEMYTURN:
                endTurn.addAction(sequence(fadeIn(1f),fadeOut(1f)));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        if (!goblin.isDead()) {
                            goblin.attack(hero);
                        }
                        enemyTurn = false;
                    }
                }, 3);
                this.state = State.STARTTURN;
                break;
            case STARTTURN:
                startOfTurn = true;
                if (startOfBattle) {
                    //draw 5
                    startOfBattle = false;
                }
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        startTurn.addAction(sequence(fadeIn(1f), fadeOut(1f)));
                        hero.setMana(5);
                        startOfTurn = false;
                    }
                }, 4);


                this.state = State.PLAYERTURN;
                //draw 2
                break;
            case PAUSE:
                break;
            case RESUME:
                Gdx.graphics.setContinuousRendering(true);
                if (enemyTurn) {
                    this.state = State.ENEMYTURN;
                } else {
                    this.state = State.PLAYERTURN;
                }
                break;
            default:
                this.state = State.PLAYERTURN;
                break;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            resume();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
            pause();
        }

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        this.state = State.PAUSE;
        game.setScreen(pauseScreen);
    }

    @Override
    public void resume() {
        this.state = State.PLAYERTURN;
        game.setScreen(game.getScreen());
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    //aligns hand with centre of screen
    public void shuffle() {
        handSize = (int) (cardNo * cardWidth);
        spaceAtEachSide = (int) ((stage.getWidth() - handSize) / 2);
        for (int i = 0; i < cards.size(); i++) {
            final Actor tempActor = cards.get(i);
            tempActor.setPosition(spaceAtEachSide, 0);
            spaceAtEachSide += 120;
        }
    }

    public void drawCard(int numberDrawn) {
        for (int i = 0; i < numberDrawn; i++) {
            drawnCard = deck.drawCard();
            cards.add(drawnCard);
            stage.addActor(drawnCard);
            cardNo += 1;

        }
        shuffle();
    }
}
