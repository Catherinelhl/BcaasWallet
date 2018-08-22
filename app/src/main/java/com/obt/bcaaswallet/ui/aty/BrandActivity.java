package com.obt.bcaaswallet.ui.aty;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.obt.bcaaswallet.R;
import com.obt.bcaaswallet.base.BaseActivity;
import com.obt.bcaaswallet.constants.Constants;
import com.obt.bcaaswallet.presenter.BrandPresenterImp;
import com.obt.bcaaswallet.ui.contracts.BrandContracts;
import com.obt.bcaaswallet.utils.LL;
import com.obt.bcaaswallet.utils.PreferenceU;
import com.obt.bcaaswallet.vo.WalletVO;


/**
 * @author catherine.brainwilliam
 * @since 2018/8/15
 */
public class BrandActivity extends BaseActivity implements BrandContracts.View {


    private BrandContracts.Presenter presenter;

    @Override
    public void getArgs(Bundle bundle) {

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int code = msg.what;
            if (code == 1) {
                intentToActivity(LoginActivity.class, true);
            } else {
                intentToActivity(
                        MainActivity.class, true);

            }
        }
    };

    @Override
    public int getContentView() {
        return R.layout.aty_brand;
    }

    @Override
    public void initViews() {
        PreferenceU.getInstance(this).saveFloat("brand",0.01f);
        presenter = new BrandPresenterImp(this);
        LL.d("initViews");
        new Thread(new Runnable() {
            @Override
            public void run() {
                presenter.queryWalletInfo();
//                handler.sendEmptyMessageDelayed(1, Constants.ValueMaps.brandSleepTime);
            }
        }).start();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void noWalletInfo() {
        handler.sendEmptyMessage(1);

    }

    @Override
    public void onLogin() {
        handler.sendEmptyMessage(2);

    }

}