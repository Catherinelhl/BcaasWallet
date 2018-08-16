package com.obt.bcaaswallet.ui.aty;


import android.graphics.Typeface;
import android.os.Bundle;
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
import com.obt.bcaaswallet.utils.StringU;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

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
                .setImgSize(72,
                        72)
                .setFontSize(16)
                .setTabPadding(5,
                        getResources().getDimensionPixelOffset(R.dimen.d0),
                        5)
                .setChangeColor(getResources().getColor(R.color.black),
                        getResources().getColor(R.color.grey))
                .addTabItem(getString(R.string.main), R.mipmap.ic_launcher, MainFragment.class)
                .addTabItem(getString(R.string.receive), R.mipmap.ic_launcher, ReceiveFragment.class)
                .addTabItem(getString(R.string.scan), R.mipmap.ic_launcher, ScanFragment.class)
                .addTabItem(getString(R.string.send), R.mipmap.ic_launcher, SendFragment.class)
                .addTabItem(getString(R.string.setting), R.mipmap.ic_launcher, SettingFragment.class);

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

                        break;
                    case 3:
                        title = getResources().getString(R.string.send);
                        color = getResources().getColor(R.color.transparent);
                        textColor = getResources().getColor(R.color.black);

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
        tvTitle.setText(getResources().getString(R.string.bcaas_u));
        tvTitle.setBackgroundColor(getResources().getColor(R.color.red2));
        tvTitle.setTextColor(getResources().getColor(R.color.white));
    }

}
