package edu.virginia.lab1test;

import com.sun.javafx.geom.Rectangle;

import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;

public class Platformer extends Sprite implements IEventListener {
	
	
	public Platformer(String id, String imageFileName) {
        super(id, imageFileName);
    }


    @Override
    public void handleEvent(Event event) {
        if (event.getEventType() == "Collison") {
            this.setVisibleState(false);
        }
    }
   
    
    
    
}
