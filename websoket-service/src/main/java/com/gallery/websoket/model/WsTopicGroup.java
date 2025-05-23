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
 * 群组订阅表
 * </p>
 *
 * @author xwj
 * @since 2025-01-10
 */
@Getter
@Setter
  @TableName("ws_topic_group")
public class WsTopicGroup implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 自增主键
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      /**
     * 推送主题ID
     */
      private Long topicId;

      /**
     * 群组ID
     */
      private Long groupId;

      /**
     * 订阅状态：1-已订阅，0-已取消订阅
     */
      private Boolean status;

      /**
     * 订阅时间
     */
      private Date subscribedAt;

      /**
     * 取消订阅时间
     */
      private Date unsubscribedAt;
}
