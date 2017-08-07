package com.coolenoughsmile.foto.loginActivity.model;

import android.util.Log;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

/**
 * Created by CoolEnoughSmile on 2017/7/20.
 */

public class LoginModelImpl implements LoginModel {
    private static String TAG ="LoginModelImpl";

    @Override
    public void login(String username, String password, final LoginListener loginListener) {

        loginListener.onWait();
        BmobUser.loginByAccount(username, password, new LogInListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (user!=null){
                    loginListener.onSuccess();
                    loginListener.showToast("登录成功！");
                }else {
                    Log.e(TAG, "done: ",e);
                    loginListener.onFailure();
                    if (e.getErrorCode()==9016){
                        loginListener.showToast("登录失败，请检查网络！");
                    }
                    if (e.getErrorCode()==101){
                        loginListener.showToast("用户名或密码错误！");
                    }
                }
            }
        });
    }
}
