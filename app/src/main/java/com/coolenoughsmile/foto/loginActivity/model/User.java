package com.coolenoughsmile.foto.loginActivity.model;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2016/8/11.
 */
public class User extends BmobUser {
    private String mUsername;
    private String mPassword;
    private String mPhone;
    private String mElsename;
    private String mSex;
    private String mbirthday;
    private String mSchool;
    private String mLogo;

    public String getmElsename() {
        return mElsename;
    }

    public void setmElsename(String mElsename) {
        this.mElsename = mElsename;
    }

    public String getmSex() {
        return mSex;
    }

    public void setmSex(String mSex) {
        this.mSex = mSex;
    }

    public String getmSchool() {
        return mSchool;
    }

    public void setmSchool(String mSchool) {
        this.mSchool = mSchool;
    }

    public String getMbirthday() {
        return mbirthday;
    }

    public void setMbirthday(String mbirthday) {
        this.mbirthday = mbirthday;
    }


    public String getmUsername() {
        return mUsername;
    }

    public void setmUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getmBirthday() {
        return mbirthday;
    }

    public String getmLogo() {
        return mLogo;
    }

    public void setmLogo(String mLogo) {
        this.mLogo = mLogo;
    }
}
