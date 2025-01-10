package com.gallery.websoket.service;

import com.gallery.websoket.dto.parm.WsTopicParam;
import com.gallery.websoket.dto.res.WsTopicRes;
import com.gallery.websoket.model.WsTopic;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 推送主题表 服务类
 * </p>
 *
 * @author xwj
 * @since 2025-01-10
 */
public interface WsTopicService extends IService<WsTopic> {


    /**
     * 根据ID获取Topic
     *
     * @param id Topic ID
     * @return WsTopicRes
     */
    WsTopicRes getTopicById(Long id);


    /**
     * 创建推送Topic
     *
     * @param wsTopicParam 推送Topic的请求参数
     * @return 返回结果
     */
    boolean createTopic(WsTopicParam wsTopicParam);

    /**
     * 更新推送Topic
     *
     * @param id 推送Topic的ID
     * @param wsTopicParam 更新的推送Topic请求参数
     * @return 返回结果
     */
    boolean updateTopic(Long id, WsTopicParam wsTopicParam);

}
