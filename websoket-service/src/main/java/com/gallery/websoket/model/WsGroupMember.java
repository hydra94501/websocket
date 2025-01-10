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
 * 群组成员表
 * </p>
 *
 * @author xwj
 * @since 2025-01-10
 */
@Getter
@Setter
  @TableName("ws_group_member")
public class WsGroupMember implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 自增主键
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      /**
     * 群组ID
     */
      private Long groupId;

      /**
     * 用户ID
     */
      private Long userId;

      /**
     * 成员状态：1-在群组中，0-已移除
     */
      private Boolean status;

      /**
     * 加入时间
     */
      private Date joinedAt;

      /**
     * 离开时间
     */
      private Date leftAt;
}
