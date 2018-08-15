package com.obt.bcaaswallet.ui.aty;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.obt.bcaaswallet.R;
import com.obt.bcaaswallet.base.BaseActivity;
import com.obt.bcaaswallet.utils.StringU;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/15
 * <p>
 * 为新导入的钱包设置密码
 */
public class SetPwdForImportWalletActivity extends BaseActivity {
    private EditText etPassword;
    private EditText etPasswordConfirm;
    private TextView tvOpenWalletMethod;
    private Button btnCancel;
    private Button btnSure;

    @Override
    public int getContentView() {
        return R.layout.aty_set_pwd_for_import_wallet;
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
        tvOpenWalletMethod = findViewById(R.id.tvOpenWalletMethod);
        tvOpenWalletMethod.setText(getResources().getString(R.string.import_wallet));


    }

    @Override
    public void initListener() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 清空所有的数据还是停留在当前页面？
            }
        });
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = etPassword.getText().toString();
                String passwordConfirm = etPasswordConfirm.getText().toString();
                if (StringU.equals(password, passwordConfirm)) {
                    finish();//TODO 回到登录页面然后进入首页
                } else {
                    showToast(getString(R.string.confirm_two_pwd_is_consistent));
                }
            }
        });

    }
}
