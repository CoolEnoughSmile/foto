package com.coolenoughsmile.foto.registerActivity.model;

import android.util.Log;

import com.coolenoughsmile.foto.loginActivity.model.User;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.smssdk.SMSSDK;

/**
 * Created by CoolEnoughSmile on 2017/7/24.
 */

public class RegisterModelImpl implements RegisterModel {
    private static String TAG="RegisterModelImpl";
    private String country="86";
    @Override
    public void getSecurityCode(String tel) {
        SMSSDK.getVerificationCode(country,tel);
    }

    @Override
    public void register(String tel, String code) {
        SMSSDK.submitVerificationCode(country,tel,code);
    }

    @Override
    public void doRegister(String tel, String username, String password, final RegisterModel.RegisterListener registerListener) {
        registerListener.showProgressBar();
        User user=new User();
        user.setMobilePhoneNumber(tel);
        user.setUsername(username);
        user.setPassword(password);
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e==null){
                    registerListener.hideProgressBar();
                    registerListener.onRegisterSuccess();
                }else {
                    Log.e(TAG, "signUp:done: ",e );
                    registerListener.hideProgressBar();
                    if (e.getErrorCode()==209){
                        registerListener.showMsg("该手机已注册！");
                    }else {
                        registerListener.showMsg("请检查网络！");
                    }
                }
            }
        });
    }
}
