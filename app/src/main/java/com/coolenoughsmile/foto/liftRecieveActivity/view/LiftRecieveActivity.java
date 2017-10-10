package com.coolenoughsmile.foto.liftRecieveActivity.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;
import com.coolenoughsmile.foto.R;
import com.coolenoughsmile.foto.base.BaseActivity;
import com.coolenoughsmile.foto.liftRecieveActivity.model.OrderAdapter;
import com.coolenoughsmile.foto.liftRecieveActivity.presenter.LiftRecievePresenter;
import com.coolenoughsmile.foto.liftRecieveActivity.presenter.LiftRecievePresenterImpl;

/**
 * Created by CoolEnoughSmile on 2017/7/27.
 */

public class LiftRecieveActivity extends BaseActivity implements LiftRecieveView {
    private Button back_btn;
    private RecyclerView recyclerView;
    private LiftRecievePresenter liftRecievePresenter;
    private OrderAdapter oderAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_lift_recieve_order);

        initView();
        liftRecievePresenter=new LiftRecievePresenterImpl(this);
        liftRecievePresenter.loadData(this);
        setListener();
        setOnFreshListener();
    }



    private void setListener() {
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //下拉刷新
    private void setOnFreshListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                liftRecievePresenter.loadData(LiftRecieveActivity.this);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initView(){
        back_btn= (Button) findViewById(R.id.back_btn);
        recyclerView= (RecyclerView) findViewById(R.id.order_item_rv);
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
    }

    @Override
    public void showMsg(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void setData(OrderAdapter adapter) {
        oderAdapter=adapter;
        recyclerView.setAdapter(oderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
