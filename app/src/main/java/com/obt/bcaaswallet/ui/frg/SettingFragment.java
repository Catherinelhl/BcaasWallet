package com.obt.bcaaswallet.ui.frg;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.obt.bcaaswallet.R;
import com.obt.bcaaswallet.adapter.SettingTypesAdapter;
import com.obt.bcaaswallet.base.BaseFragment;
import com.obt.bcaaswallet.base.BcaasApplication;
import com.obt.bcaaswallet.bean.SettingTypeBean;
import com.obt.bcaaswallet.constants.Constants;
import com.obt.bcaaswallet.listener.OnItemSelectListener;
import com.obt.bcaaswallet.presenter.SettingPresenterImp;
import com.obt.bcaaswallet.ui.aty.AddressManagerActivity;
import com.obt.bcaaswallet.ui.aty.CheckWalletInfoActivity;
import com.obt.bcaaswallet.ui.contracts.SettingContract;
import com.obt.bcaaswallet.utils.L;
import com.obt.bcaaswallet.vo.WalletVO;

import java.util.List;

import butterknife.BindView;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/15
 * <p>
 * 设置页面
 */
public class SettingFragment extends BaseFragment implements SettingContract.View {
    @BindView(R.id.rvSetting)
    RecyclerView rvSetting;
    @BindView(R.id.btnLogout)
    Button btnLogout;

    private SettingContract.Presenter presenter;

    private SettingTypesAdapter settingTypesAdapter;


    public static SettingFragment newInstance() {
        SettingFragment settingFragment = new SettingFragment();
        return settingFragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.frg_setting;
    }


    @Override
    public void initViews(View view) {
        presenter = new SettingPresenterImp(this);
        List<SettingTypeBean> settingTypes = presenter.initSettingTypes();//得到设置页面需要显示的所有设置选项
        settingTypesAdapter = new SettingTypesAdapter(context, settingTypes);
        rvSetting.setHasFixedSize(true);
        rvSetting.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        rvSetting.setAdapter(settingTypesAdapter);
    }

    @Override
    public void initListener() {
        settingTypesAdapter.setSettingItemSelectListener(new OnItemSelectListener() {
            @Override
            public <T> void onItemSelect(T type) {
                //TODO  到时候这个类目，可以考虑用一个有TAG值的数据类来保存
                if (type == null) return;
                if (type instanceof SettingTypeBean) {
                    SettingTypeBean settingTypeBean = (SettingTypeBean) type;
                    switch (settingTypeBean.getTag()) {
                        case CHECKWALLETINFO:
                            Gson gson = new Gson();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.KeyMaps.CURRENCY, gson.toJson(getCurrency()));
                            bundle.putString(Constants.KeyMaps.ALLCURRENCY, gson.toJson(getAllTransactionData()));
                            intentToActivity(bundle, CheckWalletInfoActivity.class, false);
                            break;
                        case MODIFYPOSSWORD:
                        case MODIFYAUTH:
                            showToast(settingTypeBean.getType());
                            break;
                        case ADRESSMANNAGE:
                            intentToActivity(null, AddressManagerActivity.class, false);
                            break;
                    }
                }
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L.d("当前账户地址是=》" + BcaasApplication.getWalletAddress());
                presenter.logout(BcaasApplication.getWalletAddress());
            }
        });
    }

    @Override
    public void logoutFailure() {

    }

    @Override
    public void logoutSuccess() {

    }

    @Override
    public void showLoadingDialog(String loading) {

    }

    @Override
    public void hideLoadingDialog() {

    }

}
