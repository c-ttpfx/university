package com.ttpfx.common.exception;

/**
 * @author ttpfx
 * @date 2023/2/15
 */
public enum LivingCodeEnum {
    UNKNOWN_EXCEPTION(40000, "系统未知异常"),
    INVALID_EXCEPTION(40001, "参数校验异常~~");

    private int code;
    private String msg;

    LivingCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
