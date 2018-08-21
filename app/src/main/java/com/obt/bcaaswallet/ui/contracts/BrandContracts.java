package com.obt.bcaaswallet.ui.contracts;


import com.obt.bcaaswallet.base.BaseView;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/20
 */
public interface BrandContracts {

    interface View extends BaseView{
        void noWalletInfo();
        void online();
        void offline();
    }

    interface Presenter {
        void queryWalletInfo();
    }
}
