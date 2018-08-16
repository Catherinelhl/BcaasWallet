package com.obt.bcaaswallet.presenter;

import com.obt.bcaaswallet.bean.SettingTypeBean;
import com.obt.bcaaswallet.contants.Contants;
import com.obt.bcaaswallet.ui.contracts.SettingContract;

import java.util.ArrayList;
import java.util.List;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/16
 */
public class SettingPresenterImp implements SettingContract.Presenter {

    private SettingContract.View viewInterface;

    public SettingPresenterImp(SettingContract.View view) {
        this.viewInterface = view;
    }

    @Override
    public List<SettingTypeBean> initSettingTypes() {
        List<SettingTypeBean> settingTypes = new ArrayList<>();
        //TODO  应用当前的上下文
        SettingTypeBean settingTypeBean=new SettingTypeBean("查看钱包信息", Contants.SettingType.CHECKWALLETINFO);
        SettingTypeBean settingTypeBean2=new SettingTypeBean("修改密码", Contants.SettingType.MODIFYPOSSWORD);
        SettingTypeBean settingTypeBean3=new SettingTypeBean("修改授权代表", Contants.SettingType.MODIFYAUTH);
        SettingTypeBean settingTypeBean4=new SettingTypeBean("地址管理", Contants.SettingType.ADRESSMANNAGE);
        settingTypes.add(settingTypeBean);
        settingTypes.add(settingTypeBean2);
        settingTypes.add(settingTypeBean3);
        settingTypes.add(settingTypeBean4);
        return settingTypes;

    }
}
