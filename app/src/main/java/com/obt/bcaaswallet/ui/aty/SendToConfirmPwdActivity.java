package com.obt.bcaaswallet.ui.aty;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.obt.bcaaswallet.R;
import com.obt.bcaaswallet.base.BaseActivity;
import com.obt.bcaaswallet.contants.Contants;
import com.obt.bcaaswallet.event.SwitchTab;
import com.obt.bcaaswallet.utils.OttoU;
import com.obt.bcaaswallet.utils.StringU;

import butterknife.BindView;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/16
 * <p>
 * <p>
 * 发送页面点击「发送」然后跳转到此页面进行密码到确认，点击「确认」，进行网络的请求，然后返回到「首页」
 */
public class SendToConfirmPwdActivity extends BaseActivity {

    @BindView(R.id.etMyAccountAddress)
    EditText etMyAccountAddress;
    @BindView(R.id.etTransactionAmount)
    EditText etTransactionAmount;
    @BindView(R.id.tvCurrency)
    TextView tvCurrency;
    @BindView(R.id.etInputPwd)
    EditText etInputPwd;
    @BindView(R.id.btnSend)
    Button btnSend;
    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ibRight)
    ImageButton ibRight;

    private String receiveCurrency, receiveAddress, transactionAmount;//获取上一个页面传输过来的接收方的币种以及地址信息,以及交易数额

    @Override
    public int getContentView() {
        return R.layout.aty_send_toconfirm_pwd;
    }

    @Override
    public void getArgs(Bundle bundle) {
        if (bundle == null) return;
        receiveCurrency = bundle.getString(Contants.KeyMaps.RECEIVECURRENCY);
        receiveAddress = bundle.getString(Contants.KeyMaps.RECEIVEADDRESS);
        transactionAmount = bundle.getString(Contants.KeyMaps.TRANSACTIONAMOUNT);


    }

    @Override
    public void initViews() {
        ibBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.send));
        etTransactionAmount.setText(transactionAmount);
        etMyAccountAddress.setHint(receiveAddress);
        tvCurrency.setText(receiveCurrency);
    }

    @Override
    public void initListener() {
        etInputPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String pwd = s.toString();
                btnSend.setPressed(StringU.notEmpty(pwd));
            }
        });
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 存储当前的信息，并且返回到首页
                String pwd = etInputPwd.getText().toString();
                if (StringU.isEmpty(pwd)) {
                    showToast("请确认密码的输入！");
                } else {
                    OttoU.getInstance().post(new SwitchTab(0));
                    finish();
                }
            }
        });

    }
}
