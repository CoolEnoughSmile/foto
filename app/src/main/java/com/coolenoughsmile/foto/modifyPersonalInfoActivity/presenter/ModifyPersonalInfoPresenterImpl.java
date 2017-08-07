package com.coolenoughsmile.foto.modifyPersonalInfoActivity.presenter;

import com.coolenoughsmile.foto.modifyPersonalInfoActivity.model.ModifyPersonalInfoModel;
import com.coolenoughsmile.foto.modifyPersonalInfoActivity.model.ModifyPersonalInfoModelImpl;
import com.coolenoughsmile.foto.modifyPersonalInfoActivity.view.ModifyPersonalInfoView;

/**
 * Created by CoolEnoughSmile on 2017/8/2.
 */

public class ModifyPersonalInfoPresenterImpl implements ModifyPersonalInfoPresenter,ModifyPersonalInfoModel.OnModifyListener{

    private ModifyPersonalInfoView modifyPersonalInfoView;
    private ModifyPersonalInfoModel modifyPersonalInfoModel;

    public ModifyPersonalInfoPresenterImpl(ModifyPersonalInfoView modifyPersonalInfoView){
        this.modifyPersonalInfoView=modifyPersonalInfoView;
        modifyPersonalInfoModel=new ModifyPersonalInfoModelImpl();
    }
    @Override
    public void modify(String elseName, String sex, String birthday, String school) {
        modifyPersonalInfoModel.modify(elseName,sex,birthday,school,this);
    }

    @Override
    public void showProgressBar() {
        modifyPersonalInfoView.showProgressBar();
    }

    @Override
    public void hideProgressBar() {
        modifyPersonalInfoView.hideProgressBar();
    }

    @Override
    public void success() {
        modifyPersonalInfoView.success();
    }

    @Override
    public void failure() {
        modifyPersonalInfoView.failure();
    }

}
