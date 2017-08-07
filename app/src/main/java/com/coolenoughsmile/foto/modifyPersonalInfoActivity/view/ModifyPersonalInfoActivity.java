package com.coolenoughsmile.foto.modifyPersonalInfoActivity.view;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.blankj.utilcode.util.ToastUtils;
import com.coolenoughsmile.foto.R;
import com.coolenoughsmile.foto.base.BaseActivity;
import com.coolenoughsmile.foto.loginActivity.model.User;
import com.coolenoughsmile.foto.modifyPersonalInfoActivity.presenter.ModifyPersonalInfoPresenter;
import com.coolenoughsmile.foto.modifyPersonalInfoActivity.presenter.ModifyPersonalInfoPresenterImpl;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.bmob.v3.BmobUser;
import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;

/**
 * Created by CoolEnoughSmile on 2017/7/27.
 */

public class ModifyPersonalInfoActivity  extends BaseActivity implements ModifyPersonalInfoView{
    private TextView sex,birthday,school,back_btn,username;
    private EditText else_name;
    private Button complete_btn;
    private ModifyPersonalInfoPresenter presenter;
    private ProgressBar pb;
    private SimpleDraweeView user_logo;

    private static String DEFAULT_HEAD ="http://bmob-cdn-13292.b0.upaiyun.com/2017/08/04/46e29336d6d24f929416380afb03aa33.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);

        initView();
        loadData();
        setListener();
        presenter=new ModifyPersonalInfoPresenterImpl(this);
    }

    private void loadData() {
        User user= BmobUser.getCurrentUser(User.class);
        username.setText(user.getUsername());
        if (user.getmElsename()!=null&&!"".equals(user.getmElsename())){
            else_name.setText(user.getmElsename());
        }
        if (user.getmSex()!=null&&!"".equals(user.getmSex())){
            sex.setText(user.getmSex());
        }
        if (user.getmBirthday()!=null&&!"".equals(user.getmBirthday())){
            birthday.setText(user.getmBirthday());
        }
        if (user.getmSchool()!=null&&!"".equals(user.getmSchool())){
            school.setText(user.getmSchool());
        }
        //加载头像
        String logo=user.getmLogo();
        if (logo != null && !logo.equals("")) {
            Uri uri = Uri.parse(logo);
            user_logo.setImageURI(uri);
        } else {
            user_logo.setImageURI(DEFAULT_HEAD);
        }
    }

    private void setListener() {
        sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OptionPicker picker = new OptionPicker(ModifyPersonalInfoActivity.this, new String[]{
                        "女", "男"
                });
                picker.setCanceledOnTouchOutside(false);
                picker.setDividerRatio(WheelView.DividerConfig.FILL);
                picker.setShadowColor(Color.YELLOW);
                picker.setSelectedIndex(1);
                picker.setCycleDisable(true);
                picker.setTextSize(16);
                picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        sex.setText(item);
                    }
                });
                picker.show();
            }
        });

        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //时间选择器
                TimePickerView pvTime = new TimePickerView.Builder(ModifyPersonalInfoActivity.this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        birthday.setText(getTime(date));
                    }
                }).setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                        .setCancelText("取消")//取消按钮文字
                        .setSubmitText("确认")//确认按钮文字
                        .setContentSize(18)//滚轮文字大小
                        .setTitleSize(20)//标题文字大小
                        .setTitleText("Title")//标题文字
                        .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                        .isCyclic(true)//是否循环滚动
                        .setTitleColor(Color.BLACK)//标题文字颜色
                        .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                        .setCancelColor(Color.BLUE)//取消按钮文字颜色
                        .setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
                        .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
//                        .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
//                        .setRangDate(startDate,endDate)//起始终止年月日设定
                        .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        .isDialog(false)//是否显示为对话框样式
                        .build();
                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();
            }
        });

        school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OptionPicker picker = new OptionPicker(ModifyPersonalInfoActivity.this, new String[]{
                        "天津工业大学", "天津理工大学","天津师范大学"
                });
                picker.setCanceledOnTouchOutside(false);
                picker.setDividerRatio(WheelView.DividerConfig.FILL);
                picker.setShadowColor(Color.YELLOW);
                picker.setSelectedIndex(1);
                picker.setCycleDisable(true);
                picker.setTextSize(16);
                picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        school.setText(item);
                    }
                });
                picker.show();
          }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        complete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(else_name.getText().toString().trim())){
                    ToastUtils.showShort("请输入昵称");
                    return;
                }
                if (TextUtils.isEmpty(sex.getText().toString().trim())){
                    ToastUtils.showShort("请输入性别");
                    return;
                }
                if (TextUtils.isEmpty(birthday.getText().toString().trim())){
                    ToastUtils.showShort("请输入生日");
                    return;
                }
                if (TextUtils.isEmpty(school.getText().toString().trim())){
                    ToastUtils.showShort("请输入学校");
                    return;
                }
                presenter.modify(else_name.getText().toString().trim(),sex.getText().toString().trim(),birthday.getText().toString().trim(),school.getText().toString().trim());
            }
        });
    }
    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
        return format.format(date);
    }

    private void initView() {
        sex= (TextView) findViewById(R.id.sex);
        birthday= (TextView) findViewById(R.id.birthday);
        school= (TextView) findViewById(R.id.school);
        back_btn= (TextView) findViewById(R.id.back_btn);
        else_name= (EditText) findViewById(R.id.else_name);
        username= (TextView) findViewById(R.id.username);
        complete_btn= (Button) findViewById(R.id.complete_btn);
        pb= (ProgressBar) findViewById(R.id.pb);
        user_logo= (SimpleDraweeView) findViewById(R.id.user_logo);
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
    public void success() {
        ToastUtils.showShort("修改成功！");
        finish();
    }

    @Override
    public void failure() {
        ToastUtils.showShort("修改失败，请检查网络！");
    }
}
