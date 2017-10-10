package com.coolenoughsmile.foto.liftRecieveActivity.presenter;

import android.content.Context;

import com.coolenoughsmile.foto.liftRecieveActivity.model.LiftRecieveModel;
import com.coolenoughsmile.foto.liftRecieveActivity.model.LiftRecieveModelImpl;
import com.coolenoughsmile.foto.liftRecieveActivity.model.OrderAdapter;
import com.coolenoughsmile.foto.liftRecieveActivity.view.LiftRecieveView;

/**
 * Created by CoolEnoughSmile on 2017/8/5.
 */

public class LiftRecievePresenterImpl implements LiftRecievePresenter,LiftRecieveModel.OnloadDataListener{
    private LiftRecieveModel liftRecieveModel;
    private LiftRecieveView liftRecieveView;

    public LiftRecievePresenterImpl(LiftRecieveView liftRecieveView){
        this.liftRecieveModel=new LiftRecieveModelImpl();
        this.liftRecieveView=liftRecieveView;
    }

    @Override
    public void loadData(Context context) {
        liftRecieveModel.loadData(context,this);
    }

    @Override
    public void showMsg(String msg) {
        liftRecieveView.showMsg(msg);
    }

    @Override
    public void setData(OrderAdapter orderAdapter) {
        liftRecieveView.setData(orderAdapter);
    }
}
