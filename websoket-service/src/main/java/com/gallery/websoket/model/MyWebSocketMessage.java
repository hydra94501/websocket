package com.gallery.websoket.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 消息体类，用于封装 WebSocket 消息的内容。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyWebSocketMessage implements Serializable {

    /**
     * 发送用户昵称
     */
    private String sendUserNickname;

    /**
     * 发送用户ID
     */
    private Long sendUserId;

    /**
     * 接收用户ID
     */
    private Long receiveUserId;

    /**
     * 消息类型
     * 由 ProjectConstant.MessageType 定义
     */
    private Byte messageType;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息时间
     */
    private String createTime;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息链接
     */
    private String url;

    /**
     * 扩展字段，用户自定义
     */
    private Object extensionField;


}