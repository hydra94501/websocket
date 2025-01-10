package com.gallery.websoket.web;

import com.gallery.websoket.dto.parm.WsTopicParam;
import com.gallery.websoket.enums.ResultCodeEnum;
import com.gallery.websoket.dto.res.WsTopicRes;
import com.gallery.websoket.result.R;
import com.gallery.websoket.service.WsTopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 推送主题表 前端控制器
 * </p>
 *
 * @author xwj
 * @since 2025-01-10
 */
@RestController
@RequestMapping("/api/wsTopic")
@Api(tags = "主题 API")
public class WsTopicController {

    private final WsTopicService wsTopicService;

    @Autowired
    public WsTopicController(WsTopicService wsTopicService) {
        this.wsTopicService = wsTopicService;
    }

    /**
     * 根据ID获取Topic
     */
    @ApiOperation(value = "根据ID获取Topic信息", notes = "根据Topic ID查询Topic详情")
    @GetMapping("/getTopicById")
    public R<WsTopicRes> getTopicById(@RequestParam("id") Long id) {
        return R.ok(wsTopicService.getTopicById(id));
    }

    /**
     * 创建推送Topic
     */
    @ApiOperation(value = "创建推送Topic", notes = "根据请求参数创建一个新的推送Topic")
    @PostMapping
    public R<Boolean> createTopic(@RequestBody WsTopicParam wsTopicParam) {
        return R.ok(wsTopicService.createTopic(wsTopicParam));
    }

    /**
     * 更新推送Topic
     */
    @ApiOperation(value = "更新推送Topic", notes = "根据Topic ID更新推送Topic信息")
    @PutMapping("/{id}")
    public R<Boolean> updateTopic(
            @ApiParam(value = "推送Topic ID", required = true) @PathVariable Long id,
            @RequestBody WsTopicParam wsTopicParam) {
        return R.ok(wsTopicService.updateTopic(id, wsTopicParam));
    }

}
