package com.coolenoughsmile.foto.liftActivity.presenter;

import com.coolenoughsmile.foto.liftActivity.model.LiftModel;
import com.coolenoughsmile.foto.liftActivity.model.LiftModelImpl;
import com.coolenoughsmile.foto.liftActivity.model.Order;
import com.coolenoughsmile.foto.liftActivity.view.LiftView;

/**
 * Created by CoolEnoughSmile on 2017/8/2.
 */

public class LiftPresenterImpl implements LiftPresenter,LiftModel.OnSubmitOrderListener {
    private LiftView liftView;
    private LiftModel liftModel;

    public LiftPresenterImpl(LiftView liftView){
        this.liftView=liftView;
        liftModel=new LiftModelImpl();
    }

    @Override
    public void submitOrder(String departure, String destination, String appointment_time, String type, String phone, String remarke) {
        liftModel.submitOrder(departure,destination,appointment_time,type,phone,remarke,this);
    }

    @Override
    public void showProgressBar() {
        liftView.showProgressBar();
    }

    @Override
    public void hideProgressBar() {
        liftView.hideProgressBar();
    }

    @Override
    public void success(Order order) {
        liftView.success(order);
    }

    @Override
    public void failure() {
        liftView.failure();
    }
}
