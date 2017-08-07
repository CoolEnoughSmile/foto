package com.coolenoughsmile.foto.liftActivity.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.blankj.utilcode.util.ToastUtils;
import com.coolenoughsmile.foto.R;
import com.coolenoughsmile.foto.base.BaseActivity;
import com.coolenoughsmile.foto.liftActivity.model.Order;
import com.coolenoughsmile.foto.liftActivity.presenter.LiftPresenter;
import com.coolenoughsmile.foto.liftActivity.presenter.LiftPresenterImpl;
import com.coolenoughsmile.foto.mapActivity.LocationActivity;
import com.coolenoughsmile.foto.orderInfomation.view.OrderInformation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by CoolEnoughSmile on 2017/7/27.
 */

public class LiftActivity extends BaseActivity implements  LiftView{

    private final static int DEPARTURE_REQUEST_CODE = 0x123;
    private final static int DESTINATION_REQUEST_CODE = 0x124;
    private TextView lift_departure_edit,lift_destination_edit,lift_appointment_time_edit;
    private Button lift_back_btn,lift_submit_btn;
    private RadioButton[] radioButtons;
    private EditText lift_phone_edit,lift_remarke_edit;
    private LiftPresenter liftPresenter;
    private ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lift);

        liftPresenter=new LiftPresenterImpl(this);
        initView();
        setListener();
    }

    private void setListener() {
        lift_departure_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(LiftActivity.this, LocationActivity.class), DEPARTURE_REQUEST_CODE);
            }
        });
        lift_destination_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(LiftActivity.this, LocationActivity.class), DESTINATION_REQUEST_CODE);
            }
        });
        lift_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        lift_appointment_time_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //时间选择器
                TimePickerView pvTime = new TimePickerView.Builder(LiftActivity.this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        lift_appointment_time_edit.setText(getTime(date));
                    }
                }).setType(new boolean[]{false, true, true, true, true, false})// 默认全部显示
                        .setCancelText("取消")//取消按钮文字
                        .setSubmitText("确定")//确认按钮文字
                        .setContentSize(18)//滚轮文字大小
                        .setTitleSize(20)//标题文字大小
                        .setTitleText("选择预约时间")//标题文字
                        .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                        .isCyclic(true)//是否循环滚动
                        .setTitleColor(Color.BLACK)//标题文字颜色
                        .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                        .setCancelColor(Color.BLUE)//取消按钮文字颜色
                        .setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
                        .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
//                        .setRangDate()//起始终止年月日设定
                        .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        .isDialog(false)//是否显示为对话框样式
                        .build();
                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();
            }
        });
        lift_submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(lift_departure_edit.getText().toString().trim())){
                    ToastUtils.showShort("请填写出发地！");
                    return;
                }
                if (TextUtils.isEmpty(lift_destination_edit.getText().toString().trim())){
                    ToastUtils.showShort("请填写目的地！");
                    return;
                }
                if (TextUtils.isEmpty(lift_appointment_time_edit.getText().toString().trim())){
                    ToastUtils.showShort("请填写预约时间！");
                    return;
                }
                if (TextUtils.isEmpty(lift_phone_edit.getText().toString().trim())){
                    ToastUtils.showShort("请填写联系电话！");
                    return;
                }
                String departure=lift_departure_edit.getText().toString().trim();
                String destination=lift_destination_edit.getText().toString().trim();
                String appointment_time=lift_appointment_time_edit.getText().toString().trim();
                String phone=lift_phone_edit.getText().toString().trim();
                String remarke=lift_remarke_edit.getText().toString().trim();
                String type ="";
                for (int i=0;i<radioButtons.length;i++){
                    if (radioButtons[i].isChecked()){
                        type=radioButtons[i].getText().toString().trim();
                    }
                }
                liftPresenter.submitOrder(departure,destination,appointment_time, type,phone,remarke);
            }
        });
    }
    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
        return format.format(date);
    }
    private void initView() {
        lift_departure_edit= (TextView) findViewById(R.id.lift_departure_edit);
        lift_destination_edit= (TextView) findViewById(R.id.lift_destination_edit);
        lift_appointment_time_edit= (TextView) findViewById(R.id.lift_appointment_time_edit);
        lift_back_btn= (Button) findViewById(R.id.lift_back_btn);
        lift_submit_btn= (Button) findViewById(R.id.lift_submit_btn);
        lift_phone_edit= (EditText) findViewById(R.id.lift_phone_edit);
        lift_remarke_edit= (EditText) findViewById(R.id.lift_remarke_edit);
        pb= (ProgressBar) findViewById(R.id.pb);
        radioButtons=new RadioButton[3];
        radioButtons[0]= (RadioButton) findViewById(R.id.r0);
        radioButtons[1]= (RadioButton) findViewById(R.id.r1);
        radioButtons[2]= (RadioButton) findViewById(R.id.r2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 返回成功
        if (resultCode == RESULT_OK && requestCode == DEPARTURE_REQUEST_CODE && data != null) {
            String position = data.getStringExtra("position");
            lift_departure_edit.setText(position);
        }
        if (resultCode == RESULT_OK && requestCode == DESTINATION_REQUEST_CODE && data != null) {
            String position = data.getStringExtra("position");
            lift_destination_edit.setText(position);
        }
    }

    @Override
    public void showProgressBar() {
        pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        pb.setVisibility(View.GONE);
    }

    @Override
    public void success(Order order) {
        ToastUtils.showShort("提交订单成功！");
        startActivity(new Intent(this, OrderInformation.class).putExtra("order",order));
        finish();
    }

    @Override
    public void failure() {
        ToastUtils.showShort("提交订单失败，请检查网络！");
    }
}
