package com.coolenoughsmile.foto.liftActivity.model;

import android.util.Log;

import com.coolenoughsmile.foto.loginActivity.model.User;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by CoolEnoughSmile on 2017/8/2.
 */

public class LiftModelImpl implements LiftModel {
    private static String TAG="LiftModelImpl";
    @Override
    public void submitOrder(String departure, String destination, String appointment_time, String type, String phone, String remarke, final OnSubmitOrderListener onSubmitOrderListener) {
        onSubmitOrderListener.showProgressBar();
        User user=BmobUser.getCurrentUser(User.class);
        final Order order=new Order(user.getObjectId(),user.getmLogo(),user.getUsername(),departure,destination,appointment_time,type,phone,remarke,"等待接单");
        Log.e(TAG, "type: "+type );
        order.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){
                    onSubmitOrderListener.success(order);
                    onSubmitOrderListener.hideProgressBar();
                }else{
                    Log.e(TAG, "done: ",e );
                    onSubmitOrderListener.failure();
                    onSubmitOrderListener.hideProgressBar();
                }
            }
        });
    }
}
