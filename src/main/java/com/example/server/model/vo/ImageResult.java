package com.example.server.model.vo;

public class ImageResult<T> {
    private String error_code;
    private String log_id;
    private T result;
    private String error_msg;

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
    public ImageResult() {
        super();
    }

    public ImageResult(String error_code, String log_id, T result, String error_msg) {
        super();
        this.error_code = error_code;
        this.log_id = log_id;
        this.result = result;
        this.error_msg = error_msg;
    }

    public static <T> ImageResult<T> success(T data) {
        return new ImageResult<>("", "", data, "");
    }

    public ImageResult(String message) {
        super();
        this.error_msg = message;
    }

    /**
     * 无异常信息的时候使用
     */
    public static <T> ImageResult<T> error(String code, String message) {
        if (code == null) {
            code = EXCEPTION_CODE_500;
        }
        if (message == null) {
            message = EXCEPTION_CODE_400.equals(code) ? EXCEPTION_MESSAGE_400 : EXCEPTION_MESSAGE_500;
        }
        return new ImageResult<>(code, message, null, null);
    }

    /**
     * 仅指定message时使用
     */
    public static <T> ImageResult<T> error(String message) {
        return error(message);
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }
}
