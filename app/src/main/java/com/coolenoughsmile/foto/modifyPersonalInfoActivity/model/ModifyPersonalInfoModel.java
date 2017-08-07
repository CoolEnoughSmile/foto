package com.coolenoughsmile.foto.modifyPersonalInfoActivity.model;

/**
 * Created by CoolEnoughSmile on 2017/8/2.
 */

public interface ModifyPersonalInfoModel {
    void modify(String elseName,String sex,String birthday,String school,OnModifyListener onModifyListener);

    interface OnModifyListener{
        void showProgressBar();
        void hideProgressBar();
        void success();
        void failure();
    }
}
