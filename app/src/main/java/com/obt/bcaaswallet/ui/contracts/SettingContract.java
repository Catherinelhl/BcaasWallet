package com.obt.bcaaswallet.ui.contracts;

import com.obt.bcaaswallet.base.BaseView;
import com.obt.bcaaswallet.bean.SettingTypeBean;

import java.util.List;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/16
 */
public interface SettingContract {
    interface View  extends BaseView{
        void logoutSuccess();

        void logoutFailure(String message);
    }

    interface Presenter {
        List<SettingTypeBean> initSettingTypes();

        void logout(String WalletAddress);
    }
}
