package com.gallery.websoket.dto.res;

import lombok.Data;

import java.util.Date;

/**
 * <p>
 * WsTopic 响应类
 * </p>
 */
@Data
public class WsTopicRes {

    /**
     * 推送主题ID
     */
    private Long id;

    /**
     * 推送主题名称，如直播间、视频、系统消息等标识
     */
    private String topicName;

    /**
     * 推送类型：live、video、im、system等
     */
    private String topicType;

    /**
     * 推送范围：all(所有)、user(指定用户)、group(群组)等
     */
    private String pushScope;

    /**
     * 推送状态：1-正常，0-停止推送
     */
    private Integer pushStatus;

    /**
     * 推送频率（单位：秒），0表示不限制
     */
    private Integer pushFrequency;

    /**
     * 推送内容描述，支持富文本
     */
    private String topicDescription;

    /**
     * 推送区域：根据需要限制推送区域，比如某些国家、城市
     */
    private String region;

    /**
     * 推送开始时间，支持定时推送
     */
    private Date startTime;

    /**
     * 推送结束时间，过期后不再推送
     */
    private Date endTime;

    /**
     * 创建者ID，用于权限管理和查看推送记录
     */
    private Long creatorId;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;
}
