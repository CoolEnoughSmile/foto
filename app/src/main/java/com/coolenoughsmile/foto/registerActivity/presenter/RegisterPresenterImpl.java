package com.coolenoughsmile.foto.registerActivity.presenter;

import com.coolenoughsmile.foto.registerActivity.model.RegisterModel;
import com.coolenoughsmile.foto.registerActivity.model.RegisterModelImpl;
import com.coolenoughsmile.foto.registerActivity.view.RegisterView;

/**
 * Created by CoolEnoughSmile on 2017/7/24.
 */

public class RegisterPresenterImpl implements RegisterPresenter,RegisterModel.RegisterListener {

    private RegisterModel registerModel;
    private RegisterView registerView;

    public RegisterPresenterImpl(RegisterView registerView){
        registerModel=new RegisterModelImpl();
        this.registerView=registerView;
    }

    @Override
    public void getSecurityCode(String tel) {
        registerModel.getSecurityCode(tel);
    }

    @Override
    public void register(String tel, String code) {
        registerModel.register(tel,code);
    }

    @Override
    public void doRegister(String tel, String username, String password) {
        registerModel.doRegister(tel,username,password,this);
    }

    @Override
    public void onRegisterSuccess() {
        registerView.onRegisterSuccess();
    }

    @Override
    public void showMsg(String msg) {
        registerView.showMsg(msg);
    }

    @Override
    public void showProgressBar() {
        registerView.showProgressBar();
    }

    @Override
    public void hideProgressBar() {
        registerView.hideProgressBar();
    }
}
