package com.obt.bcaaswallet.utils;

import com.obt.bcaaswallet.database.WalletInfo;
import com.obt.bcaaswallet.ecc.Wallet;
import com.obt.bcaaswallet.vo.WalletVO;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/20
 * <p>
 * <p>
 * 钱包信息的相关取得
 */
public class WalletU {

    /* 自动创建钱包信息*/
    public static Wallet getWalletInfo() {
        return getWalletInfo("");
    }

    /*通过WIF格式的私钥来创建钱包*/
    public static Wallet getWalletInfo(String privateKeyWIFStr) {
        if (StringU.isEmpty(privateKeyWIFStr)) {
            return Wallet.createWallet();
        } else {
            return Wallet.createWallet(privateKeyWIFStr);

        }

    }

    //通过默认的方式来获取钱包地址
    public static String getWalletAddress() {
        return getWalletInfo("").getBitcoinAddressStr();
    }

    //通过WIF格式的私钥来获取钱包地址信息
    public static String getWalletAddress(String privateKeyWIFStr) {
        return getWalletInfo(privateKeyWIFStr).getBitcoinAddressStr();
    }

    /*待定：数据类的转换，将数据请求的WalletVo 转换成本地的Info*/
    public static WalletVO infoToVo(WalletInfo walletInfo) {
        WalletVO walletVO = new WalletVO();
        walletVO.setWalletAddress(walletInfo.getBitcoinAddressStr());
        walletVO.setBlockService(walletInfo.getBlockService());
        walletVO.setAccessToken(walletInfo.getAccessToken());
        return walletVO;
    }

    /*待定：数据类的转换，将本地的Info 转换成数据请求的WalletVo*/
    public static WalletInfo voToInfo(WalletVO walletVO) {
        WalletInfo walletInfo = new WalletInfo();
        walletInfo.setBitcoinAddressStr(walletVO.getWalletAddress());
        walletInfo.setBlockService(walletVO.getBlockService());
        walletInfo.setAccessToken(walletVO.getAccessToken());
        return walletInfo;
    }
}
