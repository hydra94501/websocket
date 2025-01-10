package com.gallery.websoket.result;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gallery.websoket.enums.ResultCodeEnum;
import com.gallery.websoket.utils.JsonKit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class R<T> implements Serializable {

    /**
     * 业务响应码
     **/
    private Integer code;

    /**
     * 业务响应信息
     **/
    private String msg;

    /**
     * 数据对象
     **/
    private T data;

    /**
     * 签名值
     **/
    private String sign;

    /**
     * 输出json格式字符串
     **/
    public String toJSONString() {
        return JSON.toJSONString(this);
    }

    /**
     * 业务处理成功
     **/
    public static R ok() {
        return ok(null);
    }

    /**
     * 业务处理成功
     **/
    public static <T> R<T> ok(T data) {
        return new R<>(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMsg(), data, null);
    }


    /**
     * 业务处理成功, 返回简单json格式
     **/
    public static R ok4newJson(String key, Object val) {
        return ok(JsonKit.newJson(key, val));
    }

    /**
     * 业务处理成功， 封装分页数据， 仅返回必要参数
     **/
    public static <T> R<PageR.PageBean<T>> page(IPage<T> iPage) {

        PageR.PageBean<T> result = new PageR.PageBean<>();
        result.setRecords(iPage.getRecords());  //记录明细
        result.setTotal(iPage.getTotal()); //总条数
        result.setCurrent(iPage.getCurrent()); //当前页码
        result.setHasNext(iPage.getPages() > iPage.getCurrent()); //是否有下一页
        return ok(result);
    }

    /**
     * 业务处理失败
     **/
    public static R fail(ResultCodeEnum resultCodeEnum, String customMsg) {

        return new R(resultCodeEnum.getCode(), customMsg, null, null);
    }

    /**
     * 业务处理失败
     **/
    public static R fail(ResultCodeEnum resultCodeEnum) {

        return new R(resultCodeEnum.getCode(), resultCodeEnum.getMsg(), null, null);
    }

    /**
     * 自定义错误信息, 原封不用的返回输入的错误信息
     **/
    public static R customFail(String customMsg) {
        return new R(ResultCodeEnum.CUSTOM_FAIL.getCode(), customMsg, null, null);
    }
}
