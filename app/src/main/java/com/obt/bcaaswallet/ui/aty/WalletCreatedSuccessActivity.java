package com.obt.bcaaswallet.ui.aty;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.obt.bcaaswallet.R;
import com.obt.bcaaswallet.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/15
 * 钱包创建成功
 */
public class WalletCreatedSuccessActivity extends BaseActivity {

    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ibRight)
    ImageButton ibRight;
    @BindView(R.id.rlHeader)
    RelativeLayout rlHeader;
    @BindView(R.id.et_account_address)
    EditText etAccountAddress;
    @BindView(R.id.et_private_key)
    EditText etPrivateKey;
    @BindView(R.id.cbPwd)
    CheckBox cbPwd;
    @BindView(R.id.btn_finish)
    Button btnFinish;
    private String accountAddress, privateKey;// 账户地址，私钥

    @Override
    public int getContentView() {
        return R.layout.aty_wallet_created_success;
    }

    @Override
    public void getArgs(Bundle bundle) {


    }

    @Override
    public void initViews() {
        accountAddress = "akdsfhjaihdsgfoilasjdfiuadshjfnkuiuahdsjfnkahsjznckuiaHbdfw8asiu===";
        privateKey = "34567890-4567890467895678¬";
        etAccountAddress.setHint(accountAddress);
        etPrivateKey.setText(privateKey);
        etPrivateKey.setFocusable(false);
        etAccountAddress.setFocusable(false);
        tvTitle.setText(getResources().getString(R.string.create_new_wallet));
    }

    @Override
    public void initListener() {
        cbPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                etPrivateKey.setInputType(isChecked ?
                        InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD :
                        InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);//设置当前私钥显示不可见
            }
        });
        btnFinish.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
