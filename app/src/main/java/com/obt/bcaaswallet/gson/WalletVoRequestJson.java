package com.obt.bcaaswallet.gson;

import com.obt.bcaaswallet.vo.WalletVO;

import java.io.Serializable;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/20
 * <p>
 * 请求SFN 请求数据格式
 */
public class WalletVoRequestJson implements Serializable {

    private WalletVO walletVO;
    public WalletVoRequestJson(WalletVO walletVO){
        this.walletVO=walletVO;
    }

    public WalletVO getWalletVO() {
        return walletVO;
    }

    public void setWalletVO(WalletVO walletVO) {
        this.walletVO = walletVO;
    }

    @Override
    public String toString() {
        return "WalletVoRequestJson{" +
                "walletVO=" + walletVO +
                '}';
    }
}
