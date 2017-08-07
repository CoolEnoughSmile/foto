package com.coolenoughsmile.foto.loginActivity.presenter;

import com.coolenoughsmile.foto.loginActivity.model.LoginModel;
import com.coolenoughsmile.foto.loginActivity.model.LoginModelImpl;
import com.coolenoughsmile.foto.loginActivity.view.LoginView;

/**
 * Created by CoolEnoughSmile on 2017/7/20.
 */

public class LoginPresenterImpl implements LoginPresenter,LoginModel.LoginListener{

    private LoginModel loginModel;
    private LoginView loginView;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginModel = new LoginModelImpl();
        this.loginView=loginView;
    }

    @Override
    public void login(String username, String password) {
        loginModel.login(username,password,this);
    }

    @Override
    public void onWait() {
        loginView.showProgressBar();
    }

    @Override
    public void onSuccess() {
        loginView.onSuccess();
        loginView.hideProgressBar();
    }

    @Override
    public void onFailure() {
        loginView.hideProgressBar();
    }

    @Override
    public void showToast(String msg) {
        loginView.showToast(msg);
    }
}
