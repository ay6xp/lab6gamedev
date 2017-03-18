package edu.virginia.engine.events;

import edu.virginia.engine.display.Sprite;

public interface IEventListener {
    void handleEvent(Event event);
    void handleEvent(Event event, Sprite sprite);
}

