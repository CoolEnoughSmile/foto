package com.coolenoughsmile.foto.liftRecieveActivity.model;

import android.content.Context;

/**
 * Created by CoolEnoughSmile on 2017/8/5.
 */

public interface LiftRecieveModel {
    void loadData(Context context,OnloadDataListener onloadDataListener);

    interface OnloadDataListener{
        void showMsg(String msg);
        void setData(OrderAdapter orderAdapter);
    }
}
