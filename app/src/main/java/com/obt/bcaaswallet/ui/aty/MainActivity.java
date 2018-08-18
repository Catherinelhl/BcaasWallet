package com.obt.bcaaswallet.ui.aty;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.obt.bcaaswallet.R;
import com.obt.bcaaswallet.base.BaseActivity;
import com.obt.bcaaswallet.base.BaseFragment;
import com.obt.bcaaswallet.bean.TransactionsBean;
import com.obt.bcaaswallet.event.SwitchTab;
import com.obt.bcaaswallet.event.UpdateAddressEvent;
import com.obt.bcaaswallet.ui.frg.MainFragment;
import com.obt.bcaaswallet.ui.frg.ReceiveFragment;
import com.obt.bcaaswallet.ui.frg.ScanFragment;
import com.obt.bcaaswallet.ui.frg.SendFragment;
import com.obt.bcaaswallet.ui.frg.SettingFragment;
import com.obt.bcaaswallet.utils.OttoU;
import com.obt.qrcode.activity.CaptureActivity;
import com.squareup.otto.Subscribe;

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

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tab_bar)
    BottomNavigationBar tabBar;
    private List<String> currency;//获取所有的币种
    private List<TransactionsBean> allCurrency;//获取所有的币种以及相关的交易信息

    private String addressOfUser;//用户的账户地址
    private List<BaseFragment> mFragmentList;
    private Fragment currentFragment;
    private int currentIndex;


    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    public int getContentView() {
        return R.layout.aty_main;
    }

    @Override
    public void initViews() {
        mFragmentList = new ArrayList<>();
        initCurrency();
        initCurrencyData();
        initFragment();
        initNavigation();
        setMainTitle();
    }

    private void initNavigation() {
        tabBar.clearAll();
        tabBar.addItem(new BottomNavigationItem(R.mipmap.icon_home_f, getString(R.string.main)).setInactiveIconResource(R.mipmap.icon_home))
                .addItem(new BottomNavigationItem(R.mipmap.icon_receive_f, getString(R.string.receive)).setInactiveIconResource(R.mipmap.icon_receive))
                .addItem(new BottomNavigationItem(R.mipmap.icon_scan_f, getString(R.string.scan)).setInactiveIconResource(R.mipmap.icon_scan))
                .addItem(new BottomNavigationItem(R.mipmap.icon_send_f, getString(R.string.send)).setInactiveIconResource(R.mipmap.icon_send))
                .addItem(new BottomNavigationItem(R.mipmap.icon_setting_f, getString(R.string.setting)).setInactiveIconResource(R.mipmap.icon_setting))
                .setFirstSelectedPosition(0)
                .initialise();
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
        tabBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                //未选中->选中
                showFragment(position);
                switch (position) {
                    case 0:
                        tvTitle.setText(getResources().getString(R.string.main));
                        break;
                    case 1:
                        tvTitle.setText(getResources().getString(R.string.receive));
                        break;
                    case 2:
                        tvTitle.setText(getResources().getString(R.string.scan));
                        switchTab(0);
                        setMainTitle();
                        intentToCaptureAty();
                        break;
                    case 3:
                        tvTitle.setText(getResources().getString(R.string.send));
                        break;
                    case 4:
                        tvTitle.setText(getResources().getString(R.string.setting));
                        break;
                }
            }

            @Override
            public void onTabUnselected(int position) {
                //选中->未选中
            }

            @Override
            public void onTabReselected(int position) {
                //选中->选中
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
        tabBar.selectTab(position);
        if (position == 0) {
            setMainTitle();
        }
    }

    private void setMainTitle() {
        tvTitle.setText(getResources().getString(R.string.bcaas_u));
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
                switchTab(3);//扫描成功，然后将当前扫描数据存储，然后跳转到发送页面
                OttoU.getInstance().post(new UpdateAddressEvent(result));
            }
        }
    }

    @Subscribe
    public void updateAddressEvent(UpdateAddressEvent updateAddressEvent) {
        System.out.println("UpdateAddressEvent" + updateAddressEvent);
        if (updateAddressEvent == null) return;
        String result = updateAddressEvent.getResult();
        showToast(result);
    }

    @Subscribe
    public void switchTab(SwitchTab switchTab) {
        if (switchTab == null) return;
        switchTab(switchTab.getPosition());
    }

    private void initFragment() {
        //tab 和 fragment 联动
        MainFragment mainFragment = MainFragment.newInstance();
        mFragmentList.add(mainFragment);
        ReceiveFragment receiveFragment = ReceiveFragment.newInstance();
        mFragmentList.add(receiveFragment);
        ScanFragment scanFragment = ScanFragment.newInstance();
        mFragmentList.add(scanFragment);
        SendFragment sendFragment = SendFragment.newInstance();
        mFragmentList.add(sendFragment);
        SettingFragment settingFragment = SettingFragment.newInstance();
        mFragmentList.add(settingFragment);
    }

    private void showFragment(int position) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        BaseFragment fragment = mFragmentList.get(position);
        currentFragment = fragment;
        if (!fragment.isAdded()) {
            ft.add(R.id.fl_module, fragment);
        }
        ft.show(fragment);
        if (currentIndex != position) {
            ft.hide(mFragmentList.get(currentIndex));
            currentIndex = position;
        }
        ft.commitAllowingStateLoss();
    }

}
