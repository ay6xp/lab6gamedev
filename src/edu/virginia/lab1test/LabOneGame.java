package edu.virginia.lab1test;

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
import edu.virginia.engine.util.GameClock;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */

public class LabOneGame extends Game {
	

	
	JLabel label;
	/* Create a sprite object for our game. We'll use mario */
	Sprite mario = new Sprite("Mario", "Mario.png");
	//AnimatedSprite spriteSheet = new AnimatedSprite("MarioAnime","spritesheet.png");
	
	private AnimatedSprite animation;
	private BufferedImage[] notMoving;
	private BufferedImage[] walkingSprite;
	private BufferedImage[] walkingSpriteRight;
	private BufferedImage[] jumpingSprite;
	private BufferedImage[] fallingSprite;
	private BufferedImage[] walkingMario;
	private boolean facingLeft;
	
	Sprite marioBackground = new Sprite("Background","BowserCastle2.png");
	
	int xPos;
	int yPos;
	int counter;
	int lastTime;
	Clip clip;
	static int healthBar;
	
	
	
	static GameClock clock;
	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public LabOneGame() {
		super("Lab One Test Game", 800, 600);
		
		clock = new GameClock();
		
		counter = 60;
		
		try{
		
			

				 AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/sound1.wav").getAbsoluteFile());
			        clip = AudioSystem.getClip();
			        clip.open(audioInputStream);
			        clip.start();
				
					
		}catch (Exception e){
			e.printStackTrace();
		}
		
		
		animation = new AnimatedSprite("animate");
		
		
		
	}
	public void playSound(String file){
		try{
		 AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(file).getAbsoluteFile());
	        Clip clip1 = AudioSystem.getClip();
	        clip1.open(audioInputStream);
	        clip1.start();
		}catch(Exception e){
			e.printStackTrace();
		}
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
			checkTime();
			checkHealth();
			checkPosition();
			animation.update();
			
				
			
		}
		
		
		
		//System.out.println(animation.currentFrame);
		//marioWalk.update(pressedKeys);
		
			//animation.setFrames(notMoving);
			//animation.setDelay(-1);
			
		
		
		
		if(pressedKeys.contains("Up")){
			//yPos = yPos - 10;
			mario.setPositionY(mario.getPositionY() - 10);
			//animation.setFrames(walkingSprite);
			//animation.setDelay(100);
			animation.jump();
			playSound("resources/song100.wav");
			
		}
		
		if(pressedKeys.contains("Down")){
			//yPos = yPos + 10;
			mario.setPositionY(mario.getPositionY() + 10);
			animation.fall();
			//playSound("resources/song107.wav");
		}
		if(pressedKeys.contains("Right")){
			//xPos = xPos + 10;
			mario.setPositionX(mario.getPositionX() + 10);
			//animation.setFrames(walkingSprite);
			//animation.setDelay(100);
			//animation.setFrames(walkingSprite);
			//animation.setDelay(100);
			animation.walkRight();
			//animation.runRight();
			
		}
		if(pressedKeys.contains("Left")){
			//xPos = xPos - 10;
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
		
		
		
		
		
		
	}
	
	public void checkPosition(){
		
		if(mario.getPositionX()>=800-mario.getUnscaledWidth()){
			mario.setPositionX(mario.getPositionX()-10);
			
		}
		
		if(mario.getPositionX()<=-0){
			mario.setPositionX(mario.getPositionX()+10);
			
		}
		if(mario.getPositionY()>=560-mario.getUnscaledHeight()){
			mario.setPositionY(mario.getPositionY()-10);
			
		}
		if(mario.getPositionY()<=0){
			mario.setPositionY(mario.getPositionY()+10);
			
		}
		if(animation.getPositionX()>=800-animation.getUnscaledWidth()){
			animation.setPositionX(animation.getPositionX()-10);
			
		}
		
		if(animation.getPositionX()<=-0){
			animation.setPositionX(animation.getPositionX()+10);
			
		}
		if(animation.getPositionY()>=560-animation.getUnscaledHeight()){
			animation.setPositionY(animation.getPositionY()-10);
			
		}
		if(animation.getPositionY()<=0){
			animation.setPositionY(animation.getPositionY()+10);
			
		}
		
		
	}
	
	
	public void checkTime(){
		
		
		
		currentTime = (int)clock.getElapsedTime()/1000;
		
		if(currentTime == lastTime+1){
		
		counter--;
			
		}
		if(currentTime >=60){
			
			stop();
			System.out.println("Player 1 wins!");
		
		}
		
		lastTime = currentTime;
	}
	
	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
	 * the screen, we need to make sure to override this method and call mario's draw method.
	 * */
	public void checkHealth(){
		
		if(healthBar <=0){
			
			System.out.println("Player 2 Wins!");
			stop();
			
			
		}
		
	}

	@Override
	public void draw(Graphics g){
		
		
		super.draw(g);
		
		if(marioBackground!=null){
			marioBackground.draw(g);
			
		}
		
		
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 140, 30);
		
		
		g.setColor(Color.RED);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString("Time Left " + Integer.toString(counter), 20, 20);
		
		
		g.setColor(Color.BLACK);
		g.fillRect(620,0 , getWidth(), 30);
		
		g.setColor(Color.RED);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString("Health " + Integer.toString(healthBar), 670, 20);
		
		
		
		
	/* Same, just check for null in case a frame gets thrown in before Mario is initialized */
		/*
		if(mario != null &&mario.getVisibility()){
			
			mario.draw(g);
			
		}
		*/
		
		if(currentTime >=60){
			g.setColor(Color.GREEN);
			g.setFont(new Font("ARIAL", Font.PLAIN, 48));
			g.drawString("Kirby wins!!!!", 278, 150);
		
		}
		if(healthBar <=0){
			
			g.setColor(Color.GREEN);
			g.setFont(new Font("Bitstream Vera Sans",Font.PLAIN, 48));
			g.drawString("You win!!!!", 270, 155);
		
			
		}
		
		if(animation!=null && animation.getVisibility()){
			animation.draw(g);
		}
	
		
	}
	public int getWidth(){
		if(healthBar ==80){
			return 175;
		}else if(healthBar == 70){
			return 140;
			
		}else if(healthBar == 60){
			return 110;
			
		}else if(healthBar == 50){
			return 80;
			
		}else if(healthBar == 40){
			return 50;
			
		}else if(healthBar == 30){
			return 25;
			
		}else if(healthBar == 20){
			return 20;
			
		}else if(healthBar == 10){
			return 10;
		}else{
			return 0;
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
	
	if((mouseX>=minWc && mouseX<=maxWc) && (mouseY>=minHc && mouseY<=maxHc)){
		healthBar = healthBar - 10;
	}
	
	}
	
	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 * */
	
	
	static long startTime;
	int currentTime;
	
	public static void main(String[] args) {
		clock = new GameClock();
		healthBar = 80;
		LabOneGame game = new LabOneGame();
		
		
		game.start();
		
		
	
		

	}
}
