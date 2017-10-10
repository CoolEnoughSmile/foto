package com.coolenoughsmile.foto.registerActivity.view;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.coolenoughsmile.foto.R;
import com.coolenoughsmile.foto.base.BaseActivity;
import com.coolenoughsmile.foto.registerActivity.presenter.RegisterPresenter;
import com.coolenoughsmile.foto.registerActivity.presenter.RegisterPresenterImpl;
import com.facebook.drawee.view.SimpleDraweeView;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by CoolEnoughSmile on 2017/7/20.
 */

public class RegisterActivity extends BaseActivity implements RegisterView{
    private EditText username_edit,password_edit,ensure_password_edit,phone_edit,security_code_edit;
    private Button get_security_code_btn,complete_btn,back_btn;
    private LinearLayout checkBoxLayout;
    private CheckBox checkBox;
    private TextView agreement_view;
    private TimeCount mTimeCount;
    private ProgressBar pb;
    private SimpleDraweeView logo;

    private static final String TAG = "RegisterActivity";

    private EventHandler eh;
    private Handler handler;

    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerPresenter=new RegisterPresenterImpl(this);
        mTimeCount = new TimeCount(60000, 1000);
        initView();
        setListener();
        handler=new Handler();
    }

    private void initEnentHandler(final String tel, final String username, final String password) {
        eh=new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {
                switch (event) {
                    case SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE:
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            //验证成功
                            Log.e(TAG, "afterEvent: 验证成功" );
                            registerPresenter.doRegister(tel,username,password);
                        } else {
                            //验证失败
                            Log.e(TAG, "afterEvent: 验证失败" );
                        }
                        break;
                    case SMSSDK.EVENT_GET_VERIFICATION_CODE:
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            //获取验证码成功
                            Log.e(TAG, "afterEvent: 获取验证码成功" );
                        } else {
                            //获取验证码失败
                            Log.e(TAG, "afterEvent: 获取验证码失败" );
                        }
                        break;
                }
            }
        };
        SMSSDK.registerEventHandler(eh);
    }

    private void setListener() {
        get_security_code_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tel=phone_edit.getText().toString().trim();
                if (!RegexUtils.isMobileExact(tel)){
                    ToastUtils.showShort("电话号码有误！");
                    Log.e("isMobileExact", tel);
                    return;
                }
                ToastUtils.showShort("60秒后可再次发送！");
                mTimeCount.start();
                registerPresenter.getSecurityCode(tel);
            }
        });

        complete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tel=phone_edit.getText().toString().trim();
                String username=username_edit.getText().toString().trim();
                String password=password_edit.getText().toString().trim();
                String ensure=ensure_password_edit.getText().toString().trim();
                String code=security_code_edit.getText().toString().trim();
                if (!password.equals(ensure)){
                    ToastUtils.showShort("密码不一致！");
                    return;
                }
                if (!checkBox.isChecked()){
                    ToastUtils.showShort("请接受条款！");
                    return;
                }
                if (!username.equals("")&&!password.equals("")&&!code.equals("")){
                    initEnentHandler(tel,username,password);
                    registerPresenter.register(tel,code);
                }else {
                    ToastUtils.showShort("请填写完整！");
                }
            }
        });

        checkBoxLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()){
                    checkBox.setChecked(false);
                }else {
                    checkBox.setChecked(true);
                }
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        username_edit= (EditText) findViewById(R.id.username_edit);
        password_edit= (EditText) findViewById(R.id.password_edit);
        ensure_password_edit= (EditText) findViewById(R.id.ensure_password_edit);
        phone_edit= (EditText) findViewById(R.id.phone_edit);
        security_code_edit= (EditText) findViewById(R.id.security_code_edit);
        get_security_code_btn= (Button) findViewById(R.id.get_security_code_btn);
        complete_btn= (Button) findViewById(R.id.complete_btn);
        checkBoxLayout= (LinearLayout) findViewById(R.id.checkbox_layout);
        checkBox= (CheckBox) findViewById(R.id.checkbox);
        agreement_view= (TextView) findViewById(R.id.agreement_view);
        back_btn= (Button) findViewById(R.id.back_btn);
        pb= (ProgressBar) findViewById(R.id.progressBar);
        logo= (SimpleDraweeView) findViewById(R.id.logo);
        logo.setImageURI("http://bmob-cdn-13292.b0.upaiyun.com/2017/08/04/46e29336d6d24f929416380afb03aa33.jpg");
   }

    @Override
    public void onRegisterSuccess() {
        ToastUtils.showShort("注册成功！");
        finish();
    }

    @Override
    public void showMsg(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void showProgressBar() {
        new Thread() {
            @Override
            public void run() {//在run()方法实现业务逻辑；
                //更新UI操作；
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        pb.setVisibility(View.VISIBLE);
                    }
                });
            }
        }.start();
    }

    @Override
    public void hideProgressBar() {
        new Thread() {
            @Override
            public void run() {//在run()方法实现业务逻辑；
                //更新UI操作；
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        pb.setVisibility(View.GONE);
                    }
                });
            }
        }.start();
    }


    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            get_security_code_btn.setClickable(false);
            get_security_code_btn.setText(l/1000 +"");
        }

        @Override
        public void onFinish() {
            get_security_code_btn.setClickable(true);
            get_security_code_btn.setText("点击获取");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }
}
