package com.obt.bcaaswallet.ui.contracts;

import com.obt.bcaaswallet.bean.AddressBean;
import com.obt.bcaaswallet.bean.SettingTypeBean;

import java.util.List;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/16
 *
 */
public interface AddressManagerContract {
    interface View{
    }
    interface Presenter{
        List<AddressBean> initAddressList();
    }
}
