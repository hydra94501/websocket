package com.gallery.websoket.service.impl;

import com.gallery.websoket.dto.parm.WsTopicParam;
import com.gallery.websoket.dto.res.WsTopicRes;
import com.gallery.websoket.enums.ResultCodeEnum;
import com.gallery.websoket.exception.BizException;
import com.gallery.websoket.model.WsTopic;
import com.gallery.websoket.mapper.WsTopicMapper;
import com.gallery.websoket.service.WsTopicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 推送主题表 服务实现类
 * </p>
 *
 * @author xwj
 * @since 2025-01-10
 */
@Service
public class WsTopicServiceImpl extends ServiceImpl<WsTopicMapper, WsTopic> implements WsTopicService {

    private final WsTopicMapper wsTopicMapper;

    @Autowired
    public WsTopicServiceImpl(WsTopicMapper wsTopicMapper) {
        this.wsTopicMapper = wsTopicMapper;
    }


    @Override
    public WsTopicRes getTopicById(Long id) {
        WsTopic wsTopic = getById(id);
        WsTopicRes wsTopicRes = new WsTopicRes();
        BeanUtils.copyProperties(wsTopic, wsTopicRes);
        return wsTopicRes;
    }

    @Override
    public boolean createTopic(WsTopicParam wsTopicParam) {
        WsTopic wsTopic = new WsTopic();
        BeanUtils.copyProperties(wsTopicParam, wsTopic);
        return save(wsTopic);
    }

    @Override
    public boolean updateTopic(Long id, WsTopicParam wsTopicParam) {
        WsTopic wsTopic = getById(id);
        if (wsTopic == null) {
            throw new BizException(ResultCodeEnum.SYS_OPERATION_FAIL_SELETE, "topic不存在！");
        }
        WsTopic wsTopic1 = new WsTopic();
        BeanUtils.copyProperties(wsTopicParam, wsTopic);
        wsTopic1.setId(id);
        return updateById(wsTopic);
    }
}
