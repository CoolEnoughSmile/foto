package com.coolenoughsmile.foto.registerActivity.model;

/**
 * Created by CoolEnoughSmile on 2017/7/24.
 */

public interface RegisterModel {


    void getSecurityCode(String tel);

    void register(String tel, String code);

    void doRegister(String tel, String username, String password,RegisterModel.RegisterListener registerListener);

    interface RegisterListener{

        void onRegisterSuccess();

        void showMsg(String msg);

        void showProgressBar();

        void hideProgressBar();
    }
}
