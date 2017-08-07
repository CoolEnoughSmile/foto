package com.coolenoughsmile.foto.mainActivity.model;

import android.util.Log;

import com.coolenoughsmile.foto.loginActivity.model.User;

import java.io.File;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by CoolEnoughSmile on 2017/8/4.
 */

public class MainModelImpl implements MainModel {
    private static String TAG="MainModelImpl";
    @Override
    public void updateLogo(String path, final OnUpdateLogoListener onUpdateLogoListener) {
        final BmobFile bmobFile = new BmobFile(new File(path));
        bmobFile.uploadblock(new UploadFileListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    //bmobFile.getFileUrl()--返回的上传文件的完整地址
                    updateUserInfo(bmobFile.getFileUrl(),onUpdateLogoListener);
                }else{
                    onUpdateLogoListener.showMsg("上传文件失败，请检查网络");
                    Log.e(TAG, "done: ",e );
                }

            }

            @Override
            public void onProgress(Integer value) {
                // 返回的上传进度（百分比）
            }
        });
    }
    private void updateUserInfo(final String file, final OnUpdateLogoListener onUpdateLogoListener){
        User user= BmobUser.getCurrentUser(User.class);
        user.setmLogo(file);
        user.update(user.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){
                    onUpdateLogoListener.showMsg("上传文件成功");
                    onUpdateLogoListener.success();
                }else {
                    onUpdateLogoListener.showMsg("更新用户失败");
                }
            }
        });
    }
}
