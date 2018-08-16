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
import com.obt.bcaaswallet.bean.AddressBean;
import com.obt.bcaaswallet.listener.OnItemSelectListener;
import com.obt.bcaaswallet.presenter.AddressManagerPresenterImp;
import com.obt.bcaaswallet.ui.contracts.AddressManagerContract;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/16
 * <p>
 * 地址管理
 */
public class AddressManagerActivity extends BaseActivity implements AddressManagerContract.View {
    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ibRight)
    ImageButton ibRight;
    @BindView(R.id.rvSetting)
    RecyclerView rvSetting;

    private AddressManagerAdapter addressManagerAdapter;
    private AddressManagerContract.Presenter presenter;
    List<AddressBean> addressBeans;

    @Override
    public int getContentView() {
        return R.layout.aty_address_manager;
    }

    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    public void initViews() {
        presenter = new AddressManagerPresenterImp(this);
        ibBack.setVisibility(View.VISIBLE);
        ibRight.setVisibility(View.VISIBLE);
        tvTitle.setText(R.string.address_mamager);
        initAdapter();
    }

    private void initAdapter() {
        addressBeans = presenter.initAddressList();
        addressManagerAdapter = new AddressManagerAdapter(this, addressBeans);
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
                if (type instanceof AddressBean) {
                    AddressBean addressBean = (AddressBean) type;
                    int position = addressBean.getPostion();
                    //TODO 删除地址需要再次弹框进行确认
                    //响应删除事件
                    if (addressBeans != null) {
                        if (position < addressBeans.size()) {
                            addressBeans.remove(addressBean);
                            addressManagerAdapter.notifyDataSetChanged();
                        }
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
}
