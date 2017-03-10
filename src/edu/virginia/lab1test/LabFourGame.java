package edu.virginia.lab1test;

import edu.virginia.engine.display.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.io.*;

import javax.sound.sampled.*;
import javax.swing.*;



import com.sun.glass.ui.Timer;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.util.GameClock;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */

public class LabFourGame extends Game {
	

	
	
	/* Create a sprite object for our game. We'll use mario */
	Sprite mario = new Sprite("Mario", "Mario.png");
	Coin myCoin = new Coin("Coin", "Coin.png");
    QuestManager myQuestManager = new QuestManager();
    Event PickedUpEvent;
    boolean complete = false;
	Event collidedEvent;
	
	private AnimatedSprite animation;
	
	
	
	
	
	
	
	
	static GameClock clock;
	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public LabFourGame() {
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
		}
		
		
	}
	
	
	
	
	
	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
	 * the screen, we need to make sure to override this method and call mario's draw method.
	 * */
	
	@Override
	public void draw(Graphics g){
		
		
		super.draw(g);
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
		
		LabFourGame game = new LabFourGame();
		
		
		game.start();
		
		
	
		

	}
}
