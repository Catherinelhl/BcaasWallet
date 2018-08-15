package com.obt.bcaaswallet.ui.aty;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.obt.bcaaswallet.R;
import com.obt.bcaaswallet.base.BaseActivity;
import com.obt.bcaaswallet.utils.StringU;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/15
 *
 * 是否以LoginActivity为当前账户登录的主要Activity，保持此activity不finish，然后跳转创建、或者导入
 * 钱包的界面，操作结束的时候，返回到当前页面，然后进入MainActivity。
 */
public class LoginActivity extends BaseActivity {

    private EditText etPassword;
    private Button btnUnlockWallet;
    private Button btnCreateWallet;
    private Button btnImportWallet;

    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    public int getContentView() {
        return R.layout.aty_login;
    }

    @Override
    public void initViews() {
        etPassword = findViewById(R.id.et_password);
        btnUnlockWallet = findViewById(R.id.btn_unlock_wallet);
        btnCreateWallet = findViewById(R.id.btn_create_wallet);
        btnImportWallet = findViewById(R.id.btn_import_wallet);

    }

    @Override
    public void initListener() {
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                btnUnlockWallet.setVisibility(StringU.notEmpty(s.toString()) ? View.VISIBLE : View.INVISIBLE);

            }
        });
        btnUnlockWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = etPassword.getText().toString();
                if (StringU.notEmpty(password)) {
                    intentToActivity(MainActivity.class, true);
                } else {
                    showToast(getString(R.string.walletinfo_must_not_null));
                }

            }
        });
        btnCreateWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToActivity(CreateWalletActivity.class);
            }
        });
        btnImportWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToActivity(ImportWalletActivity.class);
            }
        });

    }
}
