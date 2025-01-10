package com.gallery.websoket.mapper;

import com.gallery.websoket.model.WsUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表（推送管理） Mapper 接口
 * </p>
 *
 * @author xwj
 * @since 2025-01-10
 */
@Mapper
public interface WsUserMapper extends BaseMapper<WsUser> {

}
