package edu.virginia.lab1test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import Tweens.*;
import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.util.GameClock;

public class LabSixGame extends Game {


    /* Create a sprite object for our game. We'll use mario */
    Sprite mario = new Sprite("Mario", "Mario.png");
    Coin myCoin0 = new Coin("Coin", "Coin4.png");
    Coin myCoin = new Coin("Coin", "Coin4.png");
    Coin myCoin1 = new Coin("Coin", "Coin4.png");
    Coin myCoin2 = new Coin("Coin", "Coin4.png");
    Coin myCoin3 = new Coin("Coin", "Coin4.png");
    Coin myCoin4 = new Coin("Coin", "Coin4.png");
    Coin myCoin5 = new Coin("Coin", "Coin4.png");
    Coin myCoin6 = new Coin("Coin", "Coin4.png");
    Coin myCoin7 = new Coin("Coin", "Coin4.png");
    Coin myCoin8 = new Coin("Coin", "Coin4.png");
    Coin myCoin9 = new Coin("Coin", "Coin4.png");
    TweenJuggler tweenJuggler;

    QuestManager myQuestManager = new QuestManager();
    Event PickedUpEvent;
    boolean complete = false;
    Event collidedEvent;
    Platformer platform = new Platformer("Rectangele", "platform.png");
    Platformer platform1 = new Platformer("Rectangele", "platform.png");
    Platformer platform2 = new Platformer("Rectangele", "platform.png");
    Platformer platform3 = new Platformer("Rectangele", "platform.png");
    Platformer platform4 = new Platformer("Rectangele", "platform.png");
    Platformer platform5 = new Platformer("Rectangele", "platform.png");
    boolean start = false;
    SoundManagerClass music = new SoundManagerClass();
    Sprite marioBackground = new Sprite("Background", "BowserCastle3.png");
    ArrayList<Platformer> collisionArray = new ArrayList<Platformer>();
    ArrayList<Coin> coinArray = new ArrayList<Coin>();
    boolean collision = false;
    private AnimatedSprite animation;
    Rectangle intersection;
    double defaultDistance1;
    double defaultDistance2;
    double defaultDistance3;
    double defaultDistance4;
    double defaultDistance5;
    double defaultDistance6;
    int coinCount = 0;
    ArrayList<Double> listArray = new ArrayList<Double>();
    ArrayList<Tween> coinTweenArray = new ArrayList<>();

    int offset = 1;


    static GameClock clock;

    /**
     * Constructor. See constructor in Game.java for details on the parameters given
     */
    public LabSixGame() {
        super("Lab Four Test Game", 1200, 900);
        PickedUpEvent = new TweenEvent();
        PickedUpEvent.setEventType("CoinPickedUp");
        this.addEventListener(myQuestManager, PickedUpEvent.getEventType());
        //this.addEventListener(myCoin, PickedUpEvent.getEventType());
        collidedEvent = new Event();
        collidedEvent.setEventType("CollidedEvent");
        this.addEventListener(myQuestManager, collidedEvent.getEventType());
        myCoin.setPivotX(myCoin.getUnscaledWidth() / 2);
        myCoin.setPivotY(myCoin.getUnscaledHeight() / 2);
        myCoin.setPositionY(50);
        myCoin.setPositionX(400);

        collisionArray.add(platform);
        collisionArray.add(platform1);
        collisionArray.add(platform2);
        collisionArray.add(platform3);
        collisionArray.add(platform4);
        collisionArray.add(platform5);


        coinArray.add(myCoin);
        coinArray.add(myCoin1);
        coinArray.add(myCoin3);
        coinArray.add(myCoin4);
        coinArray.add(myCoin5);
        coinArray.add(myCoin6);
        coinArray.add(myCoin7);
        coinArray.add(myCoin8);
        coinArray.add(myCoin9);


        animation = new AnimatedSprite("animate");
        platform.setPositionX(50);
        platform.setPositionY(550);


        platform1.setPositionX(150);
        platform1.setPositionY(150);

        platform2.setPositionX(350);
        platform2.setPositionY(430);

        platform3.setPositionX(600);
        platform3.setPositionY(250);

        platform4.setPositionX(820);
        platform4.setPositionY(600);

        platform5.setPositionX(670);
        platform5.setPositionY(40);

        music.playMusic("resources/bowsersound.mp3");


        animation.setPositionX(51);
        animation.setPositionY(450);
        scatterCoins();
        addListeners();

        tweenJuggler = new TweenJuggler();
        TweenTransitions animationIntro = new TweenTransitions("linearTransition");
        Tween animationTween = new Tween(animation, animationIntro);
        animationTween.animate(TweenableParams.Y, 1000, 500, 2);


        TweenTransitions coinCatch = new TweenTransitions("easeInOut");
        for (int i = 0; i < 9; i++) {
            coinTweenArray.add(new Tween(coinArray.get(i), coinCatch));
        }


        tweenJuggler.add(animationTween);


    }

    public void addListeners() {
        for (int i = 0; i < coinArray.size(); i++) {
            this.addEventListener(coinArray.get(i), PickedUpEvent.getEventType());

        }
    }

    /**
     * Engine will automatically call sprite update method once per frame and pass to us
     * the set of keys (as strings) that are currently being pressed down
     */
    @Override


    public void update(ArrayList<String> pressedKeys) {
        super.update(pressedKeys);
        if (start) {
            randomPositions();
        }

		
		/* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialispriteed */
        if (animation != null) {
            mario.update(pressedKeys);

            animation.update();
            animation.falling();
            checkCollisions(animation);
            tweenJuggler.nextFrame();

        }


        if (pressedKeys.contains("Up")) {
            start = true;
            if (animation.canJump) {
                animation.isJumping = true;
                animation.jumping();

                animation.jump();
            } else {
                animation.isJumping = false;
            }

        }

        if (pressedKeys.contains("Down")) {


            animation.fall();


            music.playSoundEffect("resources/song100.wav");

        }
        if (pressedKeys.contains("Right")) {

            mario.setPositionX(mario.getPositionX() + 10);

            animation.walkRight();


        }
        if (pressedKeys.contains("Left")) {


            animation.walkLeft();

        }

        if (pressedKeys.contains("A")) {
            mario.setScaleX(mario.getScaleX() + 0.1);
            animation.setScaleX(animation.getScaleX() + 0.1);

        }
        if (pressedKeys.contains("S")) {
            mario.setScaleY(mario.getScaleY() - 0.1);
            animation.setScaleY(animation.getScaleY() - 0.1);
        }
        if (pressedKeys.contains("Q")) {
            mario.setRotation(mario.getRotation() + 1);

            animation.setRotation(animation.getRotation() + 1);

        }
        if (pressedKeys.contains("W")) {
            mario.setRotation(mario.getRotation() - 1);

            animation.setRotation(animation.getRotation() - 1);
        }
        if (pressedKeys.contains("Z")) {
            mario.setTransparency(mario.getTransparency() + 0.01f);
            animation.setTransparency(animation.getTransparency() + 0.01f);

        }
        if (pressedKeys.contains("X")) {

            mario.setTransparency(mario.getTransparency() - 0.01f);
            animation.setTransparency(animation.getTransparency() - 0.01f);


        }
        if (pressedKeys.contains("I")) {
            mario.setPivotY(mario.getPivotY() - 3);
            animation.setPivotY(animation.getPivotY() - 3);

        }
        if (pressedKeys.contains("K")) {
            mario.setPivotY(mario.getPivotY() + 3);
            animation.setPivotY(animation.getPivotY() + 3);

        }
        if (pressedKeys.contains("J")) {
            mario.setPivotX(mario.getPivotX() - 3);
            animation.setPivotX(animation.getPivotX() - 3);

        }
        if (pressedKeys.contains("L")) {
            mario.setPivotX(mario.getPivotX() + 3);

            animation.setPivotX(animation.getPivotX() + 3);
        }
        if (pressedKeys.contains("V")) {

            mario.toggleVisibility();
            animation.toggleVisibility();


        }

        if (myCoin != null && animation != null) {
            for (int i = 0; i < coinArray.size(); i++) {
                if (!coinArray.get(i).isTouched()) {
                    if (animation.collidesWith(coinArray.get(i))) {
                        coinArray.get(i).setTouched(true);
                        coinTweenArray.get(i).animate(TweenableParams.X, coinArray.get(i).getPositionX(), 600, 2);
                        coinTweenArray.get(i).animate(TweenableParams.Y, coinArray.get(i).getPositionY(), 450, 2);
                        coinTweenArray.get(i).animate(TweenableParams.SCALE_X, coinArray.get(i).getScaleX(), coinArray.get(i).getScaleX() + 1, 2);
                        coinTweenArray.get(i).animate(TweenableParams.SCALE_Y, coinArray.get(i).getScaleX(), coinArray.get(i).getScaleY() + 1, 2);
                        //coinTweenArray.get(i).animate(TweenableParams.SCALE_X,coinArray.get(i).getScaleX(),0,5);
                        //coinTweenArray.get(i).animate(TweenableParams.SCALE_Y,coinArray.get(i).getScaleY(),0,5);
                        //dispatchEvent(PickedUpEvent, coinArray.get(i));
                        //dispatchEvent(collidedEvent);
                        tweenJuggler.add(coinTweenArray.get(i));
                        music.playSoundEffect("resources/smw_coin.wav");
                    }
                }
            }
        }
        if (myCoin != null) {
            for (int i = 0; i < coinArray.size(); i++) {
                if (coinArray.get(i).getVisibility() == true) {
                    coinCount++;
                    break;
                } else if (i == coinArray.size() - 1) {
                    complete = true;
                }

            }
        }


    }

    public void checkCollisions(AnimatedSprite sprite) {
        for (int i = 0; i < collisionArray.size(); i++) {
            collide(sprite, collisionArray.get(i));

        }

    }

    public void scatterCoins() {
        Random rand = new Random();

        for (int i = 0; i < coinArray.size(); i++) {
            int nx = rand.nextInt(100) + 1;
            int ny = rand.nextInt(100) + 1;
            coinArray.get(i).setPositionX(nx * 8);
            coinArray.get(i).setPositionY(ny * 7);

        }

    }

    public void randomPositions() {

        Random rand = new Random();
        double distanceTraveled;

        boolean forward = true;
        offset++;
        for (int i = 0; i < collisionArray.size(); i++) {
            int n = rand.nextInt(10) + 1;


            if (offset > 300) {
                offset = 1;
            }


            if (offset < 150 && offset > 1) {
                collisionArray.get(i).setPositionX(collisionArray.get(i).getPositionX() + Math.random() * n);
            }


            if (offset < 300 && offset > 150) {
                collisionArray.get(i).setPositionX(collisionArray.get(i).getPositionX() - Math.random() * n);
            }


        }


    }

    public void collide(Sprite sprite, Platformer platform) {
        if (sprite.collidesWith(platform)) {


            if (sprite.getPositionX() + sprite.getUnscaledWidth() > platform.getPositionX()
                    && sprite.getPositionY() + sprite.getUnscaledHeight() / 2 > platform.getPositionY() && sprite.getPositionY() < platform.getPositionY() + platform.getUnscaledHeight()
                    && sprite.getPositionX() < platform.getPositionX()) {
                sprite.setPositionX(platform.getPositionX() - sprite.getUnscaledWidth());
            } else if (sprite.getPositionX() <= platform.getPositionX() + platform.getUnscaledWidth() && sprite.getPositionY() + sprite.getUnscaledHeight() / 2 > platform.getPositionY() && sprite.getPositionY() < platform.getPositionY() + platform.getUnscaledHeight()
                    && sprite.getPositionX() + sprite.getUnscaledWidth() > platform.getPositionX() + platform.getUnscaledWidth()) {
                sprite.setPositionX(platform.getPositionX() + platform.getUnscaledWidth());
            } else if (sprite.getPositionX() + sprite.getUnscaledWidth() / 2 < platform.getPositionX() + platform.getUnscaledWidth() && sprite.getPositionX() > platform.getPositionX()) {
                if (sprite.getPositionY() < platform.getPositionY() + platform.getUnscaledHeight() && sprite.getPositionY() + sprite.getUnscaledHeight() > platform.getPositionY() + platform.getUnscaledHeight()) {
                    sprite.setPositionY(platform.getPositionY() + platform.getUnscaledHeight());
                } else if (sprite.getPositionY() + sprite.getUnscaledHeight() > platform.getPositionY() && sprite.getPositionY() < platform.getPositionY()) {
                    sprite.setPositionY(platform.getPositionY() - sprite.getUnscaledHeight());
                }

            }


        }
    }


    @Override
    public void draw(Graphics g) {


        super.draw(g);

        if (marioBackground != null) {
            marioBackground.draw(g);

        }
        if (animation != null) {
            animation.draw(g);

        }

        if (myCoin != null) {
            for (int i = 0; i < coinArray.size(); i++) {
                if (coinArray.get(i) != null && coinArray.get(i).getVisibility() != false) {

                    coinArray.get(i).draw(g);

                }

            }
        }
        g.setFont(new Font("ARIAL", Font.PLAIN, 48));
        if (complete == true) {
            g.drawString("Quest is complete!", 400, 40);

        }


        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 140, 30);


        g.setColor(Color.RED);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));


        g.setColor(Color.BLACK);


        g.setColor(Color.RED);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        if (platform != null) {


            for (int i = 0; i < collisionArray.size(); i++) {
                collisionArray.get(i).draw(g);

            }


        }


    }


    @Override
    public void mouseClicked(MouseEvent e) {

        double minWc = animation.getCenterX() - (animation.getUnscaledWidth() / 2);
        double minHc = animation.getCenterY() - animation.getUnscaledHeight() / 2;

        double maxWc = animation.getCenterX() + (animation.getUnscaledWidth() / 2);
        double maxHc = animation.getCenterY() + animation.getUnscaledHeight() / 2 + 20;

        double mouseX = e.getX();
        double mouseY = e.getY();


    }

    /**
     * Quick main class that simply creates an instance of our game and starts the timer
     * that calls update() and draw() every frame
     */


    static long startTime;
    int currentTime;

    public static void main(String[] args) {
        clock = new GameClock();

        LabSixGame game = new LabSixGame();


        game.start();


    }
}
