package com.gallery.websoket.service;

import com.gallery.websoket.model.MyWebSocketMessage;

public interface MessagePushStrategy {
    void sendMessage(MyWebSocketMessage message);
}
