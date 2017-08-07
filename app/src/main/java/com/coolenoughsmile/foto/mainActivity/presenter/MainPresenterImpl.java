package com.coolenoughsmile.foto.mainActivity.presenter;

import com.coolenoughsmile.foto.mainActivity.model.MainModel;
import com.coolenoughsmile.foto.mainActivity.model.MainModelImpl;
import com.coolenoughsmile.foto.mainActivity.view.MainView;

/**
 * Created by CoolEnoughSmile on 2017/8/4.
 */

public class MainPresenterImpl implements MainPresenter,MainModel.OnUpdateLogoListener {
    private MainModel mainModel;
    private MainView mainView;

    public MainPresenterImpl(MainView mainView){
        this.mainModel=new MainModelImpl();
        this.mainView=mainView;
    }

    @Override
    public void updateLogo(String path) {
        mainModel.updateLogo(path,this);
    }

    @Override
    public void success() {
        mainView.success();
    }

    @Override
    public void showMsg(String msg) {
        mainView.showMsg(msg);
    }
}
