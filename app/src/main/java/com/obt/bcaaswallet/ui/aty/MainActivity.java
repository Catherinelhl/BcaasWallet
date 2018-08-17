package com.obt.bcaaswallet.ui.aty;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.hjm.bottomtabbar.BottomTabBar;
import com.obt.bcaaswallet.R;
import com.obt.bcaaswallet.base.BaseActivity;
import com.obt.bcaaswallet.bean.TransactionsBean;
import com.obt.bcaaswallet.ui.frg.MainFragment;
import com.obt.bcaaswallet.ui.frg.ReceiveFragment;
import com.obt.bcaaswallet.ui.frg.ScanFragment;
import com.obt.bcaaswallet.ui.frg.SendFragment;
import com.obt.bcaaswallet.ui.frg.SettingFragment;
import com.obt.qrcode.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/15
 * <p>
 * 进入当前钱包首页
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tab_bar)
    BottomTabBar tabBar;
    private List<String> currency;//获取所有的币种
    private List<TransactionsBean> allCurrency;//获取所有的币种以及相关的交易信息

    private String addressOfUser;//用户的账户地址


    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    public int getContentView() {
        return R.layout.aty_main;
    }

    @Override
    public void initViews() {
        initCurrency();
        initCurrencyData();
        tabBar.init(getSupportFragmentManager(), 720, 1280)
                .setImgSize(50,
                        50)
                .setFontSize(16)
                .setTabPadding(getResources().getDimensionPixelOffset(R.dimen.d4),
                        getResources().getDimensionPixelOffset(R.dimen.d3),
                        getResources().getDimensionPixelOffset(R.dimen.d4))
                .setChangeColor(getResources().getColor(R.color.black),
                        getResources().getColor(R.color.black2c))
                .addTabItem(getString(R.string.main), R.mipmap.icon_home_f, R.mipmap.icon_home, MainFragment.class)
                .addTabItem(getString(R.string.receive), R.mipmap.icon_receive_f, R.mipmap.icon_receive, ReceiveFragment.class)
                .addTabItem(getString(R.string.scan), R.mipmap.icon_scan_f, R.mipmap.icon_scan, ScanFragment.class)
                .addTabItem(getString(R.string.send), R.mipmap.icon_send_f, R.mipmap.icon_send, SendFragment.class)
                .addTabItem(getString(R.string.setting), R.mipmap.icon_setting_f, R.mipmap.icon_setting, SettingFragment.class);
        setMainTitle();

    }

    private void initCurrency() {
        currency = new ArrayList<>();
        currency.add("BCC");
        currency.add("TCC");
        currency.add("BCL");
        currency.add("TCH");
    }

    private void initCurrencyData() {
        allCurrency = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < getCurrency().size(); i++) {
            int rand = random.nextInt(9999) + 10000;
            TransactionsBean transactionsBean = new TransactionsBean("asdfafas==", String.valueOf(rand), getCurrency().get(i));
            allCurrency.add(transactionsBean);
        }

    }

    @Override
    public void initListener() {
        tabBar.setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
            @Override
            public void onTabChange(int position, String name, View view) {
                String title = getResources().getString(R.string.bcaas_u);
                int color = getResources().getColor(R.color.red2);
                int textColor = getResources().getColor(R.color.white);
                switch (position) {
                    case 0:
                        title = getResources().getString(R.string.bcaas_u);
                        color = getResources().getColor(R.color.red2);
                        textColor = getResources().getColor(R.color.white);
                        break;
                    case 1:
                        title = getResources().getString(R.string.receive);
                        color = getResources().getColor(R.color.transparent);
                        textColor = getResources().getColor(R.color.black);

                        break;
                    case 2:
                        title = getResources().getString(R.string.scan);
                        color = getResources().getColor(R.color.transparent);
                        textColor = getResources().getColor(R.color.black);
                        intentToCaptureAty();
                        break;
                    case 3:
                        title = getResources().getString(R.string.send);
                        color = getResources().getColor(R.color.transparent);
                        textColor = getResources().getColor(R.color.black);
                        showToast("asdfasd");
                        break;
                    case 4:
                        title = getResources().getString(R.string.setting);
                        color = getResources().getColor(R.color.transparent);
                        textColor = getResources().getColor(R.color.black);
                        break;

                }
                tvTitle.setText(title);
                tvTitle.setBackgroundColor(color);
                tvTitle.setTextColor(textColor);


            }
        });
    }

    private void intentToCaptureAty() {
        startActivityForResult(new Intent(this, CaptureActivity.class), 0);

    }

    public List<String> getCurrency() {
        return currency;
    }

    public List<TransactionsBean> getAllCurrencyData() {
        return allCurrency;
    }

    //切换当前底部栏的tab
    public void switchTab(int position) {
        if (tabBar == null) return;
        tabBar.setCurrentTab(position);
        setMainTitle();
    }

    private void setMainTitle() {
        tvTitle.setText(getResources().getString(R.string.bcaas_u));
        tvTitle.setBackgroundColor(getResources().getColor(R.color.red2));
        tvTitle.setTextColor(getResources().getColor(R.color.white));
    }

    public String getAddressOfUser() {
        addressOfUser = "ajkdbfnaskdjbfjhasdbf===";
        return addressOfUser;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (data == null) return;
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                String result = bundle.getString("result");
                //TODO 存储当前的扫描结果？
                tabBar.setCurrentTab(3);//扫描成功，然后将当前扫描数据存储，然后跳转到发送页面
            }
        }

    }
}
