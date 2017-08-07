package com.coolenoughsmile.foto.liftRecieveActivity.presenter;

import android.content.Context;

import com.coolenoughsmile.foto.liftRecieveActivity.model.LiftRecieveModel;
import com.coolenoughsmile.foto.liftRecieveActivity.model.LiftRecieveModelImpl;
import com.coolenoughsmile.foto.liftRecieveActivity.model.OrderAdapter;
import com.coolenoughsmile.foto.liftRecieveActivity.view.LiftRecieveView;

/**
 * Created by CoolEnoughSmile on 2017/8/5.
 */

public class LiftRecievePresenterImpl implements LiftRecievePresenter {
    private LiftRecieveModel liftRecieveModel;
    private LiftRecieveView liftRecieveView;

    public LiftRecievePresenterImpl(LiftRecieveView liftRecieveView){
        this.liftRecieveModel=new LiftRecieveModelImpl();
        this.liftRecieveView=liftRecieveView;
    }

    @Override
    public OrderAdapter getOrderAdapter(Context context) {
        return  liftRecieveModel.getOrderAdapter(context);
    }
}
