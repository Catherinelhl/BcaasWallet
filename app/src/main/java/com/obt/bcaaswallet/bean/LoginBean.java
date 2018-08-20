package com.obt.bcaaswallet.bean;

import java.io.Serializable;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/20
 * <p>
 * 登入的数据
 */
public class LoginBean implements Serializable {

    //        {
//            "blockService": String 區塊服務名稱,
//            "walletAddress": String 錢包地址
//        }

    private String blockService;
    private String walletAddress;

    public LoginBean(String blockService, String walletAddress) {
        super();
        this.blockService = blockService;
        this.walletAddress = walletAddress;
    }

    public String getBlockService() {
        return blockService;
    }

    public void setBlockService(String blockService) {
        this.blockService = blockService;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "blockService='" + blockService + '\'' +
                ", walletAddress='" + walletAddress + '\'' +
                '}';
    }
}
