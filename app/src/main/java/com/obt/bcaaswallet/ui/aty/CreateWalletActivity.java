package com.obt.bcaaswallet.ui.aty;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.obt.bcaaswallet.R;
import com.obt.bcaaswallet.base.BaseActivity;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/15
 */
public class CreateWalletActivity extends BaseActivity {

    private EditText etPassword;
    private EditText etPasswordConfirm;
    private Button btnCancel;
    private Button btnSure;

    @Override
    public int getContentView() {
        return R.layout.aty_create_wallet;
    }

    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    public void initViews() {
        etPassword = findViewById(R.id.et_password);
        etPasswordConfirm = findViewById(R.id.et_password_confirm);
        btnCancel = findViewById(R.id.btn_cancel);
        btnSure = findViewById(R.id.btn_sure);

    }

    @Override
    public void initListener() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO  点击取消，回到「登录钱包」的页面？
                finish();
            }
        });
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToActivity(WalletCreatedSuccessActivity.class, true);
            }
        });

    }
}
