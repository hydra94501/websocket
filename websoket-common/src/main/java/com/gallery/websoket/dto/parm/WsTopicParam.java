package com.gallery.websoket.dto.parm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * WsTopic 请求参数类
 * </p>
 */
@Data
@ApiModel(description = "推送Topic请求参数")
public class WsTopicParam {

    @ApiModelProperty(value = "推送主题名称，如直播间、视频、系统消息等标识", required = true)
    private String topicName;

    @ApiModelProperty(value = "推送类型：live、video、im、system等", required = true, allowableValues = "live, video, im, system")
    private String topicType;

    @ApiModelProperty(value = "推送范围：all(所有)、user(指定用户)、group(群组)等", required = true, allowableValues = "all, user, group")
    private String pushScope;

    @ApiModelProperty(value = "推送状态：1-正常，0-停止推送", example = "1", allowableValues = "0, 1")
    private Integer pushStatus;

    @ApiModelProperty(value = "推送频率（单位：秒），0表示不限制", example = "0")
    private Integer pushFrequency;

    @ApiModelProperty(value = "推送内容描述，支持富文本")
    private String topicDescription;

    @ApiModelProperty(value = "推送区域：根据需要限制推送区域，比如某些国家、城市")
    private String region;

    @ApiModelProperty(value = "推送开始时间，支持定时推送", example = "2025-01-01T10:00:00")
    private Date startTime;

    @ApiModelProperty(value = "推送结束时间，过期后不再推送", example = "2025-01-01T20:00:00")
    private Date endTime;

    @ApiModelProperty(value = "创建者ID，用于权限管理和查看推送记录", example = "123")
    private Long creatorId;
}