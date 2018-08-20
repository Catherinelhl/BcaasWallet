package com.obt.bcaaswallet.presenter;

import com.obt.bcaaswallet.R;
import com.obt.bcaaswallet.base.BasePresenterImp;
import com.obt.bcaaswallet.bean.SettingTypeBean;
import com.obt.bcaaswallet.constants.Constants;
import com.obt.bcaaswallet.ui.contracts.SettingContract;
import com.obt.bcaaswallet.vo.WalletVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/16
 */
public class SettingPresenterImp extends BasePresenterImp implements SettingContract.Presenter {

    private SettingContract.View viewInterface;

    public SettingPresenterImp(SettingContract.View view) {
        this.viewInterface = view;
    }

    @Override
    public List<SettingTypeBean> initSettingTypes() {
        List<SettingTypeBean> settingTypes = new ArrayList<>();
        SettingTypeBean settingTypeBean = new SettingTypeBean(getString(R.string.check_wallet_info), Constants.SettingType.CHECKWALLETINFO);
        SettingTypeBean settingTypeBean2 = new SettingTypeBean(getString(R.string.modify_password), Constants.SettingType.MODIFYPOSSWORD);
        SettingTypeBean settingTypeBean3 = new SettingTypeBean(getString(R.string.modify_authorized_representatives), Constants.SettingType.MODIFYAUTH);
        SettingTypeBean settingTypeBean4 = new SettingTypeBean(getString(R.string.address_manager), Constants.SettingType.ADRESSMANNAGE);
        settingTypes.add(settingTypeBean);
        settingTypes.add(settingTypeBean2);
        settingTypes.add(settingTypeBean3);
        settingTypes.add(settingTypeBean4);
        return settingTypes;

    }

}
