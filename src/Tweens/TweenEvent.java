package Tweens;

import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;

public class TweenEvent extends Event implements IEventListener{
	static boolean complete = false;
	public TweenEvent(String eventType){

	}
	public Tween getTween(){
		return null;
		
	}
	public static boolean tweenComplete(){
		return complete;
	}

	@Override
	public void handleEvent(Event event) {
		if (event.getEventType() == "FadeOut") {
			complete = true;
		}

	}

	@Override
	public void handleEvent(Event event, Sprite sprite) {

	}
}