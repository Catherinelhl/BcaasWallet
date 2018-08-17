package com.obt.bcaaswallet.base;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.obt.bcaaswallet.database.DaoMaster;
import com.obt.bcaaswallet.database.DaoSession;
import com.obt.bcaaswallet.utils.OttoU;

import org.greenrobot.greendao.database.Database;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/15
 */
public class BcaasApplication extends Application {
    private static BcaasApplication instance;
    protected static int screenWidth;
    protected static int screenHeight;

    //数据库
    /* A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher. */
    public static final boolean ENCRYPTED = false;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        getScreenMeasure();
        OttoU.getInstance().register(this);
        initDB();

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

    /*初始化数据库*/
    private void initDB() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "notes-db-encrypted" : "notes-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
