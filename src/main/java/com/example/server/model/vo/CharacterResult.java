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
    private T code_result;

    /**
     * 只为序列化，不能使用
     */
    public CharacterResult() {
        super();
    }

    private CharacterResult(String log_id, String codes_result_num, String words_result_num, T words_result, T code_result) {
        super();
        this.log_id = log_id;
        this.codes_result_num = codes_result_num;
        this.words_result_num = words_result_num;
        this.words_result = words_result;
        this.code_result = code_result;
    }

    public static <T> CharacterResult<T> success(T result, T data) {
        return new CharacterResult<>("", "", "", result, data);
    }

    /**
     * 仅指定message时使用
     */
    public static <T> CharacterResult<T> error(String message) {
        return error(message);
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

    public T getCode_result() {
        return code_result;
    }

    public void setCode_result(T code_result) {
        this.code_result = code_result;
    }
}
