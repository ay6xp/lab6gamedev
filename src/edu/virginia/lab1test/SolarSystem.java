package edu.virginia.lab1test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import java.math.*;
public class SolarSystem extends Game{
	Sprite secret = new Sprite("invisible");
	Sprite sun = new Sprite(secret,"sun","newsun.png");
	Sprite earth = new Sprite(sun,"earth","newearth.jpg");
	Sprite mercury = new Sprite(sun,"mercury","mercury.jpg");
	Sprite venus = new Sprite(sun,"venus","venus.jpg");
	Sprite moon = new Sprite(earth,"moon","moon.jpg");
	Sprite mars = new Sprite(sun,"mars","mars.jpg");
	Sprite jupiter = new Sprite(sun,"jupiter","jupiter.jpg");
	Sprite saturn = new Sprite(sun,"saturn","saturn.png");
	Sprite uranus = new Sprite(sun,"uranus","uranus.png");
	Sprite neptune = new Sprite(sun,"neptune","neptune.jpg");
	Sprite pluto = new Sprite(sun,"pluto","pluto.jpg");
	Sprite invisibleSun1 = new Sprite("sun1");
	
	Sprite invisibleSun2 = new Sprite("sun2");
	Sprite invisibleSun3 = new Sprite("sun3");
	Sprite invisibleSun4 = new Sprite("sun4");
	Sprite invisibleSun5 = new Sprite("sun5");
	Sprite invisibleSun6 = new Sprite("sun6");
	Sprite invisibleSun7 = new Sprite("sun7");
	Sprite invisibleSun8 = new Sprite("sun8");
	
	
	
	double defaultRotation;
	
	public SolarSystem(String gameId, int width, int height) {
		
		super(gameId, width, height);
		// TODO Auto-generated constructor stub
		secret.addChild(sun);
		secret.setPositionX(660);
		secret.setPositionY(120);
		sun.setPositionX(-180);
		sun.setPositionY(0);
		sun.setPivotX(sun.getUnscaledWidth()/2);
		sun.setPivotY(sun.getUnscaledHeight()/2);
		
		sun.addChild(mercury);
		sun.addChild(earth);
		sun.addChild(venus);
		earth.addChild(moon);
		sun.addChild(mars);
		sun.addChild(jupiter);
		sun.addChild(saturn);
		sun.addChild(uranus);
		sun.addChild(neptune);
		sun.addChild(pluto);
		
		
		
		/*
		sun.addChild(invisibleSun1);
		sun.addChild(invisibleSun2);
		sun.addChild(invisibleSun3);
		//sun.addChild(invisibleSun4);
		invisibleSun1.addChild(mercury);
		invisibleSun2.addChild(venus);
		invisibleSun3.addChild(earth);
		earth.addChild(moon);
		*/
		earth.setPositionX(-60);
		venus.setPositionX(10);
		mercury.setPositionX(60);
		moon.setPositionX(-30);
		mars.setPositionX(-150);
		jupiter.setPositionX(-250);
		saturn.setPositionX(580);
		saturn.setPositionY(300);
		uranus.setPositionX(730);
		neptune.setPositionX(770);
		neptune.setPositionY(220);
		pluto.setPositionX(830);
		pluto.setPositionY(150);
		
		
		adjustPivot(earth,60,0);
		adjustPivot(mercury,-30,0);
		adjustPivot(venus,-10,0);
		adjustPivot(moon,30,0);
		adjustPivot(mars,150,0);
		adjustPivot(jupiter,250,0);
		adjustPivot(saturn,-580,-300);
		adjustPivot(uranus,-730,0);
		adjustPivot(neptune,-770,-220);
		adjustPivot(pluto,-830,-150);
		defaultRotation = earth.getRotation();
		
		
		/*
		invisibleSun3.setPivotX(invisibleSun3.getUnscaledWidth()/2);
		mercury.setPivotX(mercury.getUnscaledWidth()/2-2);
		mercury.setPivotY(mercury.getUnscaledHeight()/2-2);
		
		venus.setPivotX(venus.getUnscaledWidth()/2-2);
		venus.setPivotY(venus.getUnscaledHeight()/2-2);
		moon.setPivotX(earth.getUnscaledWidth() + 10);
		moon.setPivotY(earth.getUnscaledHeight()/2);
		
		*/
	}
	public void adjustPivot(Sprite obj,int offsetX,int offsetY){
	obj.setPivotX(obj.getParent().getUnscaledWidth()/2 + offsetX);	
	obj.setPivotY(obj.getParent().getUnscaledHeight()/2 + offsetY);	
	}
	
	public void draw (Graphics g){
		super.draw(g);
		/*if(sun!=null){
		sun.draw(g);
	
		}*/
		if(secret!=null){
			secret.draw(g);
			
		}
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLUE);
		g2d.drawOval(660, 300, 10, 10);
		
		
	}
	
	public void update(ArrayList<String> pressedKeys) {
		super.update(pressedKeys);
		if(sun!=null){
			sun.update(pressedKeys);
			sun.setRotation(sun.getRotation()+0.01);
			
			
			
		}
		
		
		
	
			if(pressedKeys.contains("A")){
			
			movePlanet(earth,0.01);
			adjustEllipse(earth);
			movePlanet(mercury,0.03);
			movePlanet(venus,0.02);
			movePlanet(moon,0.05);
			movePlanet(mars,0.009);
			movePlanet(jupiter,0.006);
			movePlanet(saturn,0.004);
			movePlanet(uranus,0.003);
			movePlanet(neptune,0.002);
			movePlanet(pluto,0.001);
			
			sun.setRotation(sun.getRotation()+0.01);
			
			
		}
		if(pressedKeys.contains("S")){
			movePlanet(earth,-0.01);
			movePlanet(mercury,-0.03);
			movePlanet(venus,-0.02);
			movePlanet(moon,-0.05);
			movePlanet(mars,-0.009);
			movePlanet(jupiter,-0.006);
			movePlanet(saturn,-0.004);
			movePlanet(uranus,-0.003);
			movePlanet(neptune,-0.002);
			movePlanet(pluto,-0.001);
			
			sun.setRotation(sun.getRotation()-0.02);
			
			
		}
		
		if(pressedKeys.contains("Q")){
			secret.setScaleX(secret.getScaleX()+0.1);
			secret.setScaleY(secret.getScaleY()+0.1);
			
			
		}
		if(pressedKeys.contains("W")){
			secret.setScaleX(secret.getScaleX()-0.1);
			secret.setScaleY(secret.getScaleY()-0.1);
			
		}
		if(pressedKeys.contains("Up")){
			secret.setPositionY(secret.getPositionY()+3);
		}
		if(pressedKeys.contains("Down")){
			secret.setPositionY(secret.getPositionY()-3);
		}
		if(pressedKeys.contains("Right")){
			secret.setPositionX(secret.getPositionX()-3);
		}
		if(pressedKeys.contains("Left")){
			secret.setPositionX(secret.getPositionX()+3);
		}
		
		
		
		
	}
	public void adjustEllipse(Sprite obj){
		
		//5.7
		if(obj.getRotation()>=5.7){
			obj.setRotation(-0.57);
		}
		/*
		if(obj.getRotation()>=1.24 && obj.getRotation()<=2.28){
			obj.setPositionX(obj.getPositionX()+1);
		}
		if(obj.getRotation()>2.28 && obj.getRotation()<=3.259){
			obj.setPositionX(obj.getPositionX()-1);
		}
		*/
		
	}
	
	public void movePlanet(Sprite object,double degrees){
		object.setRotation(object.getRotation()+degrees);
		
	}

	public static void main(String args[]){
		SolarSystem system = new SolarSystem("game",1600,1000);
		
		system.start();
	}

}
