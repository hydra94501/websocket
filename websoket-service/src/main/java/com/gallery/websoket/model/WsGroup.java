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
 * 群组表
 * </p>
 *
 * @author xwj
 * @since 2025-01-10
 */
@Getter
@Setter
@TableName("ws_group")
public class WsGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 群组名称
     */
    private String groupName;

    /**
     * 群组描述
     */
    private String groupDescription;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;
}
