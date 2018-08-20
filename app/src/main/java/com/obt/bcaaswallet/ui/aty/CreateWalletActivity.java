package com.obt.bcaaswallet.ui.aty;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.obt.bcaaswallet.R;
import com.obt.bcaaswallet.base.BaseActivity;
import com.obt.bcaaswallet.utils.StringU;
import com.obt.bcaaswallet.view.PrivateKeyEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/15
 * <p>
 * 创建新钱包
 */
public class CreateWalletActivity extends BaseActivity {


    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ibRight)
    ImageButton ibRight;
    @BindView(R.id.rlHeader)
    RelativeLayout rlHeader;
    @BindView(R.id.tv_password_rule)
    TextView tvPasswordRule;
    @BindView(R.id.btn_sure)
    Button btnSure;
    @BindView(R.id.pketPwd)
    PrivateKeyEditText pketPwd;
    @BindView(R.id.pketConfirmPwd)
    PrivateKeyEditText pketConfirmPwd;

    @Override
    public int getContentView() {
        return R.layout.aty_create_wallet;
    }

    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    public void initViews() {
        ibBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.create_new_wallet));

    }

    @Override
    public void initListener() {
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO  点击取消，回到「登录钱包」的页面？
                finish();
            }
        });
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = pketPwd.getPrivateKey();
                String confirmPwd = pketConfirmPwd.getPrivateKey();
                if (StringU.isEmpty(pwd) || StringU.isEmpty(confirmPwd)) {
                    showToast(getString(R.string.confirm_pwd_not_null));
                } else {
                    if (StringU.equals(pwd, confirmPwd)) {
                        intentToActivity(WalletCreatedSuccessActivity.class, true);
                    } else {
                        showToast(getResources().getString(R.string.confirm_two_pwd_is_consistent));
                    }

                }
            }
        });

    }
}
