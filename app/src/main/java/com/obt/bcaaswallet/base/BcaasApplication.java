package com.obt.bcaaswallet.base;

import android.app.Application;
import android.content.Context;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/15
 */
public class BcaasApplication extends Application {

    protected static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
    }
}
