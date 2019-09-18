package com.google.taskmanager.Model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Task implements Serializable {

    private String mTitle;
    private String mDescription;
    private boolean mDone = false;
    private final Date mInitialDate = Calendar.getInstance().getTime();
    private Date mExpireDate;
    private String mExpireTime;
    private UUID mID = UUID.randomUUID();
    private UUID mUserID;

    public String getEpireTime() {
        return mExpireTime;
    }

    public void setEpireTime(String epireTime) {
        mExpireTime = epireTime;
    }

    public UUID getUserID() {
        return mUserID;
    }

    public void setUserID(UUID userID) {
        mUserID = userID;
    }

    public UUID getID() {
        return mID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public boolean isDone() {
        return mDone;
    }

    public void setDone(boolean done) {
        mDone = done;
    }

    public Date getInitialDate() {
        return mInitialDate;
    }

    public Date getExpireDate() {
        return mExpireDate;
    }

    public void setExpireDate(Date expireDate) {
        mExpireDate = expireDate;
    }
}
