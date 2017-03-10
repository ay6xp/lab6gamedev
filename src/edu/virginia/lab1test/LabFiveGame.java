package edu.virginia.lab1test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.util.GameClock;
public class LabFiveGame extends Game {
	

	
	
	/* Create a sprite object for our game. We'll use mario */
	Sprite mario = new Sprite("Mario", "Mario.png");
	Coin myCoin = new Coin("Coin", "Coin.png");
    QuestManager myQuestManager = new QuestManager();
    Event PickedUpEvent;
    boolean complete = false;
	Event collidedEvent;
	Platformer platform = new Platformer("Rectangele", "platform.png");
	Platformer platform1 = new Platformer("Rectangele", "platform.png");
	Platformer platform2 = new Platformer("Rectangele", "platform.png");
	SoundManagerClass music = new SoundManagerClass();
	Sprite marioBackground = new Sprite("Background","BowserCastle2.png");
	
	private AnimatedSprite animation;
	
	
	
	
	
	
	
	
	static GameClock clock;
	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public LabFiveGame() {
		super("Lab Four Test Game", 800, 600);
		PickedUpEvent = new Event();
        PickedUpEvent.setEventType("CoinPickedUp");
        this.addEventListener(myQuestManager, PickedUpEvent.getEventType());
        this.addEventListener(myCoin, PickedUpEvent.getEventType());
        collidedEvent = new Event();
        collidedEvent.setEventType("CollidedEvent");
        this.addEventListener(myQuestManager, collidedEvent.getEventType());
        myCoin.setPivotX(myCoin.getUnscaledWidth()/2);
        myCoin.setPivotY(myCoin.getUnscaledHeight()/2);
        myCoin.setPositionY(250);
        myCoin.setPositionX(250);

		
		
		
		
		
		animation = new AnimatedSprite("animate");
		platform.setPositionX(50);
		platform.setPositionY(300);
		
		
		platform1.setPositionX(300);
		platform1.setPositionY(150);
		
		platform2.setPositionX(500);
		platform2.setPositionY(400);
		

		animation.setPositionX(0);
		animation.setPositionY(480);
		music.playMusic("resources/bowsersound.mp3");
		

		animation.setPositionX(50);
		animation.setPositionY(220);

		
	}
	
	/**
	 * Engine will automatically call this update method once per frame and pass to us
	 * the set of keys (as strings) that are currently being pressed down
	 * */
	@Override
	public void update(ArrayList<String> pressedKeys){
		super.update(pressedKeys);
		
		/* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
		if(mario != null && animation.getImage()!=null){
			mario.update(pressedKeys);
			
			animation.update();
			
				
			
		}
		
		
		
		
		
		
		if(pressedKeys.contains("Up")){
			
			mario.setPositionY(mario.getPositionY() - 10);
			
			animation.jump();
			
			
		}
		
		if(pressedKeys.contains("Down")){
			
			mario.setPositionY(mario.getPositionY() + 10);
			animation.fall();
			music.playSoundEffect("resources/song100.wav");
			
		}
		if(pressedKeys.contains("Right")){
		
			mario.setPositionX(mario.getPositionX() + 10);
		
			animation.walkRight();
		
			
		}
		if(pressedKeys.contains("Left")){
			
			mario.setPositionX(mario.getPositionX() - 10);
			animation.walkLeft();
		}
		
		if(pressedKeys.contains("A")){
			mario.setScaleX(mario.getScaleX()+ 0.1);
			animation.setScaleX(animation.getScaleX()+ 0.1);
			
		}
		if(pressedKeys.contains("S")){
			mario.setScaleY(mario.getScaleY()- 0.1);
			animation.setScaleY(animation.getScaleY()- 0.1);
		}
		if(pressedKeys.contains("Q")){
			mario.setRotation(mario.getRotation() + 1);
			
			animation.setRotation(animation.getRotation() + 1);
			
		}
		if(pressedKeys.contains("W")){
			mario.setRotation(mario.getRotation() - 1);
			
			animation.setRotation(animation.getRotation() - 1);
		}
		if(pressedKeys.contains("Z")){
			mario.setTransparency(mario.getTransparency()+0.01f);
			animation.setTransparency(animation.getTransparency()+0.01f);
			
		}
		if(pressedKeys.contains("X")){
			
			mario.setTransparency(mario.getTransparency()-0.01f);
			animation.setTransparency(animation.getTransparency()-0.01f);
			
			
		}
		if(pressedKeys.contains("I")){
			mario.setPivotY(mario.getPivotY()-3);
			animation.setPivotY(animation.getPivotY()-3);
			
		}
		if(pressedKeys.contains("K")){
			mario.setPivotY(mario.getPivotY()+3);
			animation.setPivotY(animation.getPivotY()+3);
						
		}
		if(pressedKeys.contains("J")){
			mario.setPivotX(mario.getPivotX()-3);
			animation.setPivotX(animation.getPivotX()-3);
			
		}
		if(pressedKeys.contains("L")){
			mario.setPivotX(mario.getPivotX()+3);
			
			animation.setPivotX(animation.getPivotX()+3);
		}
		if(pressedKeys.contains("V")){
		
			mario.toggleVisibility();
			animation.toggleVisibility();
			

		}
		
		if(animation!=null &&  myCoin!=null){
			/*
		  if ((animation.getPositionX()>myCoin.getPositionX() && animation.getPositionX()<=myCoin.getPositionX()+myCoin.getUnscaledWidth() && (animation.getPositionY()>myCoin.getPositionY() && animation.getPositionY()<=myCoin.getPositionY()+myCoin.getUnscaledHeight()))){
	            this.dispatchEvent(PickedUpEvent);
	            complete = true;


	        }
	        */
			
			if(animation.collidesWith(myCoin)){
				 this.dispatchEvent(PickedUpEvent);
				 this.dispatchEvent(collidedEvent);
		            complete = true;
			}
			animation.collide(platform);
			animation.collide(platform1);
			animation.collide(platform2);

		}

		
	}
	
	
	
	
	
	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
	 * the screen, we need to make sure to override this method and call mario's draw method.
	 * */
	
	@Override
	public void draw(Graphics g){
		
		
		super.draw(g);

		if(marioBackground!=null){
			marioBackground.draw(g);
			
		}
		 if(myCoin != null && myCoin.getVisibility()!=false){
			 myCoin.draw(g);
			}
	        g.setFont(new Font("ARIAL",Font.PLAIN, 48));
	        if(complete == true){
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
		if(platform!=null){
		platform.draw(g);
		
		platform1.draw(g);
		
		platform2.draw(g);
		}
		
	
		
	
		
		if(animation!=null && animation.getVisibility()){
			animation.draw(g);
		}
	
		
	}
	
	
	@Override
	 public void mouseClicked(MouseEvent e) {
	
	double minWc = animation.getCenterX()- (animation.getUnscaledWidth()/2);
	double minHc = animation.getCenterY() - animation.getUnscaledHeight()/2;
	
	double maxWc = animation.getCenterX()+ (animation.getUnscaledWidth()/2);
	double maxHc = animation.getCenterY() + animation.getUnscaledHeight()/2 +20;
	
	double mouseX = e.getX();
	double mouseY = e.getY();
	
	
	
	}
	
	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 * */
	
	
	static long startTime;
	int currentTime;
	
	public static void main(String[] args) {
		clock = new GameClock();
		
		LabFiveGame game = new LabFiveGame();
		
		
		game.start();
		
		
	
		

	}
}