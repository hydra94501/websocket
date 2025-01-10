package com.gallery.websoket.enums;

public enum MessageTargetType {
    USER("user"),
    GROUP("group"),
    ALL("all"),
    TOPIC("topic");

    private final String type;

    MessageTargetType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
