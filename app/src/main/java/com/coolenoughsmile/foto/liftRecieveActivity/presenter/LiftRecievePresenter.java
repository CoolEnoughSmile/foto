package com.coolenoughsmile.foto.liftRecieveActivity.presenter;

import android.content.Context;

import com.coolenoughsmile.foto.liftRecieveActivity.model.OrderAdapter;

/**
 * Created by CoolEnoughSmile on 2017/8/5.
 */

public interface LiftRecievePresenter {
    OrderAdapter getOrderAdapter(Context context);
}
