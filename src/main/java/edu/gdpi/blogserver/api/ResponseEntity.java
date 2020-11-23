package edu.gdpi.blogserver.api;

/**
 * 响应实体
 *
 * @author ZhengHaiFeng
 */
public class ResponseEntity {
    private final Integer code;
    private final String message;
    private final Object data;

    public ResponseEntity(ResponseCode responseCode, Object data) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.data = data;
    }

    /**
     * 成功返回
     *
     * @param data 返回数据
     * @return
     */
    public static ResponseEntity success(Object data) {
        return new ResponseEntity(ResponseCode.SUCCESS, data);
    }

    /**
     * 失败返回（用户错误）
     *
     * @param data 数据
     * @return
     */
    public static ResponseEntity failure(Object data) {
        return new ResponseEntity(ResponseCode.FAILURE, data);
    }

    /**
     * 失败返回（系统错误）
     *
     * @param data
     * @return
     */
    public static ResponseEntity error(Object data) {
        return new ResponseEntity(ResponseCode.ERROR, data);
    }

    public static ResponseEntity custom(ResponseCode responseCode, Object data) {
        return new ResponseEntity(responseCode, data);
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
