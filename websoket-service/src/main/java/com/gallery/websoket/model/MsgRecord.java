package com.gallery.websoket.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 消息记录表
 * </p>
 *
 * @author xwj
 * @since 2025-01-11
 */
@Getter
@Setter
@TableName("msg_record")
public class MsgRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 发送用户ID
     */
    private Long sendUserId;

    /**
     * 接收用户ID（如果是用户推送）
     */
    private Long receiveUserId;

    /**
     * 群组ID（如果是群组推送）
     */
    private Long groupId;

    /**
     * 主题ID（如果是主题推送）
     */
    private Long topicId;

    /**
     * 消息类型（文本、图片、视频、文件、通知等）
     */
    private String messageType;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息状态（sent-已发送，failed-发送失败，pending-待发送）
     */
    private String status;

    /**
     * 失败时的错误信息
     */
    private String errorMessage;

    /**
     * 消息创建时间
     */
    private Date createTime;

    /**
     * 消息更新时间
     */
    private Date updateTime;

    /**
     * 扩展字段
     */
    private String extensionField;

    /**
     * 推送类型：all（所有人推送），user（指定用户推送），topic（主题推送），group（群组推送）
     */
    private String messageTargetType;
}
