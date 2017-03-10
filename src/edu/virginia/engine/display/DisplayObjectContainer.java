package edu.virginia.engine.display;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class DisplayObjectContainer extends DisplayObject{
	private DisplayObjectContainer parent;
	private ArrayList<DisplayObjectContainer> list = new ArrayList<DisplayObjectContainer>();
	

	
	public DisplayObjectContainer(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	

	
	public DisplayObjectContainer(String id, String name){
		super(id,name);
		
	}
	public DisplayObjectContainer(DisplayObjectContainer parent,String id, String name){
		super(id,name);
		this.parent = parent;
		
	}
	public void addChild(DisplayObjectContainer obj){
		list.add(obj);
		
		
	}
	public void addChildAtIndex(int index, DisplayObjectContainer obj){
		list.add(index, obj);
				
	}
	
	public void removeChild(DisplayObjectContainer obj){
		list.remove(obj);
		
	}
	public void removeByIndex(int index){
		list.remove(index);
		
	}
	public void removeAll(){
		list.clear();
		
	}
	public DisplayObjectContainer getParent(){
		return parent;
		
	}
	public boolean contains(DisplayObjectContainer obj){
		if(list.contains(obj)){
			return true;
		}
		return false;
	}
	public DisplayObject getChild(int index){
		
		return list.get(index);
	}
	public DisplayObject getChild(String id){
		for(int i = 0; i <list.size();i++){
			if(list.get(i).getId()== id){
				return list.get(i);
				
			}
			
			
		}
		return null;
		
	}
	
	
	public ArrayList getChildren(){
				
		return list;
		
	}
	
	public void draw(Graphics g){
		super.draw(g);
		
		Graphics2D g2d = (Graphics2D) g;
		applyTransformations(g2d);
		
		for(int i = 0; i <list.size();i++){
			list.get(i).draw(g2d);
		
		}
		reverseTransformations(g2d);
		
	}
	
	
	public void update(ArrayList<String> pressedKeys) {
		super.update(pressedKeys);
	
	}
	
	
	
}
