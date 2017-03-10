package edu.virginia.engine.display;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.sun.glass.ui.Timer;

import edu.virginia.engine.util.GameClock;

public class AnimatedSprite extends Sprite {
	
	//private int currentFrame = 0;
	private int startIndex;
	private int endIndex;
	private int frameCount;
	private int animationSpeed = 1;
	private String animateType;
	boolean playing = true;
	private GameClock clock;

	private AnimatedSprite animation;
	private BufferedImage[] notMoving;
	private BufferedImage[] walkingSprite;
	private BufferedImage[] jumpingSprite;
	private BufferedImage[] fallingSprite;
	private BufferedImage[] walkingMario;
	private BufferedImage[] walkingSpriteRight;
	private boolean facingLeft;
	
	public BufferedImage[] frames;
	public int currentFrame;
	private long startTime;
	private long delay;
	
	int gravity = 10;
	long terminalSpeed = 100;
	
	public AnimatedSprite(String id){
		super(id);
		try{
			walkingSprite = new BufferedImage[6];
			notMoving = new BufferedImage[1];
			jumpingSprite = new BufferedImage[1];
			fallingSprite = new BufferedImage[1];
			walkingSpriteRight = new BufferedImage[6];
		/*
		 * walkingSprite = new BufferedImage[1];
			notMoving = new BufferedImage[1];
			notMoving[0] = ImageIO.read(new File("resources/mario_walk0.png"));
			walkingSprite[0] = ImageIO.read(new File("resources/mario_walk1.PNG"));
		*/	
			notMoving[0] = ImageIO.read(new File("resources/kirbyidle4.0.png"));
			jumpingSprite[0] = ImageIO.read(new File("resources/kirbyjump4.0.png"));
			fallingSprite[0] = ImageIO.read(new File("resources/kirbyfall4.0.png"));
			BufferedImage image = ImageIO.read(new File("resources/kirbywalk10.0.png"));
			BufferedImage image1 = ImageIO.read(new File("resources/kirbywalk11.0.png"));
			for(int i = 0; i < walkingSprite.length;i++){
				walkingSprite[i] = image.getSubimage(i*66,0,65,60);
				walkingSpriteRight[i] = image1.getSubimage(i*66,0,65,60);
				
			}
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		
		setFrames(notMoving);
		setDelay(500);
		
		setPositionX(250);
		setPositionY(180);
		
	}
	
	public AnimatedSprite(String id, String fileName){
		super(id,fileName);
		
		
			
				
	}
	public void setFrames(BufferedImage[] images){
		frames = images;
		if(currentFrame>=frames.length){
			currentFrame = 0;
			
		}
		
	}
	//set speed with this method
	public void setDelay(long d){
		delay = d;
	}
	
	
	public void walkRight(){
		setFrames(walkingSpriteRight);
		setDelay(100);
		
		
		
		setPositionX(getPositionX()+10);
		
		
		
		
	}
	public void walkLeft(){
		setFrames(walkingSprite);
		setDelay(100);
		setPositionX(getPositionX()-10);
	}
	
	public void runRight(){
		setFrames(walkingSprite);
		setDelay(1);
		setPositionX(getPositionX()+10);
	}
	public void jump(){
		setFrames(jumpingSprite);
		setDelay(500);
		setPositionY(getPositionY()-20);
	}
	public void fall(){
		setFrames(fallingSprite);
		setDelay(500);
		setPositionY(getPositionY()+20);
		
		
		
	}
	
	public long getDelay(){
		return delay;
	}
	
	@Override
	public void update(ArrayList<String> pressedKeys){
	super.update(pressedKeys);
	

	}
	public void update(){
		if(delay == -1){
			return;
		}
		long elapsed = (System.nanoTime()-startTime)/1000000;
		if(elapsed> delay){
			currentFrame++;
			startTime = System.nanoTime();
			
		}
		if(currentFrame == frames.length){
			currentFrame = 0;
			setFrames(notMoving);
			setDelay(-1);
		}
	
		super.setImage(frames[currentFrame]);
	}
	
	public BufferedImage getImage(){
		return frames[currentFrame];
	}
	
	@Override
	public void draw(Graphics g)
	{
	/* Call the super draw method in DisplayObject class */
	super.draw(g);
	
	}
	

}
