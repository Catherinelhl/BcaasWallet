package com.obt.bcaaswallet.ui.aty;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.obt.bcaaswallet.R;
import com.obt.bcaaswallet.adapter.AddressManagerAdapter;
import com.obt.bcaaswallet.base.BaseActivity;
import com.obt.bcaaswallet.database.Address;
import com.obt.bcaaswallet.event.NotifyAddressData;
import com.obt.bcaaswallet.listener.OnItemSelectListener;
import com.obt.bcaaswallet.presenter.AddressManagerPresenterImp;
import com.obt.bcaaswallet.ui.contracts.AddressManagerContract;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/16
 * <p>
 * 地址管理
 */
public class AddressManagerActivity extends BaseActivity implements AddressManagerContract.View {
    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ibRight)
    ImageButton ibRight;
    @BindView(R.id.rvSetting)
    RecyclerView rvSetting;

    private AddressManagerAdapter addressManagerAdapter;
    private AddressManagerContract.Presenter presenter;
    private List<Address> addressBeans;

    @Override
    public int getContentView() {
        return R.layout.aty_address_manager;
    }

    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    public void initViews() {
        addressBeans = new ArrayList<>();
        presenter = new AddressManagerPresenterImp(this);
        ibBack.setVisibility(View.VISIBLE);
        ibRight.setVisibility(View.VISIBLE);
        tvTitle.setText(R.string.address_manager);
        initAdapter();
        presenter.queryAllAddresses();
    }

    private void initAdapter() {
        addressManagerAdapter = new AddressManagerAdapter(this);
        rvSetting.setHasFixedSize(true);
        rvSetting.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvSetting.setAdapter(addressManagerAdapter);
    }

    @Override
    public void initListener() {
        addressManagerAdapter.setItemSelectListener(new OnItemSelectListener() {
            @Override
            public <T> void onItemSelect(T type) {
                if (type == null) return;
                if (type instanceof Address) {
                    Address addressBean = (Address) type;
                    presenter.deleteSingleAddress(addressBean);
                    //TODO 删除地址需要再次弹框进行确认
                    //响应删除事件
                    if (addressBeans != null) {
                        addressBeans.remove(addressBean);
                        addressManagerAdapter.notifyDataSetChanged();
                    }
                }

            }
        });
        ibRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToActivity(InsertAddressActivity.class);
            }
        });
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void getAddresses(List<Address> addresses) {
        addressBeans = addresses;
        if (addressManagerAdapter != null) {
            addressManagerAdapter.addList(addressBeans);
        }
    }

    @Override
    public void noData() {
    }

    @Subscribe
    public void notifyAddressData(NotifyAddressData notifyAddressData) {
        boolean isNotify = notifyAddressData.isNotify();
        if (isNotify) {
            presenter.queryAllAddresses();
        }

    }
}
