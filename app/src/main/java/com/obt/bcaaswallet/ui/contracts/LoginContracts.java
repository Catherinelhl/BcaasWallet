package com.obt.bcaaswallet.ui.contracts;

import com.obt.bcaaswallet.base.BaseView;
import com.obt.bcaaswallet.vo.WalletVO;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/20
 */
public interface LoginContracts {

    interface View extends BaseView {
        void noWalletInfo();
        void loginFailure(String message);//登录失败

        void loginSuccess();
    }

    interface Presenter {
        void queryWalletInfo(String password);
        void login(WalletVO walletVO);
    }
}
