package edu.virginia.lab1test;


import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.EventDispatcher;
import edu.virginia.engine.events.IEventDispatcher;
import edu.virginia.engine.events.IEventListener;

/**
 * Created by Brigadoon on 2/17/2017.
 */
public class QuestManager implements IEventListener {
    @Override
    public void handleEvent(Event event) {
        if (event.getEventType() == "CoinPickedUp") {
            //System.out.println("Quest is Complete");
        }
        if(event.getEventType()== "CollidedEvent"){
        	
        }
    }

	@Override
	public void handleEvent(Event event, Sprite sprite) {
		// TODO Auto-generated method stub
		
	}



}
