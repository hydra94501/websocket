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
 * 用户表（推送管理）
 * </p>
 *
 * @author xwj
 * @since 2025-01-10
 */
@Getter
@Setter
@TableName("ws_user")
public class WsUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 外部系统名称，如live_sys、video_sys、im_sys等
     */
    private String systemName;

    /**
     * 外部系统用户唯一标识
     */
    private String externalUserId;

    /**
     * 推送状态：1-启用，0-禁用
     */
    private Boolean pushStatus;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;
}
