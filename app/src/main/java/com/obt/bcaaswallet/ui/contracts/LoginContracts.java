package com.obt.bcaaswallet.ui.contracts;

import com.obt.bcaaswallet.base.BaseView;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/20
 */
public interface LoginContracts {

    interface View extends BaseView {
        void loginSuccess();

        void loginFailure(String message);
    }

    interface Presenter {
        void login(String blockService,String walletAddress);
    }
}
