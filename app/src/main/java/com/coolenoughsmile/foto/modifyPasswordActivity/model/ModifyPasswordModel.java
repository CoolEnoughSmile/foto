package com.coolenoughsmile.foto.modifyPasswordActivity.model;

/**
 * Created by CoolEnoughSmile on 2017/8/2.
 */

public interface ModifyPasswordModel {

    void modifyPassword(String password,ModifyPasswordListener modifyPasswordListener);
    interface ModifyPasswordListener{
        void showProgressBar();
        void hideProgressBar();
        void success();
        void failure();
    }
}
