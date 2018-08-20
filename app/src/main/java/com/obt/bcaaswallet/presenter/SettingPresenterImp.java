package com.obt.bcaaswallet.presenter;

import com.obt.bcaaswallet.R;
import com.obt.bcaaswallet.base.BasePresenterImp;
import com.obt.bcaaswallet.bean.SettingTypeBean;
import com.obt.bcaaswallet.constants.Constants;
import com.obt.bcaaswallet.gson.WalletRequestJson;
import com.obt.bcaaswallet.interactor.SettingInteractor;
import com.obt.bcaaswallet.ui.contracts.SettingContract;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/16
 */
public class SettingPresenterImp extends BasePresenterImp implements SettingContract.Presenter {

    private SettingContract.View viewInterface;

    public SettingPresenterImp(SettingContract.View view) {
        super();
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

    @Override
    public void logout(String walletAddress) {
        WalletRequestJson walletRequestJson = new WalletRequestJson();
        walletRequestJson.setWalletAddress(walletAddress);
        SettingInteractor settingInteractor = new SettingInteractor();
        settingInteractor.logout(walletAddress, new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        // TODO: 2018/8/20 登出当前账户
//        doRequest(Constants.RequestUrl.logout, MessageConstants.REQUEST_MOTHOD_POST, walletRequestJson);
    }

}
