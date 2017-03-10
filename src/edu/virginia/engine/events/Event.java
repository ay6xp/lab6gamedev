package edu.virginia.engine.events;

public class Event {
    String eventType;
    IEventDispatcher source;

    public void setEventType(String eventType){this.eventType = eventType;}
    public String getEventType(){return this.eventType;}

    public void setSource(IEventDispatcher source){this.source = source;}
    public IEventDispatcher getSource(){return this.source;}
}

