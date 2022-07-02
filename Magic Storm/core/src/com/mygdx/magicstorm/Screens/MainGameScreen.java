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
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.magicstorm.Cards.Attack;
import com.mygdx.magicstorm.Cards.Card;
import com.mygdx.magicstorm.Cards.Deck;
import com.mygdx.magicstorm.Cards.Defence;
import com.mygdx.magicstorm.Enemies.Enemy;
import com.mygdx.magicstorm.Enemies.Goblin;
import com.mygdx.magicstorm.Enemies.MagicalConstruct;
import com.mygdx.magicstorm.Enemies.RenegadeMage;
import com.mygdx.magicstorm.Rewards.AttackReward;
import com.mygdx.magicstorm.Rewards.DefenceReward;
import com.mygdx.magicstorm.Rewards.HpReward;
import com.mygdx.magicstorm.Rewards.Reward;
import com.mygdx.magicstorm.UltimateSkills.UltimateArmorGain;
import com.mygdx.magicstorm.UltimateSkills.UltimateDamageDone;
import com.mygdx.magicstorm.hero.Hero;
import com.mygdx.magicstorm.MagicStorm;

import java.util.ArrayList;
import java.util.Random;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class MainGameScreen implements Screen {
    private final MagicStorm game;
    private Stage stage;
    private ArrayList<Card> cards = new ArrayList<Card>();
    private ArrayList<Actor> cardsDrawn;
    private boolean touched = false;
    private Vector2 touchPos;
    private MainPauseScreen pauseScreen;
    private VictoryScreen victoryScreen;
    private DefeatScreen defeatScreen;
    private SpriteBatch batch = new SpriteBatch();
    private boolean cardSelected = false;
    private ScreenViewport viewport;
    private int floor = 1;
    private int easyEnemies = 2;
    private int hardEnemies = 2;


    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Enemy currentEnemy;

    private boolean availableRewards;
    private boolean selectingRewards;

    private Hero hero;
    private Group group = new Group();
    private Card selectedCard;
    private int cardNo;

    private Card drawnCard;
    private int handSize;
    private int spaceAtEachSide;

    private Card sampleCard = new Attack(10);
    private float cardWidth = sampleCard.getWidth();
    private float cardHeight = sampleCard.getHeight();
    private int selectedCardX;
    private int selectedCardY;
    private boolean enemyTurn;
    private boolean startOfBattle;
    private boolean startOfTurn;
    private Random rand = new Random();

    private Deck deck;

    private AttackReward attackReward = new AttackReward();

    private DefenceReward defenceReward = new DefenceReward();

    private HpReward hpReward = new HpReward();

    public enum State {
        PAUSE,
        PLAYERTURN,
        STARTTURN,
        ENEMYTURN,
        VICTORY,
        DEFEAT,
        RESUME
    }

    private State state = State.STARTTURN;

    public MainGameScreen(MagicStorm game, Hero hero) {
        this.hero = hero;
        this.game = game;
        stage = new Stage(new ScreenViewport());
        startOfBattle = true;
        enemyTurn = false;
        startOfTurn = true;
        availableRewards = true;
        selectingRewards = false;
        Goblin goblin = new Goblin(10, 10);
        Image background = new Image(new Texture(Gdx.files.internal("Background.jpg")));
        Image endTurnButton = new Image(new Texture(Gdx.files.internal("endTurn.png")));
        Image mana = new Image(new Texture(Gdx.files.internal("mana.png")));
        Image enemyTurn = new Image(new Texture(Gdx.files.internal("EnemyTurn.png")));
        Image startTurn = new Image(new Texture(Gdx.files.internal("startTurn.png")));
        Image nextStage = new Image(new Texture(Gdx.files.internal("nextStage.png")));
        Image rewardsButton = new Image(new Texture(Gdx.files.internal("rewardsButton.png")));
        Image ultimateSkill = new Image(new Texture(Gdx.files.internal("ultimateSkill.png")));
        Image cardback = new Image(new Texture(Gdx.files.internal("cardback.png")));
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        hero.setName("hero");
        background.setName("background");
        goblin.setName("goblin");
        Goblin goblin1 = new Goblin(15,15);
        Goblin goblin2 = new Goblin(10,10);
        RenegadeMage mage1 = new RenegadeMage(10,10);
        MagicalConstruct construct1 = new MagicalConstruct(40,40);
        goblin1.setName("goblin1");
        goblin2.setName("goblin2");
        mage1.setName("mage1");
        construct1.setName("construct1");
        endTurnButton.setName("endTurnButton");
        mana.setName("mana");
        enemyTurn.setName("enemyTurn");
        startTurn.setName("startTurn");
        nextStage.setName("nextStage");
        rewardsButton.setName("rewardsButton");
        attackReward.setName("attackReward");
        defenceReward.setName("defenceReward");
        hpReward.setName("hpReward");
        deck = copyDeck(hero.getDeck());
        ultimateSkill.setName("ultimateSkill");
        cardback.setName("cardback");
        // order actors are drawn in

        group.addActor(background);
        group.addActor(mana);
        group.addActor(hero);
        group.addActor(endTurnButton);
        group.addActor(enemyTurn);
        group.addActor(startTurn);
        group.addActor(nextStage);
        group.addActor(rewardsButton);
        group.addActor(attackReward);
        group.addActor(defenceReward);
        group.addActor(hpReward);
        group.addActor(ultimateSkill);
        group.addActor(cardback);


        cardNo = cards.size();

        currentEnemy = goblin;
        group.addActor(currentEnemy);
        // add enemies in order of difficulty, randomise different parts of enemy array depending on current floor -- need to remove enemies because death state is carried over
        enemies.add(goblin1);
        enemies.add(goblin2);
        enemies.add(mage1);
        enemies.add(construct1);

        stage.addActor(group);
        hero.setPosition(0, stage.getHeight() / 3);
        hero.setHpBarPos(hero.getX(), ((stage.getHeight() / 3) - 30));
        hero.setArmorBarPos(hero.getX(), ((stage.getHeight() / 3) - 60));
        hero.setUltimateBarPos(stage.getWidth() * 1/10, stage.getHeight() * 9/10);



        endTurnButton.setPosition((stage.getWidth() * 4 / 5), stage.getHeight() * 1 / 10);
        mana.setPosition((stage.getWidth() * 1 / 5), stage.getHeight() * 1 / 10);
        enemyTurn.setPosition(stage.getWidth() * 1 / 4, stage.getHeight() * 3 / 4);
        enemyTurn.addAction(sequence(fadeOut(0f)));
        startTurn.setPosition(stage.getWidth() * 1 / 4, stage.getHeight() * 3 / 4);
        startTurn.addAction(sequence(fadeOut(0f)));
        nextStage.setPosition(stage.getWidth()* 4/5, stage.getHeight() * 1/5);
        nextStage.addAction(sequence(fadeOut(0f)));
        rewardsButton.setPosition(stage.getWidth()* 4/5, stage.getHeight() * 4/5);
        rewardsButton.addAction(fadeOut(0f));
        attackReward.setPosition(stage.getWidth()* 1/3, stage.getHeight() * 1/2);
        attackReward.addAction(fadeOut(0f));
        defenceReward.setPosition(attackReward.getX() + attackReward.getWidth() + 100, stage.getHeight() * 1/2);
        defenceReward.addAction(fadeOut(0f));
        hpReward.setPosition(defenceReward.getX() + defenceReward.getWidth() + 100, stage.getHeight() * 1/2);
        hpReward.addAction(fadeOut(0f));
        hero.setHpBarPos(0, (int) ((stage.getHeight() / 3) - 30));
        deck.setPosition(50, 50);
        cardback.setPosition(50, 50);
        ultimateSkill.setPosition(stage.getWidth() * 1/10, stage.getHeight() * 9/10);
        ultimateSkill.addAction(fadeOut(0f));


        shuffle();

        currentEnemy.setPosition(stage.getWidth() - currentEnemy.getWidth(), stage.getHeight() / 3);
        currentEnemy.setHpBarPos((int) (stage.getWidth() - currentEnemy.getWidth()), (int) ((stage.getHeight() / 3) - 30));


        Gdx.input.setInputProcessor(this.game);


    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        pauseScreen = new MainPauseScreen(game, this);
        victoryScreen = new VictoryScreen(game);
        defeatScreen = new DefeatScreen(game);
        final Hero hero = group.findActor("hero");
        Actor endTurn = group.findActor("enemyTurn");
        final Actor startTurn = group.findActor("startTurn");
        final Actor nextStage = group.findActor("nextStage");
        final Actor endTurnButton = group.findActor("endTurnButton");
        AttackReward attackReward = group.findActor("attackReward");
        DefenceReward defenceReward = group.findActor("defenceReward");
        HpReward hpReward = group.findActor("hpReward");
        nextStage.setTouchable(Touchable.disabled);
        Actor rewardsButton = group.findActor("rewardsButton");
        rewardsButton.setTouchable(Touchable.disabled);
        Actor ultimateSkill = group.findActor("ultimateSkill");
        ultimateSkill.setTouchable(Touchable.disabled);

        switch (state) {
            case PLAYERTURN:

                game.batch.begin();
                game.font.draw(game.batch, deck.getCurrentDeckSizeString() , deck.getX() + (deck.getWidth() / 2) + 50,deck.getY() + deck.getHeight() + 75);
                if (!currentEnemy.isDead()) game.font.draw(game.batch, "Attack Damage: " + currentEnemy.getAttackValue(), currentEnemy.getX(), currentEnemy.getY() - 50);
                game.batch.end();
                if(currentEnemy.isDead()) {
                    for (int i = 0; i < cards.size(); i++) {
                        (cards.get(i)).remove();
                    }
                    cards.clear();
                    cardNo = 0;
                    if (availableRewards) {
                        rewardsButton.addAction(fadeIn(0f));
                        rewardsButton.setTouchable(Touchable.enabled);
                    } else if (!selectingRewards) {
                        nextStage.addAction(fadeIn(0f));
                        nextStage.setTouchable(Touchable.enabled);
                    }
                    endTurnButton.setTouchable(Touchable.disabled);
                    endTurnButton.addAction(fadeOut(0f));
                    ultimateSkill.setTouchable(Touchable.disabled);
                    ultimateSkill.addAction(fadeOut(0f));
                    hero.setUltimateProgress(0);
                    hero.setArmor(0);
                    shuffle();
                }
                if (hero.isDead()) {
                    hero.die();
                    this.state = State.DEFEAT;
                    game.setScreen(defeatScreen);
                }

                if (hero.getUltimateSkill().isReady()) {
                    ultimateSkill.addAction(fadeIn(0f));
                    ultimateSkill.setTouchable(Touchable.enabled);
                    hero.setUltimateProgress(hero.getUltimateMaxCounter());
                    hero.resetUltimate();

                }
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

                        } else if (hitActor instanceof Card && !hitActor.equals(selectedCard) && cardSelected) {
                            selectedCard.setPosition(selectedCardX, selectedCardY);
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

                        } else if ((hitActor instanceof Enemy && cardSelected && selectedCard.getName().equals("attack"))) {
                            int damage = selectedCard.getAttack();
                            int armor = selectedCard.getDefence();
                            selectedCard.setScale(1f);
                            selectedCard.dealDamage(currentEnemy);
                            if (hero.getUltimateSkill() instanceof UltimateDamageDone) {
                                hero.progressUltimate(damage);
                            }
                            if (hero.getUltimateSkill() instanceof UltimateArmorGain) {
                                hero.progressUltimate(armor);
                            }

                            cardSelected = false;
                            cards.remove(selectedCard);
                            selectedCard.remove();
                            cardNo -= 1;
                            shuffle();

                        } else if ((hitActor instanceof Hero && cardSelected && selectedCard.getName().equals("defence"))) {
                            int damage = selectedCard.getAttack();
                            int armor = selectedCard.getDefence();
                            selectedCard.setScale(1f);
                            selectedCard.addDefence(hero);
                            if (hero.getUltimateSkill() instanceof UltimateDamageDone) {
                                hero.progressUltimate(damage);
                            }
                            if (hero.getUltimateSkill() instanceof UltimateArmorGain) {
                                hero.progressUltimate(armor);
                            }

                            cardSelected = false;
                            cards.remove(selectedCard);
                            selectedCard.remove();
                            cardNo -= 1;
                            shuffle();

                        } else if (hitActor.getName().equals("ultimateSkill")) {
                            ultimateSkill.addAction(fadeOut(0f));
                            ultimateSkill.setTouchable(Touchable.disabled);
                            hero.getUltimateSkill().effect(hero, currentEnemy);
                            hero.setUltimateProgress(0);
                        } else if (hitActor.getName().equals("endTurnButton")) {
                            enemyTurn = true;
                            this.state = State.ENEMYTURN;

                        } else if (hitActor.getName().equals("rewardsButton")) {
                            availableRewards = false;
                            rewardsButton.setTouchable(Touchable.disabled);
                            rewardsButton.addAction(fadeOut(0f));
                            attackReward.setTouchable(Touchable.enabled);
                            attackReward.addAction(fadeIn(0f));
                            defenceReward.setTouchable(Touchable.enabled);
                            defenceReward.addAction(fadeIn(0f));
                            hpReward.setTouchable(Touchable.enabled);
                            hpReward.addAction(fadeIn(0f));
                            selectingRewards = true;
                        } else if (hitActor.getName().equals("attackReward")){
                            attackReward.setTouchable(Touchable.disabled);
                            attackReward.addAction(fadeOut(0f));
                            defenceReward.setTouchable(Touchable.disabled);
                            defenceReward.addAction(fadeOut(0f));
                            hpReward.setTouchable(Touchable.disabled);
                            hpReward.addAction(fadeOut(0f));
                            selectingRewards = false;
                            //add reward effects here
                            attackReward.rewardEffect(hero);
                        } else if (hitActor.getName().equals("defenceReward")){
                            attackReward.setTouchable(Touchable.disabled);
                            attackReward.addAction(fadeOut(0f));
                            defenceReward.setTouchable(Touchable.disabled);
                            defenceReward.addAction(fadeOut(0f));
                            hpReward.setTouchable(Touchable.disabled);
                            hpReward.addAction(fadeOut(0f));
                            selectingRewards = false;
                            //add reward effects here
                            defenceReward.rewardEffect(hero);
                        } else if (hitActor.getName().equals("hpReward")){
                            attackReward.setTouchable(Touchable.disabled);
                            attackReward.addAction(fadeOut(0f));
                            defenceReward.setTouchable(Touchable.disabled);
                            defenceReward.addAction(fadeOut(0f));
                            hpReward.setTouchable(Touchable.disabled);
                            hpReward.addAction(fadeOut(0f));
                            selectingRewards = false;
                            //add reward effects here
                            hpReward.rewardEffect(hero);
                        } else if (hitActor.getName().equals("nextStage")) {
                            floor += 1;
                            if (floor > 5) {
                                this.state = State.VICTORY;
                                game.setScreen(victoryScreen);
                            }
                            else {
                                int random;
                                if (floor <= 3) {
                                    random = rand.nextInt(easyEnemies);
                                    easyEnemies -= 1;
                                }
                                else {
                                    random = rand.nextInt(hardEnemies);
                                    hardEnemies -= 1;
                                }
                                currentEnemy = enemies.remove(random);
                                stage.addActor(currentEnemy);
                                currentEnemy.draw(batch, delta);
                                currentEnemy.setPosition(stage.getWidth() - currentEnemy.getWidth(), stage.getHeight() / 3);
                                currentEnemy.setHpBarPos((int) (stage.getWidth() - currentEnemy.getWidth()), (int) ((stage.getHeight() / 3) - 30));
                                nextStage.addAction(fadeOut(0f));
                                startOfBattle = true;
                                this.state = State.STARTTURN;
                            }
                        }
                    }
                }

                if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                    selectedCard.setPosition(selectedCardX, selectedCardY);
                    selectedCard.setScale(1f);
                    cardSelected = false;
                }
                break;
            case ENEMYTURN:
                startTurn.addAction(fadeOut(0f));
                endTurn.addAction(sequence(fadeIn(1f), fadeOut(1f)));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        if (!currentEnemy.isDead()) {
                            currentEnemy.attack(hero);
                        }
                        enemyTurn = false;
                    }
                }, 3);
                this.state = State.STARTTURN;
                break;
            case STARTTURN:
                startOfTurn = true;
                if (startOfBattle) {
                    deck = copyDeck(hero.getDeck());
                    drawCard(3);
                    shuffle();
                    deck.setCurrentDeckSizeString(String.valueOf(deck.getCurrentDeckSize()));
                    availableRewards = true;
                    startOfBattle = false;
                    startTurn.addAction(sequence(fadeIn(1f), fadeOut(1f)));
                    startOfTurn = false;
                    endTurnButton.setTouchable(Touchable.enabled);
                    endTurnButton.addAction(fadeIn(0f));
                    hero.setArmor(0);
                    hero.setUltimateProgress(0);
                } else {
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            startTurn.addAction(sequence(fadeIn(1f), fadeOut(1f)));
                            hero.setMana(5);
                            drawCard(5);
                            shuffle();
                            startOfTurn = false;
                        }
                    }, 4);
                }

                this.state = State.PLAYERTURN;
                //draw 2
                break;
            case PAUSE:
                this.state = State.PLAYERTURN;
                break;
            case VICTORY:
                break;
            case DEFEAT:
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
            if (deck.getSize() > 0) {
                drawnCard = deck.drawCard();
                cards.add(drawnCard);
                stage.addActor(drawnCard);
                cardNo += 1;
            }
            else break;
        }
        shuffle();
    }

    public Deck copyDeck(Deck deck) {
        Deck newDeck = new Deck(hero.getDeck().getCurrentDeckSize(), hero.getDeck().getMaxDeckSize());
        ArrayList<Card> newCards = hero.getDeck().getCards();
        newDeck.setCards(newCards);
        return newDeck;
    }


}
