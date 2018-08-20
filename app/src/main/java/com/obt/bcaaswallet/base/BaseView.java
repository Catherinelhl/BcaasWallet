package com.obt.bcaaswallet.base;

import com.obt.bcaaswallet.vo.WalletVO;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/17
 */
public interface BaseView {
    void showLoadingDialog(String loading);

    void hideLoadingDialog();

    void requestSuccess(WalletVO walletVO);

    void requestFailure(String message);
}
