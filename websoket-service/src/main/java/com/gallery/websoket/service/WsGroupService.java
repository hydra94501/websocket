package com.gallery.websoket.service;

import com.gallery.websoket.dto.parm.WsGroupParam;
import com.gallery.websoket.dto.res.WsGroupRes;
import com.gallery.websoket.model.WsGroup;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 群组表 服务类
 * </p>
 *
 * @author xwj
 * @since 2025-01-10
 */
public interface WsGroupService extends IService<WsGroup> {

    /**
     * 根据ID获取群组
     *
     * @param id 群组ID
     * @return WsGroupRes
     */
    WsGroupRes getGroupById(Long id);

    /**
     * 创建群组
     *
     * @param wsGroupParam 群组请求参数
     * @return 是否成功
     */
    boolean createGroup(WsGroupParam wsGroupParam);

    /**
     * 更新群组
     *
     * @param id 群组ID
     * @param wsGroupParam 更新的群组请求参数
     * @return 是否成功
     */
    boolean updateGroup(Long id, WsGroupParam wsGroupParam);
}
