package com.google.taskmanager.Model;

import java.util.UUID;

public class User {
    private String mUserName;
    private String mPassWord;
    private UUID mUserID = UUID.randomUUID();

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getPassWord() {
        return mPassWord;
    }

    public void setPassWord(String passWord) {
        mPassWord = passWord;
    }

    public UUID getUserID() {
        return mUserID;
    }

}
