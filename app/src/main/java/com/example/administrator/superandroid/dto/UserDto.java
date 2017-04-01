package com.example.administrator.superandroid.dto;

import java.io.Serializable;

public class UserDto implements Serializable {

    private String userName;
    private String passWord;
    private String nickName;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
