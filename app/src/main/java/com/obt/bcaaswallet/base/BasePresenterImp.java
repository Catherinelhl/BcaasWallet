package com.obt.bcaaswallet.base;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import com.obt.bcaaswallet.database.ANClientIpInfoDao;
import com.obt.bcaaswallet.database.AddressDao;
import com.obt.bcaaswallet.database.DaoSession;
import com.obt.bcaaswallet.database.WalletInfo;
import com.obt.bcaaswallet.database.WalletInfoDao;
import com.obt.bcaaswallet.vo.WalletVO;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/17
 */
public abstract class BasePresenterImp {
    protected Context context;
    protected WalletInfoDao walletInfoDao;//钱包信息数据库
    protected AddressDao addressDao;//地址管理数据库
    protected ANClientIpInfoDao clientIpInfoDao;//当前需要请求的AN的地址

    public BasePresenterImp() {
        context = BcaasApplication.context();
        initDaoData();

    }

    private void initDaoData() {
        DaoSession session = ((BcaasApplication) context.getApplicationContext()).getDaoSession();
        walletInfoDao = session.getWalletInfoDao();
        addressDao = session.getAddressDao();
        clientIpInfoDao=session.getANClientIpInfoDao();
    }

    protected WalletInfo getWalletInfo() {
        return BcaasApplication.getWalletInfo();
    }

    /*存储当前在线钱包信息的转换口*/
    protected void saveWalletInfo(WalletVO walletVO) {
        if (walletVO == null) {
            return;
        }
        WalletInfo walletInfo = BcaasApplication.getWalletInfo();
        walletInfo.setAccessToken(walletVO.getAccessToken());
        walletInfo.setBlockService(walletVO.getBlockService());
        walletInfo.setBitcoinAddressStr(walletVO.getWalletAddress());
        BcaasApplication.setWalletInfo(walletInfo);
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
