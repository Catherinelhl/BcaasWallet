package com.obt.bcaaswallet.base;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.obt.bcaaswallet.utils.OttoU;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/15
 */
public class BcaasApplication extends Application {
    private static BcaasApplication instance;
    protected static int screenWidth;
    protected static int screenHeight;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        getScreenMeasure();
        OttoU.getInstance().register(this);
    }

    /*得到当前屏幕的尺寸*/
    private void getScreenMeasure() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) this.getSystemService(WINDOW_SERVICE);
        assert windowManager != null;
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
    }

    public static Context context() {
        return instance.getApplicationContext();
    }

}
