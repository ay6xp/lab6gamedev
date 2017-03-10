package edu.virginia.engine.display;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Nothing in this class (yet) because there is nothing specific to a Sprite yet that a DisplayObject
 * doesn't already do. Leaving it here for convenience later. you will see!
 * */
public class Sprite extends DisplayObjectContainer {
	 private static BufferedImage spriteSheet;
	 String imageName;
	

	public Sprite(String id) {
		super(id);
	}

	public Sprite(String id, String imageFileName) {
		super(id, imageFileName);
		
		
	}
	public Sprite(DisplayObjectContainer parent,String id, String imageFileName) {
		super(parent,id, imageFileName);
		
		
	}
	
	public Sprite(String id, String imageFileName,BufferedImage spritesheet) {
		super(id, imageFileName);
		
	}
	
	@Override
	public void update(ArrayList<String> pressedKeys) {
		super.update(pressedKeys);
	
	}
	public BufferedImage loadingSprite(String file){
		
		BufferedImage sprite = null;
		sprite = readImage(file);
		
		return sprite;
	}
	
	 public  BufferedImage getSprite(int xGrid, int yGrid) {

	        if (spriteSheet == null) {
	            spriteSheet = loadingSprite("AnimationSpriteSheet");
	        }

	        return spriteSheet.getSubimage(0,0,0,0);
	    }

	}
	
	

	
	
	
