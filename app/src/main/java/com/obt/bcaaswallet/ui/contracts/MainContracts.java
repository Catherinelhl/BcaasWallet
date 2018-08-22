package com.obt.bcaaswallet.ui.contracts;

import com.obt.bcaaswallet.base.BaseView;
import com.obt.bcaaswallet.database.WalletInfo;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/21
 */
public interface MainContracts {
    interface View extends BaseView {
        void responseSuccess();

        void resetAuthNodeFailure(String message);//重设AN失败

        void resetAuthNodeSuccess();//重设AN成功

        void noAnClientInfo();
    }

    interface Presenter {

        void getWalletWaitingToReceiveBlock();

        void checkANClientIPInfo(String from);

        void resetAuthNodeInfo(WalletInfo walletInfo);

        void startTCPConnectToGetReceiveBlock();//开始TCP连线，请求未处理的交易
    }
}

