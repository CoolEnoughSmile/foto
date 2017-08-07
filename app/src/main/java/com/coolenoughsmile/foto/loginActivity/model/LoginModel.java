package com.coolenoughsmile.foto.loginActivity.model;

/**
 * Created by CoolEnoughSmile on 2017/7/20.
 */

public interface LoginModel {
    void login(String username,String password,LoginListener loginListener);

    interface LoginListener{
        void onWait();
        void onSuccess();
        void onFailure();
        void showToast(String msg);
    }
}
