package com.example.server.model.vo;

/**
 * <功能描述>
 *
 * @author
 * @date 2021/8/12 18:44
 */
public class CharacterResult<T> {
    private String log_id = "";
    private String codes_result_num = "0";
    private String words_result_num = "0";
    private T words_result;
    private T codes_result;
    private String error_code;
    private String error_msg;


    /**
     * 只为序列化，不能使用
     */
    public CharacterResult() {
        super();
    }


    public CharacterResult(String log_id, String codes_result_num, String words_result_num, T words_result, T codes_result, String error_code, String error_msg) {
        super();
        this.log_id = log_id;
        this.codes_result_num = codes_result_num;
        this.words_result_num = words_result_num;
        this.words_result = words_result;
        this.codes_result = codes_result;
        this.error_code = error_code;
        this.error_msg = error_msg;
    }

    public CharacterResult(String error_code, String error_msg) {
        this.error_code = error_code;
        this.error_msg = error_msg;
    }

    public static <T> CharacterResult<T> success(T result, T data) {
        return new CharacterResult<>("", "", "", result, data, "", "");
    }


    /**
     * 仅指定message时使用
     */
    public static <T> CharacterResult<T> error(String code, String message) {
        return new CharacterResult<>(code, message);
    }

    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public String getCodes_result_num() {
        return codes_result_num;
    }

    public void setCodes_result_num(String codes_result_num) {
        this.codes_result_num = codes_result_num;
    }

    public String getWords_result_num() {
        return words_result_num;
    }

    public void setWords_result_num(String words_result_num) {
        this.words_result_num = words_result_num;
    }

    public T getWords_result() {
        return words_result;
    }

    public void setWords_result(T words_result) {
        this.words_result = words_result;
    }

    public T getCodes_result() {
        return codes_result;
    }

    public void setCodes_result(T codes_result) {
        this.codes_result = codes_result;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }
}
