package com.obt.bcaaswallet.gson;

import com.obt.bcaaswallet.vo.WalletVO;

import java.io.Serializable;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/20
 */
public class WalletVoResponseJson implements Serializable {

    private WalletVO walletVO;
    private Boolean success;
    private int code;
    private String message;

    public WalletVO getWalletVO() {
        return walletVO;
    }

    public void setWalletVO(WalletVO walletVO) {
        this.walletVO = walletVO;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "WalletVoResponseJson{" +
                "walletVO=" + walletVO +
                ", success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
