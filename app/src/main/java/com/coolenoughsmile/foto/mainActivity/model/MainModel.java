package com.coolenoughsmile.foto.mainActivity.model;

/**
 * Created by CoolEnoughSmile on 2017/8/4.
 */

public interface MainModel {
    void updateLogo(String path,OnUpdateLogoListener onUpdateLogoListener);
    interface OnUpdateLogoListener{
        void success();
        void showMsg(String msg);
    }
}
