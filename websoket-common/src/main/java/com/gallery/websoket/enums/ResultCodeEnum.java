package com.gallery.websoket.enums;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {

    SUCCESS(0, "SUCCESS", "success"), //请求成功
    CUSTOM_FAIL(9999, "自定义业务异常","custom.business.exception"),  //自定义业务异常
    SYSTEM_ERROR(500, "系统异常","system.exception"),
    SYSTEM_SETTING_ERROR(13, "系统异常[配置失败]","system.setting.exception"),
    SYSTEM_FILE_ERROR(14, "系统异常[选择文件不存在]","system.file.exception"),
    PARAMS_ERROR(11, "参数有误[转换异常]","param.exception"),
    DB_ERROR(12, "数据库服务异常","database.exception"),
    SYS_OPERATION_FAIL_CREATE(5000, "新增失败","add.exception"),
    SYS_OPERATION_FAIL_DELETE(5001, "删除失败","del.exception"),
    SYS_OPERATION_FAIL_UPDATE(5002, "修改失败","edit.exception"),
    SYS_OPERATION_FAIL_SELETE(5003, "记录不存在","record.exception"),
    SYS_PERMISSION_ERROR(5004, "权限错误，当前用户不支持此操作","auth.exception");


    private int code;

    private String msg;

    private String key;

    ResultCodeEnum(int code, String msg, String key) {
        this.code = code;
        this.msg = msg;
        this.key = key;
    }

}
