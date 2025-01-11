package com.gallery.websoket.dto.parm;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "WebSocket_message消息体，用于封装发送的消息内容")
public class MyWebSocketMessage implements Serializable {

    /**
     * 发送用户昵称
     */
    @ApiModelProperty(value = "发送用户的昵称", required = true)
    private String sendUserNickname;

    /**
     * 发送用户ID
     */
    @ApiModelProperty(value = "发送用户的ID", required = true)
    private String sendUserId;

    /**
     * 接收用户ID
     */
    @ApiModelProperty(value = "接收消息的用户ID", required = true)
    private String receiveUserId;

    /**
     * 消息类型
     * 由 ProjectConstant.MessageType 定义
     */
    @ApiModelProperty(value = "消息类型", notes = "由 ProjectConstant.MessageType 定义", required = true)
    private Byte messageType;

    /**
     * 消息内容
     */
    @ApiModelProperty(value = "消息内容", required = true)
    private String content;

    /**
     * 消息时间
     */
    @ApiModelProperty(value = "消息创建时间", example = "2025-01-10T10:00:00Z", required = true)
    private String createTime;


    /**
     * 消息链接
     */
    @ApiModelProperty(value = "消息的URL链接", required = false)
    private String url;

    /**
     * 扩展字段，用户自定义
     */
    @ApiModelProperty(value = "扩展字段，用于存储用户自定义的额外信息", required = false)
    private JSONObject extensionField;

}
