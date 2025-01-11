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
 * 推送记录表
 * </p>
 *
 * @author xwj
 * @since 2025-01-11
 */
@Getter
@Setter
  @TableName("msg_push_record")
public class MsgPushRecord implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 自增主键
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      /**
     * 消息记录ID，关联到消息记录表
     */
      private Long messageId;

      /**
     * 推送类型：all（所有人推送），user（指定用户推送），topic（主题推送），group（群组推送）
     */
      private String pushType;

      /**
     * 推送目标的ID（如用户ID、群组ID、主题ID等），如果是群组推送，target_id 表示成员ID
     */
      private Long targetId;

      /**
     * 推送状态：sent（已成功推送），failed（推送失败），pending（待推送）,partial_failed(部分失败)
     */
      private String status;

      /**
     * 失败时的错误信息
     */
      private String errorMessage;

      /**
     * 重试次数，失败后可以重试，部分失败的在失败记录里找到重试，全部失败的在这里重试
     */
      private Integer retryCount;

      /**
     * 推送记录创建时间
     */
      private Date createTime;

      /**
     * 推送记录更新时间
     */
      private Date updateTime;
}
