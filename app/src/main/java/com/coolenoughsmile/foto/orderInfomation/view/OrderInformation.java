package com.coolenoughsmile.foto.orderInfomation.view;

import android.os.Bundle;
import android.widget.TextView;

import com.coolenoughsmile.foto.R;
import com.coolenoughsmile.foto.base.BaseActivity;
import com.coolenoughsmile.foto.liftActivity.model.Order;

/**
 * Created by CoolEnoughSmile on 2017/8/3.
 */

public class OrderInformation extends BaseActivity {
    private TextView username,appointment_time,departure,destination,remarke;
//    private Button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_information);

        initView();
        loadData();
        setListener();
    }

    private void setListener() {
    }

    private void loadData() {
        Order order= (Order) getIntent().getSerializableExtra("order");
        username.setText(order.getUsername());
        appointment_time.setText(order.getAppointment_time());
        departure.setText(order.getDeparture());
        destination.setText(order.getDestination());
        remarke.setText(order.getRemarke());
    }

    private void initView() {
        username= (TextView) findViewById(R.id.username);
        appointment_time= (TextView) findViewById(R.id.appointment_time);
        departure= (TextView) findViewById(R.id.departure);
        destination= (TextView) findViewById(R.id.destination);
        remarke= (TextView) findViewById(R.id.remarke);
    }
}
