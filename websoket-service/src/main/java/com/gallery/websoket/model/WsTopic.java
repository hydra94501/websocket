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
 * 推送主题表
 * </p>
 *
 * @author xwj
 * @since 2025-01-10
 */
@Getter
@Setter
  @TableName("ws_topic")
public class WsTopic implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 自增主键
     */
        @TableId(value = "id", type = IdType.AUTO)
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
      private Boolean pushStatus;

      /**
     * 推送频率（单位：秒），0 表示不限制
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
     * 创建时间
     */
      private Date createdAt;

      /**
     * 更新时间
     */
      private Date updatedAt;

      /**
     * 创建者ID，用于权限管理和查看推送记录
     */
      private Long creatorId;
}
