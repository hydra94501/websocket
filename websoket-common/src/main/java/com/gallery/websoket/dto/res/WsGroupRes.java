package com.gallery.websoket.dto.res;

import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 群组响应类
 * </p>
 */
@Data
public class WsGroupRes {

    /**
     * 群组ID
     */
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
