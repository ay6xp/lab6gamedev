package edu.virginia.engine.events;

import java.util.ArrayList;

public class EventDispatcher implements IEventDispatcher {
    ArrayList<IEventListener> eventlist =  new ArrayList<>();
    @Override
    public void addEventListener(IEventListener listener, String eventType) {
        eventlist.add(listener);
    }

    @Override
    public void removeEventListener(IEventListener listener, String eventType) {
        eventlist.remove(listener);
    }

    @Override
    public void dispatchEvent(Event event) {
        for (int i =0; i<eventlist.size();i++){
            eventlist.get(i).handleEvent(event);
        }
    }

    @Override
    public boolean hasEventListener(IEventListener listener, String eventType) {
        if (eventlist.contains(listener)){return true;}
        return false;
    }
}
