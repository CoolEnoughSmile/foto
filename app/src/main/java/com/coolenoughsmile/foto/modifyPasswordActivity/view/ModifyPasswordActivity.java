package com.coolenoughsmile.foto.modifyPasswordActivity.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.blankj.utilcode.util.ToastUtils;
import com.coolenoughsmile.foto.R;
import com.coolenoughsmile.foto.base.BaseActivity;
import com.coolenoughsmile.foto.modifyPasswordActivity.presenter.ModifyPasswordPresenter;
import com.coolenoughsmile.foto.modifyPasswordActivity.presenter.ModifyPasswordPresenterImpl;

/**
 * Created by CoolEnoughSmile on 2017/7/27.
 */

public class ModifyPasswordActivity extends BaseActivity implements View.OnClickListener,ModifyPasswordView{
    private Button back_btn,save_btn;
    private EditText old_password,new_password,ensure;
    private ModifyPasswordPresenter modifyPasswordPresenter;
    private ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);

        modifyPasswordPresenter=new ModifyPasswordPresenterImpl(this);
        initView();
        setListener();
    }

    private void setListener() {
        back_btn.setOnClickListener(this);
        save_btn.setOnClickListener(this);
    }

    private void initView() {
        back_btn= (Button) findViewById(R.id.back_btn);
        save_btn= (Button) findViewById(R.id.save_btn);
        old_password= (EditText) findViewById(R.id.old_password);
        new_password= (EditText) findViewById(R.id.new_password);
        ensure= (EditText) findViewById(R.id.ensure);
        pb= (ProgressBar) findViewById(R.id.pb);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.save_btn:
                if (TextUtils.isEmpty(old_password.getText().toString().trim())){
                    ToastUtils.showShort("请输入旧密码！");
                    return;
                }
                if (TextUtils.isEmpty(new_password.getText().toString().trim())){
                    ToastUtils.showShort("请输入新密码！");
                    return;
                }
                if (TextUtils.isEmpty(ensure.getText().toString().trim())){
                    ToastUtils.showShort("请确认新密码！");
                    return;
                }
                if (!new_password.getText().toString().trim().equals(ensure.getText().toString().trim())){
                    ToastUtils.showShort("密码不一致！");
                    return;
                }
                modifyPasswordPresenter.modifyPassword(new_password.getText().toString().trim());
                break;
            default:
                break;
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
    public void success() {
        ToastUtils.showShort("修改成功！");
        finish();
    }

    @Override
    public void failure() {
        ToastUtils.showShort("修改失败，请检查网络！");
    }
}
