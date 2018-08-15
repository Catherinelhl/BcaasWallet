package com.obt.bcaaswallet.base;

import android.app.Application;
import android.content.Context;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/15
 */
public class BcaasApplication extends Application {
    private static BcaasApplication instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context context() {
        return instance.getApplicationContext();
    }
}
