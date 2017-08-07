package com.coolenoughsmile.foto.liftActivity.model;

/**
 * Created by CoolEnoughSmile on 2017/8/2.
 */

public interface LiftModel {
    void submitOrder(String departure, String destination, String appointment_time, String type, String phone, String remarke,OnSubmitOrderListener onSubmitOrderListener);
    interface OnSubmitOrderListener {
        void showProgressBar();
        void hideProgressBar();
        void success(Order order);
        void failure();
    }
}
