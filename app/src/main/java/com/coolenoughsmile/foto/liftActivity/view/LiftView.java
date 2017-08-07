package com.coolenoughsmile.foto.liftActivity.view;

import com.coolenoughsmile.foto.liftActivity.model.Order;

/**
 * Created by CoolEnoughSmile on 2017/8/2.
 */

public interface LiftView {
    void showProgressBar();
    void hideProgressBar();
    void success(Order order);
    void failure();
}
