package com.coolenoughsmile.foto.loginActivity.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.coolenoughsmile.foto.R;
import com.coolenoughsmile.foto.base.BaseActivity;
import com.coolenoughsmile.foto.findPasswordActivity.view.FindPasswordActivity;
import com.coolenoughsmile.foto.loginActivity.presenter.LoginPresenter;
import com.coolenoughsmile.foto.loginActivity.presenter.LoginPresenterImpl;
import com.coolenoughsmile.foto.mainActivity.view.MainActivity;
import com.coolenoughsmile.foto.registerActivity.view.RegisterActivity;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by CoolEnoughSmile on 2017/7/20.
 */

public class LoginActivity extends BaseActivity  implements LoginView{

    private EditText username_edit,password_edit;
    private Button login_btn;
    private TextView forget,register;
    private ProgressBar progressBar;
    private LoginPresenter loginPresenter;
    private SimpleDraweeView logo;
    private static String DEFAULT_HEAD ="http://bmob-cdn-13292.b0.upaiyun.com/2017/08/04/46e29336d6d24f929416380afb03aa33.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter=new LoginPresenterImpl(this);
        initView();
        setOnClickListener();
    }

    private void setOnClickListener() {
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=username_edit.getText().toString().trim();
                String password=password_edit.getText().toString().trim();
                if ("".equals(username)||"".equals(password)){
                    showToast("用户名或密码为空！");
                    return;
                }
                loginPresenter.login(username,password);
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, FindPasswordActivity.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void initView() {
        username_edit= (EditText) findViewById(R.id.username_edit);
        password_edit= (EditText) findViewById(R.id.password_edit);
        login_btn= (Button) findViewById(R.id.login_btn);
        forget= (TextView) findViewById(R.id.forget_btn);
        register= (TextView) findViewById(R.id.register_btn);
        progressBar= (ProgressBar) findViewById(R.id.progress_bar);
        logo= (SimpleDraweeView) findViewById(R.id.logo);
        logo.setImageURI(DEFAULT_HEAD);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess() {
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showShort(msg);
    }
}
