package com.gallery.websoket.service.impl;

import com.gallery.websoket.dto.parm.WsGroupParam;
import com.gallery.websoket.dto.res.WsGroupRes;
import com.gallery.websoket.model.WsGroup;
import com.gallery.websoket.mapper.WsGroupMapper;
import com.gallery.websoket.service.WsGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 群组表 服务实现类
 * </p>
 *
 * @author xwj
 * @since 2025-01-10
 */
@Service
public class WsGroupServiceImpl extends ServiceImpl<WsGroupMapper, WsGroup> implements WsGroupService {

    private final WsGroupMapper wsGroupMapper;

    @Autowired
    public WsGroupServiceImpl(WsGroupMapper wsGroupMapper) {
        this.wsGroupMapper = wsGroupMapper;
    }

    @Override
    public WsGroupRes getGroupById(Long id) {
        WsGroup wsGroup = getById(id);
        WsGroupRes wsGroupRes = new WsGroupRes();
        BeanUtils.copyProperties(wsGroup, wsGroupRes);
        return wsGroupRes;
    }

    @Override
    public boolean createGroup(WsGroupParam wsGroupParam) {
        WsGroup wsGroup = new WsGroup();
        BeanUtils.copyProperties(wsGroupParam, wsGroup);
        return save(wsGroup);
    }

    @Override
    public boolean updateGroup(Long id, WsGroupParam wsGroupParam) {
        WsGroup wsGroup = getById(id);
        if (wsGroup == null) {
            throw new RuntimeException("群组不存在！");
        }
        BeanUtils.copyProperties(wsGroupParam, wsGroup);
        wsGroup.setId(id);
        return updateById(wsGroup);
    }
}
