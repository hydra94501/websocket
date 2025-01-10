package com.gallery.websoket.web;

import com.gallery.websoket.dto.parm.WsGroupParam;
import com.gallery.websoket.dto.res.WsGroupRes;
import com.gallery.websoket.result.R;
import com.gallery.websoket.service.WsGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 群组控制器
 * </p>
 *
 * @author xwj
 * @since 2025-01-10
 */
@RestController
@RequestMapping("/api/wsGroup")
@Api(tags = "群组 API")
public class WsGroupController {

    private final WsGroupService wsGroupService;

    @Autowired
    public WsGroupController(WsGroupService wsGroupService) {
        this.wsGroupService = wsGroupService;
    }

    /**
     * 根据ID获取群组
     */
    @ApiOperation(value = "根据ID获取群组信息", notes = "根据群组ID查询群组详情")
    @GetMapping("/getGroupById")
    public R<WsGroupRes> getGroupById(@RequestParam("id") Long id) {
        return R.ok(wsGroupService.getGroupById(id));
    }

    /**
     * 创建群组
     */
    @ApiOperation(value = "创建群组", notes = "根据请求参数创建一个新的群组")
    @PostMapping
    public R<Boolean> createGroup(@RequestBody WsGroupParam wsGroupParam) {
        return R.ok(wsGroupService.createGroup(wsGroupParam));
    }

    /**
     * 更新群组
     */
    @ApiOperation(value = "更新群组", notes = "根据群组ID更新群组信息")
    @PutMapping("/{id}")
    public R<Boolean> updateGroup(
            @ApiParam(value = "群组ID", required = true) @PathVariable Long id,
            @RequestBody WsGroupParam wsGroupParam) {
        return R.ok(wsGroupService.updateGroup(id, wsGroupParam));
    }
}
