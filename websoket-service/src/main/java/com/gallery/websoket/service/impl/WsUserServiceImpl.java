package com.gallery.websoket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gallery.websoket.model.WsUser;
import com.gallery.websoket.mapper.WsUserMapper;
import com.gallery.websoket.service.WsUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表（推送管理） 服务实现类
 * </p>
 *
 * @author xwj
 * @since 2025-01-10
 */
@Service
public class WsUserServiceImpl extends ServiceImpl<WsUserMapper, WsUser> implements WsUserService {

    @Override
    public WsUser getUserBySystemNameAndExternalUserId(String systemName, String externalUserId) {
        LambdaQueryWrapper<WsUser> queryWrapper = new LambdaQueryWrapper<WsUser>();
        queryWrapper.eq(WsUser::getSystemName, systemName);
        queryWrapper.eq(WsUser::getExternalUserId, externalUserId);
        return getOne(queryWrapper);
    }
}
