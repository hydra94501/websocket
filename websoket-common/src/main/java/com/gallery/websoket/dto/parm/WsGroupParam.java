package com.gallery.websoket.dto.parm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 群组请求参数类
 * </p>
 */
@Data
@ApiModel(description = "群组请求参数")
public class WsGroupParam {

    @ApiModelProperty(value = "群组名称", required = true)
    private String groupName;

    @ApiModelProperty(value = "群组描述", required = true)
    private String groupDescription;

    @ApiModelProperty(value = "创建时间", example = "2025-01-01T10:00:00")
    private Date createdAt;

    @ApiModelProperty(value = "更新时间", example = "2025-01-01T20:00:00")
    private Date updatedAt;
}
