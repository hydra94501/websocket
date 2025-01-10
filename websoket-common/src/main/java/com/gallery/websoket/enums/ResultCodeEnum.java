package com.gallery.websoket.enums;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {

    SUCCESS(0, "SUCCESS"), //请求成功
    CUSTOM_FAIL(9999, "自定义业务异常"),  //自定义业务异常
    SYSTEM_ERROR(500, "系统异常"),
    SYSTEM_SETTING_ERROR(13, "系统异常[配置失败]"),
    SYSTEM_FILE_ERROR(14, "系统异常[选择文件不存在]"),
    PARAMS_ERROR(11, "参数有误[转换异常]"),
    DB_ERROR(12, "数据库服务异常"),
    SYS_OPERATION_FAIL_CREATE(5000, "新增失败"),
    SYS_OPERATION_FAIL_DELETE(5001, "删除失败"),
    SYS_OPERATION_FAIL_UPDATE(5002, "修改失败"),
    SYS_OPERATION_FAIL_SELETE(5003, "记录不存在"),
    SYS_PERMISSION_ERROR(5004, "权限错误，当前用户不支持此操作");


    private int code;

    private String msg;


    ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
