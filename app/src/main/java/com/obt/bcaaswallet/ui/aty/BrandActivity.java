package com.obt.bcaaswallet.ui.aty;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.obt.bcaaswallet.R;
import com.obt.bcaaswallet.base.BaseActivity;
import com.obt.bcaaswallet.contants.Contants;


/**
 * @author catherine.brainwilliam
 * @since 2018/8/15
 */
public class BrandActivity extends BaseActivity {

    @Override
    public void getArgs(Bundle bundle) {

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            intentToActivity(LoginActivity.class,true);
        }
    };

    @Override
    public int getContentView() {
        return R.layout.aty_brand;
    }

    @Override
    public void initViews() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessageDelayed(1, Contants.ValueMaps.brandSleepTime);
            }
        }).start();
    }

    @Override
    public void initListener() {

    }
}
