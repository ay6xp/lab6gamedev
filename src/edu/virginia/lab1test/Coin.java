package edu.virginia.lab1test;

import Tweens.*;
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

    }

    public void handleEvent(Event event, Sprite sprite) {
        if (event.getEventType() == "FadeOut" && TweenEvent.tweenComplete()) {
            Tween myTween = new Tween(sprite, new TweenTransitions("linearTransition"));
            myTween.animate(TweenableParams.ALPHA, sprite.getTransparency(), 0, 2);
            TweenJuggler.getInstance().add(myTween);

        }
    }



}
