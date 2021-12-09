package com.example.server.model.vo;


public class UserResult<T> {

    private String code;
    private T data;
    private String message;

    /**
     * 只为序列化，不能使用
     */
    public UserResult() {
        super();
    }

    /**
     * 构造不对外
     *
     * @param code
     * @param message
     * @param data
     */
    private UserResult(String code, T data, String message) {
        super();
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public UserResult(String message) {
        super();
        this.message = message;
    }

    /**
     * 自定义成功信息
     */
    public static <T> UserResult<T> success(String message, T data) {
        return new UserResult<>("0", data, message);
    }

    /**
     * 500
     */
    private static final String EXCEPTION_CODE_500 = "500";
    /**
     * 400
     */
    private static final String EXCEPTION_CODE_400 = "400";
    /**
     * 500 Default message
     */
    private static final String EXCEPTION_MESSAGE_500 = "Server Internal Error";
    /**
     * 400 Default message
     */
    private static final String EXCEPTION_MESSAGE_400 = "Bad request";

    /**
     * 无异常信息的时候使用
     */
    public static <T> UserResult<T> error(String code, String message) {
        if (code == null) {
            code = EXCEPTION_CODE_500;
        }
        if (message == null) {
            message = EXCEPTION_CODE_400.equals(code) ? EXCEPTION_MESSAGE_400 : EXCEPTION_MESSAGE_500;
        }
        return new UserResult<>(code, null, message);
    }

    /**
     * 默认错误时使用
     */
    public static <T> UserResult<T> error() {
        return error(EXCEPTION_CODE_500, null);
    }

    /**
     * 仅指定message时使用
     */
    public static <T> UserResult<T> error(String message) {
        return error(null, message);
    }

    /**
     * 只有异常信息的错误
     */

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }
}
