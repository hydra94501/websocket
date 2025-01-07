package com.gallery.websoket.enums;

public enum ResultCodeEnum {

    SUCCESS(true, 200, "成功"),

    UNKNOWN_REASON(false, 201, "未知错误");

    private final Boolean success;

    private final Integer code;

    private final String message;

    ResultCodeEnum(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ResultCodeEnum{" + "success=" + success + ", code=" + code + ", message='" + message + '\'' + '}';
    }


}
