package com.coolenoughsmile.foto.liftRecieveActivity.model;

import android.content.Context;
import android.util.Log;

import com.coolenoughsmile.foto.liftActivity.model.Order;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by CoolEnoughSmile on 2017/8/5.
 */

public class LiftRecieveModelImpl implements LiftRecieveModel {
    @Override
    public OrderAdapter getOrderAdapter(Context context) {
        final List<Order> list=new ArrayList<>();
        BmobQuery<Order> query=new BmobQuery<>();
        query.addWhereEqualTo("status","等待接单");
        query.setLimit(20);
        query.findObjects(new FindListener<Order>() {
            @Override
            public void done(List<Order> object, BmobException e) {
                if(e==null){
                    list.addAll(object);
                    Log.e("bmob","成功：！");
                }else{
                    Log.e("bmob","失败：",e);
                }
            }
        });
        return new OrderAdapter(list,context);
    }
}
