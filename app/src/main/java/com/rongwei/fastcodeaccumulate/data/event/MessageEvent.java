package com.rongwei.fastcodeaccumulate.data.event;


public class MessageEvent {
    private EventTag eventTag;
    private Object object;

    public MessageEvent(EventTag eventTag) {
        this(eventTag, null);
    }

    public MessageEvent(EventTag eventTag, Object object) {
        this.eventTag = eventTag;
        this.object = object;
    }

    public EventTag getEventTag() {
        return eventTag;
    }


    public Object getObject() {
        return object;
    }
}
