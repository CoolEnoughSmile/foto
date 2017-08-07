package com.coolenoughsmile.foto.loginActivity.view;

/**
 * Created by CoolEnoughSmile on 2017/7/20.
 */

public interface LoginView {
    void showProgressBar();
    void hideProgressBar();
    void onSuccess();
    void showToast(String msg);
}
