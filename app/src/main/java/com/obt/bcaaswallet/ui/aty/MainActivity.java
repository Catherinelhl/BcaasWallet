package com.obt.bcaaswallet.ui.aty;


import android.os.Bundle;
import android.view.View;

import com.hjm.bottomtabbar.BottomTabBar;
import com.obt.bcaaswallet.R;
import com.obt.bcaaswallet.base.BaseActivity;
import com.obt.bcaaswallet.ui.frg.MainFragment;
import com.obt.bcaaswallet.ui.frg.ReceiveFragment;
import com.obt.bcaaswallet.ui.frg.ScanFragment;
import com.obt.bcaaswallet.ui.frg.SendFragment;
import com.obt.bcaaswallet.ui.frg.SettingFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/15
 * <p>
 * 进入当前钱包首页
 */
public class MainActivity extends BaseActivity {

    private BottomTabBar bottomTabBar;
    private List<String> currency;


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
        bottomTabBar = findViewById(R.id.tab_bar);
        bottomTabBar.init(getSupportFragmentManager(), 720, 1280)
                .setImgSize(getResources().getDimensionPixelOffset(R.dimen.d32),
                        getResources().getDimensionPixelOffset(R.dimen.d32))
                .setFontSize(getResources().getDimensionPixelOffset(R.dimen.text_size_6))
                .setTabPadding(getResources().getDimensionPixelOffset(R.dimen.d5),
                        getResources().getDimensionPixelOffset(R.dimen.d0),
                        getResources().getDimensionPixelOffset(R.dimen.d5))
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

    @Override
    public void initListener() {
        bottomTabBar.setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
            @Override
            public void onTabChange(int position, String name, View view) {
                showToast(position);
            }
        });
    }

    public List<String> getCurrency() {
        return currency;
    }
}
