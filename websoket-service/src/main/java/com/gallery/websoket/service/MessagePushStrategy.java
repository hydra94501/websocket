package com.gallery.websoket.service;

import com.gallery.websoket.dto.parm.MyWebSocketMessage;
import com.gallery.websoket.dto.res.MessagePushRes;

public interface MessagePushStrategy {
    MessagePushRes sendMessage(MyWebSocketMessage message);
}
