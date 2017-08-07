package com.coolenoughsmile.foto.modifyPasswordActivity.model;

import android.util.Log;

import com.coolenoughsmile.foto.loginActivity.model.User;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by CoolEnoughSmile on 2017/8/2.
 */

public class ModifyPasswordModelImpl implements ModifyPasswordModel {
    private static String TAG="ModifyPasswordModel";
    @Override
    public void modifyPassword(String password, final ModifyPasswordListener modifyPasswordListener) {
        modifyPasswordListener.showProgressBar();
        User newUser = new User();
        newUser.setPassword(password);
        newUser.update(BmobUser.getCurrentUser().getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){
                    modifyPasswordListener.hideProgressBar();
                    modifyPasswordListener.success();
                }else {
                    Log.e(TAG, "done: ",e );
                    modifyPasswordListener.hideProgressBar();
                    modifyPasswordListener.failure();
                }
            }
        });
    }
}
