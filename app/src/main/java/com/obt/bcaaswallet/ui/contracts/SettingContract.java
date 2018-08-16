package com.obt.bcaaswallet.ui.contracts;

import com.obt.bcaaswallet.bean.SettingTypeBean;

import java.util.List;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/16
 *
 */
public interface SettingContract {
    interface View{
    }
    interface Presenter{
        List<SettingTypeBean> initSettingTypes();
    }
}
