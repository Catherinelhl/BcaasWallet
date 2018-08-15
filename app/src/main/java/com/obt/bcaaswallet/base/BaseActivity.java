package com.obt.bcaaswallet.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/15
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        initViews();
        initListener();
    }

    public abstract int getContentView();

    public abstract void initViews();

    public abstract void initListener();


}
