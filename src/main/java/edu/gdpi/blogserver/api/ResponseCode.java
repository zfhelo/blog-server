package edu.gdpi.blogserver.api;

/**
 * @author ZhengHaiFeng
 */
public enum ResponseCode {
    // 2000~2999 成功
    SUCCESS(2000, "成功"),
    FAILURE(4000, "失败"),
    FAILURE_NO_AUTH(4001, "未登录"),
    FAILURE_BAD_PARAM(4002, "参数错误"),
    FAILURE_ACCESS_DENIED(4003, "没有权限"),
    TOKEN_EXPIRED(4004, "登录已过期"),

    // 服务端错误
    ERROR(5000, "失败");

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
