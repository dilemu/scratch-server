package com.example.server.model.vo;


/**
 * <功能描述>
 *
 * @author
 * @date 2021/8/11 16:06
 */
public class UserRequest {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
