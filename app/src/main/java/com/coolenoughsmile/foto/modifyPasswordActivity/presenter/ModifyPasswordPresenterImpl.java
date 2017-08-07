package com.coolenoughsmile.foto.modifyPasswordActivity.presenter;

import com.coolenoughsmile.foto.modifyPasswordActivity.model.ModifyPasswordModel;
import com.coolenoughsmile.foto.modifyPasswordActivity.model.ModifyPasswordModelImpl;
import com.coolenoughsmile.foto.modifyPasswordActivity.view.ModifyPasswordView;

/**
 * Created by CoolEnoughSmile on 2017/8/2.
 */

public class ModifyPasswordPresenterImpl implements ModifyPasswordPresenter,ModifyPasswordModel.ModifyPasswordListener {

    private ModifyPasswordView modifyPasswordView;
    private ModifyPasswordModel modifyPasswordModel;

    public ModifyPasswordPresenterImpl(ModifyPasswordView modifyPasswordView){
        this.modifyPasswordView=modifyPasswordView;
        modifyPasswordModel=new ModifyPasswordModelImpl();
    }

    @Override
    public void modifyPassword(String password) {
        modifyPasswordModel.modifyPassword(password,this);
    }

    @Override
    public void showProgressBar() {
        modifyPasswordView.showProgressBar();
    }

    @Override
    public void hideProgressBar() {
        modifyPasswordView.hideProgressBar();
    }

    @Override
    public void success() {
        modifyPasswordView.success();
    }

    @Override
    public void failure() {
        modifyPasswordView.failure();
    }
}
