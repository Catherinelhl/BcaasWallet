package com.obt.bcaaswallet.base;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import com.obt.bcaaswallet.database.AddressDao;
import com.obt.bcaaswallet.database.DaoSession;
import com.obt.bcaaswallet.database.WalletInfoDao;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/17
 */
public abstract class BasePresenterImp {
    protected Context context;
    protected WalletInfoDao walletInfoDao;//钱包信息数据库
    protected AddressDao addressDao;//地址管理数据库


    public BasePresenterImp() {
        context = BcaasApplication.context();
        initDaoData();

    }

    private void initDaoData() {
        DaoSession session = ((BcaasApplication) context.getApplicationContext()).getDaoSession();
        walletInfoDao = session.getWalletInfoDao();
        addressDao = session.getAddressDao();
    }

    protected String getString(int resId) {
        return context.getString(resId);
    }

    protected Resources getString() {
        return context.getResources();
    }

    protected AssetManager getAssets() {
        return context.getAssets();
    }


}
