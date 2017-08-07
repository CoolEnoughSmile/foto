package com.coolenoughsmile.foto.registerActivity.presenter;

/**
 * Created by CoolEnoughSmile on 2017/7/24.
 */

public interface RegisterPresenter {

    void getSecurityCode(String tel);

    void register(String tel, String code);

    void doRegister(String tel, String username, String password);
}
