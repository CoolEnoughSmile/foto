package com.coolenoughsmile.foto.modifyPersonalInfoActivity.model;

import android.util.Log;

import com.coolenoughsmile.foto.loginActivity.model.User;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by CoolEnoughSmile on 2017/8/2.
 */

public class ModifyPersonalInfoModelImpl implements ModifyPersonalInfoModel{
    private static String TAG="ModifyPersonalInfoModel";
    @Override
    public void modify(String elseName, String sex, String birthday, String school, final OnModifyListener onModifyListener) {
        onModifyListener.showProgressBar();
        User newUser = new User();
        newUser.setmElsename(elseName);
        newUser.setmSex(sex);
        newUser.setMbirthday(birthday);
        newUser.setmSchool(school);
        newUser.update(BmobUser.getCurrentUser().getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    onModifyListener.success();
                    onModifyListener.hideProgressBar();
                } else {
                    Log.e(TAG, "done: ",e );
                    onModifyListener.failure();
                    onModifyListener.hideProgressBar();
                }
            }
        });
    }
}
