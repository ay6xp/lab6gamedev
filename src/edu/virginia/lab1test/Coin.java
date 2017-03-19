package edu.virginia.lab1test;

import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventDispatcher;
import edu.virginia.engine.events.IEventListener;

import java.util.ArrayList;
import java.util.EventListener;


public class Coin extends Sprite implements IEventListener {

    public Coin(String id, String imageFileName) {
        super(id, imageFileName);
    }
    private boolean touched = false;

    public boolean isTouched() {
        return touched;
    }

    public void setTouched(boolean touched) {
        this.touched = touched;
    }

    @Override
    public void handleEvent(Event event) {
        if (event.getEventType() == "CoinPickedUp") {
          this.setVisibleState(false);
            
        }
    }
    
    public void handleEvent(Event event, Sprite sprite) {
        if (event.getEventType() == "CoinPickedUp") {
         sprite.setVisibleState(false);
            
        }
    }
    
}
