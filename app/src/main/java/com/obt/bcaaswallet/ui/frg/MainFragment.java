package com.obt.bcaaswallet.ui.frg;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.obt.bcaaswallet.R;
import com.obt.bcaaswallet.adapter.PendingTransactionAdapter;
import com.obt.bcaaswallet.base.BaseFragment;
import com.obt.bcaaswallet.base.BcaasApplication;
import com.obt.bcaaswallet.bean.TransactionsBean;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/15
 * <p>
 * '首页
 */
public class MainFragment extends BaseFragment {
    private String accountAddress;//我的账户地址
    private String balance;//当前币种下面的余额

    private Spinner spSelect;
    private TextView tvBalance, tvCurrency;
    private RecyclerView rvPendingTransaction;
    private EditText etAccountAddress;// 显示当前账户地址的容器
    private ArrayAdapter adapter;
    private List<String> currency;
    private List<TransactionsBean> allCurrency;
    private PendingTransactionAdapter pendingTransactionAdapter;//待交易数据
    private List<TransactionsBean> transactionsBeanList;

    @Override
    public int getLayoutRes() {
        return R.layout.frg_main;
    }

    @Override
    public void initViews(View view) {
        initCurrencyData();
        initTransactionList();
        spSelect = view.findViewById(R.id.sp_select);
        rvPendingTransaction = view.findViewById(R.id.rvPendingTransaction);
        tvBalance = view.findViewById(R.id.tv_balance);
        tvCurrency = view.findViewById(R.id.tvCurrency);
        etAccountAddress = view.findViewById(R.id.myAccountAddress);
        accountAddress = "asdjfnaks.jnfak.jdsnfkm===";
        etAccountAddress.setText(accountAddress);
        //将可选内容与ArrayAdapter连接起来
        adapter = new ArrayAdapter(this.context, R.layout.spinner_item, currency);
//        ArrayAdapter.createFromResource(this, R.array.songs, android.R.layout.simple_spinner_item);

        //设置下拉列表的风格
        adapter.setDropDownViewResource(R.layout.dropdown_style);
//        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        //将adapter2 添加到spinner中
        spSelect.setAdapter(adapter);
        initTransactionsAdapter();


    }

    private void initTransactionList() {
        transactionsBeanList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            TransactionsBean transactionsBean = new TransactionsBean(i + "asdkjfbakjhsdvfjahvfaghvdfh==",
                    allCurrency.get(i).getBalance(),
                    currency.get(i / 2));
            transactionsBeanList.add(transactionsBean);
        }
    }

    private void initTransactionsAdapter() {
        pendingTransactionAdapter = new PendingTransactionAdapter(this.context, transactionsBeanList);
        rvPendingTransaction.setHasFixedSize(true);
        rvPendingTransaction.setLayoutManager(new LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false));
        rvPendingTransaction.setAdapter(pendingTransactionAdapter);
    }

    private void initCurrencyData() {
        allCurrency = new ArrayList<>();
        currency = new ArrayList<>();
        currency.add("BCC");
        currency.add("TCC");
        currency.add("BCL");
        currency.add("TCH");
        Random random = new Random();
        for (int i = 0; i < currency.size(); i++) {
            int rand = random.nextInt(9999) + 10000;
            TransactionsBean transactionsBean = new TransactionsBean("asdfafas==", String.valueOf(rand), currency.get(i));
            allCurrency.add(transactionsBean);
        }

    }

    @Override
    public void initListener() {
        //添加事件Spinner事件监听
        spSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvCurrency.setText(String.valueOf(adapter.getItem(position)));
                tvBalance.setText(allCurrency.get(position).getBalance());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
