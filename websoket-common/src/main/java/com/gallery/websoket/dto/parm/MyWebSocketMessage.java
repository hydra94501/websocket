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
     * 外部系统名称，如live_sys、video_sys、im_sys等
     */
    @ApiModelProperty(value = "外部系统名称，如live_sys、video_sys、im_sys等", required = true)
    private String systemName;
    /**
     * 发送用户ID
     */
    @ApiModelProperty(value = "发送用户的ID")
    private String sendUserId;

    /**
     * 推送类型：all（所有人推送），user（指定用户推送），topic（主题推送），group（群组推送）
     */
    @ApiModelProperty(value = "推送类型：all（所有人推送），user（指定用户推送），topic（主题推送），group（群组推送）")
    private String messageTargetType;

    /**
     * 群组ID（如果是群组推送）
     */
    @ApiModelProperty(value = "群组ID（如果是群组推送）")
    private Long groupId;

    /**
     * 主题ID（如果是主题推送）
     */
    @ApiModelProperty(value = "主题ID（如果是主题推送）")
    private Long topicId;

    /**
     * 接收用户ID
     */
    @ApiModelProperty(value = "接收消息的用户ID")
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
