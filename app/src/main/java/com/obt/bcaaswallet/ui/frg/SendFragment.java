package com.obt.bcaaswallet.ui.frg;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.obt.bcaaswallet.R;
import com.obt.bcaaswallet.base.BaseFragment;
import com.obt.bcaaswallet.utils.StringU;

import org.w3c.dom.Text;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/15
 * 发送页面
 */
public class SendFragment extends BaseFragment {

    private EditText etMyAccountAddress;//我的账户地址显示容器
    private EditText etTransactionAmount;//我的交易数额
    private Spinner spSelectDisplayCurrency;//选择当前查询显示的币种
    private Spinner spSelectReceiveAccountAddress;//选择收款账户地址
    private Spinner spSelectReceiveCurrency;//选择交易发送的币种
    private Button btnSend;
    private LinearLayout llTransactionSelectInfo;//将「确定」状态下需要隐藏的布局用一个容器包装起来

    private TextView tvInputPwd;//「请输入当前密码」
    private EditText etInputPwd;//请输入当前密码输入框

    private String currentStatus;//存储当前界面操作的状态，主要用于区分「发送」、「确定」两种状态
    private String myAccountAddress;//得到当前的账户地址

    @Override
    public int getLayoutRes() {
        return R.layout.frg_send;
    }

    @Override
    public void initViews(View view) {
        myAccountAddress = "3672783jshadgvhbnjbvjf===";
        etMyAccountAddress = view.findViewById(R.id.etMyAccountAddress);
        etTransactionAmount = view.findViewById(R.id.etTransactionAmount);
        spSelectDisplayCurrency = view.findViewById(R.id.spSelectDisplayCurrency);
        spSelectReceiveAccountAddress = view.findViewById(R.id.spSelectAccountAddress);
        spSelectReceiveCurrency = view.findViewById(R.id.spSelectReceiveCurrency);
        llTransactionSelectInfo = view.findViewById(R.id.llTransactionSelectInfo);
        btnSend = view.findViewById(R.id.btnSend);
        tvInputPwd = view.findViewById(R.id.tvInputPwd);
        etInputPwd = view.findViewById(R.id.etInputPwd);
        etMyAccountAddress.setText(myAccountAddress);
        etMyAccountAddress.setFocusable(false);

    }

    @Override
    public void initListener() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO  如果当前页面用户进行了点击切换其他的页面，是否需要保存当前的数据状态
                //得到当前操作的信息，然后保存，显示输入密码的view，最后确定
                currentStatus = btnSend.getText().toString();
                boolean isSend = StringU.equals(currentStatus, getResources().getString(R.string.send));//是否是「发送」的状态
                if (isSend) {
                    //将当前按钮设置为「确定」，修改当前界面的状态
                    btnSend.setText(getResources().getString(R.string.sure));
                } else {
                    //点击「确定」，保存数据，发起请求；回到首页,并将当前页面的状态初始化

                }
                showSelectInfoView(!isSend);
                showInputPwdView(isSend);
                removeEtTransactionAmountInfoFocus(isSend);


            }
        });

    }

    private void showSelectInfoView(boolean isShow) {//是否显示选择信息View
        llTransactionSelectInfo.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    private void showInputPwdView(boolean isShow) {//是否显示交易密码的View
        tvInputPwd.setVisibility(isShow ? View.VISIBLE : View.GONE);
        etInputPwd.setVisibility(isShow ? View.VISIBLE : View.GONE);

    }

    private void removeEtTransactionAmountInfoFocus(boolean isRemove) {//移除操作交易信息的焦点
        etTransactionAmount.setFocusable(false);
        spSelectReceiveCurrency.setFocusable(false);
    }

}
