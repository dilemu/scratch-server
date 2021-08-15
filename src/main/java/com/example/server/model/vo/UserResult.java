package com.example.server.model.vo;

public class UserResult<T> {

    private String code;
    private String content;
    private T data;
    private String msg;

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
     * 只为序列化，不能使用
     */
    public UserResult() {
        super();
    }

    public UserResult(String code, String content, T data, String msg) {
        super();
        this.code = code;
        this.content = content;
        this.data = data;
        this.msg = msg;
    }

    public static <T> UserResult<T> success(T data) {
        return new UserResult<>("", "", data, "");
    }

    public UserResult(String message) {
        super();
        this.msg = message;
    }

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
        return new UserResult<>(code, message, null, null);
    }

    /**
     * 仅指定message时使用
     */
    public static <T> UserResult<T> error(String message) {
        return error(message);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
