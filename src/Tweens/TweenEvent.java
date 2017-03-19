package Tweens;

import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;

public class TweenEvent extends Event implements IEventListener{
	
	TweenEvent(String eventType,Tween tween){
		
	}
	public Tween getTween(){
		return null;
		
	}

	@Override
	public void handleEvent(TweenEvent event) {

	}

	@Override
	public void handleEvent(Event event, Sprite sprite) {
		if (event.getEventType() == "CoinPickedUp") {
			sprite.setVisibleState(false);

		}
	}
}