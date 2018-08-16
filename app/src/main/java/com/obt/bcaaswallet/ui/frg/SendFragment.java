package com.obt.bcaaswallet.ui.frg;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.obt.bcaaswallet.R;
import com.obt.bcaaswallet.base.BaseFragment;
import com.obt.bcaaswallet.ui.aty.MainActivity;
import com.obt.bcaaswallet.utils.StringU;

import butterknife.BindView;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/15
 * 发送页面
 */
public class SendFragment extends BaseFragment {

    @BindView(R.id.etMyAccountAddress)
    EditText etMyAccountAddress;//我的账户地址显示容器
    @BindView(R.id.spSelectDisplayCurrency)
    Spinner spSelectDisplayCurrency;//选择当前查询显示的币种
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.tvCurrency)
    TextView tvCurrency;
    @BindView(R.id.v_line)
    View vLine;
    @BindView(R.id.spSelectAccountAddress)
    Spinner spSelectAccountAddress;//选择收款账户地址
    @BindView(R.id.tv_eg)
    TextView tvEg;
    @BindView(R.id.llTransactionSelectInfo)
    LinearLayout llTransactionSelectInfo;//将「确定」状态下需要隐藏的布局用一个容器包装起来
    @BindView(R.id.tvTransactionAmount)
    TextView tvTransactionAmount;//我的交易数额
    @BindView(R.id.etTransactionAmount)
    EditText etTransactionAmount;
    @BindView(R.id.spSelectReceiveCurrency)
    Spinner spSelectReceiveCurrency;//选择交易发送的币种
    @BindView(R.id.tvInputPwd)
    TextView tvInputPwd;//「请输入当前密码」
    @BindView(R.id.etInputPwd)
    EditText etInputPwd;//请输入当前密码输入框
    @BindView(R.id.btnSend)
    Button btnSend;

    private String currentStatus;//存储当前界面操作的状态，主要用于区分「发送」、「确定」两种状态
    private String myAccountAddress;//得到当前的账户地址
    private ArrayAdapter currencyAdapter;//声明用于填充币种的适配
    private ArrayAdapter allAccountAddressAdapter;//声明用于填充所有可选账户的地址


    @Override
    public int getLayoutRes() {
        return R.layout.frg_send;
    }

    @Override
    public void initViews(View view) {
        myAccountAddress = "3672783jshadgvhbnjbvjf===";
        etMyAccountAddress.setText(myAccountAddress);
        etMyAccountAddress.setFocusable(false);
        initData();

    }

    private void initData() {
        initSelectDisplaySpinnerAdapter();
        initReceiveAccountAddressSpinnerAdapter();

    }

    /*初始化选择显示当前想要发送的账户的数据；这里有三种方式：1：可以手动输入；2：通过扫描对方的code；3：通过选择自己本地的交易过的账户列表*/
    private void initReceiveAccountAddressSpinnerAdapter() {
        //将可选内容与ArrayAdapter连接起来
        allAccountAddressAdapter = new ArrayAdapter<>(this.context, R.layout.spinner_item, getCurrency());
        //设置下拉列表的风格
        allAccountAddressAdapter.setDropDownViewResource(R.layout.dropdown_style);
        //将adapter 添加到spinner中
        spSelectAccountAddress.setAdapter(allAccountAddressAdapter);
    }

    /*初始化选择显示当前币种的数据*/
    private void initSelectDisplaySpinnerAdapter() {
        //将可选内容与ArrayAdapter连接起来
        currencyAdapter = new ArrayAdapter<>(this.context, R.layout.spinner_item, getCurrency());
        //设置下拉列表的风格
        currencyAdapter.setDropDownViewResource(R.layout.dropdown_style);
        //将adapter 添加到spinner中
        spSelectDisplayCurrency.setAdapter(currencyAdapter);
        spSelectReceiveCurrency.setAdapter(currencyAdapter);
    }


    @Override
    public void initListener() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO  如果当前页面用户进行了点击切换其他的页面，是否需要保存当前的数据状态
                //得到当前操作的信息，然后保存，显示输入密码的view，最后确定
                String btnStatus = btnSend.getText().toString();
                boolean isSend = StringU.equals(btnStatus, getResources().getString(R.string.send));//是否是「发送」的状态
                //将当前按钮设置为「确定」，修改当前界面的状态
                btnSend.setText(getResources().getString(isSend ? R.string.sure : R.string.send));
                if (isSend) {
                } else {
                    //点击「确定」，保存数据，发起请求；回到首页,并将当前页面的状态初始化
                    ((MainActivity) activity).switchTab(0);
                }
                showSelectInfoView(!isSend);
                showInputPwdView(isSend);
                removeEtTransactionAmountInfoFocus(isSend);
                currentStatus = btnStatus;
            }
        });
        spSelectDisplayCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvCurrency.setText(String.valueOf(currencyAdapter.getItem(position)));
                tvBalance.setText(getAllTransactionData().get(position).getBalance());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spSelectReceiveCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //存储当前选中的用于交易的币种信息

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spSelectAccountAddress.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
        spSelectReceiveCurrency.setEnabled(false);
    }
}
