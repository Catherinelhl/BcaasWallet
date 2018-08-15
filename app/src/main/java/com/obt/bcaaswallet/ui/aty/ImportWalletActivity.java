package com.obt.bcaaswallet.ui.aty;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.obt.bcaaswallet.R;
import com.obt.bcaaswallet.base.BaseActivity;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/15
 */
public class ImportWalletActivity extends BaseActivity {

    private TextView tvOpenWalletMethod;
    private Button btnCancel;
    private Button btnSure;

    @Override
    public int getContentView() {
        return R.layout.aty_import_wallet;
    }

    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    public void initViews() {
        tvOpenWalletMethod = findViewById(R.id.tvOpenWalletMethod);
        btnCancel = findViewById(R.id.btn_cancel);
        btnSure = findViewById(R.id.btn_sure);
        tvOpenWalletMethod.setText(getResources().getString(R.string.import_wallet));

    }

    @Override
    public void initListener() {
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToActivity(SetPwdForImportWalletActivity.class);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//TODO 回到登录界面
            }
        });

    }
}
