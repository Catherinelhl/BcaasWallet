package com.obt.bcaaswallet.presenter;

import com.obt.bcaaswallet.base.BasePresenterImp;
import com.obt.bcaaswallet.base.BcaasApplication;
import com.obt.bcaaswallet.database.Address;
import com.obt.bcaaswallet.database.AddressDao;
import com.obt.bcaaswallet.ui.contracts.AddressManagerContract;

import java.util.List;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/16
 */
public class AddressManagerPresenterImp extends BasePresenterImp implements AddressManagerContract.Presenter {

    private AddressManagerContract.View view;

    public AddressManagerPresenterImp(AddressManagerContract.View view) {
        super();
        this.view = view;
    }

    @Override
    public void queryAllAddresses() {
        List<Address> addressBeans = addressDao.queryBuilder().list();
        if (addressBeans == null) {
            view.noData();
        } else {
            if (addressBeans.size() == 0) {
                view.noData();
            } else {
                view.getAddresses(addressBeans);

            }
        }
    }

    @Override
    public void deleteSingleAddress(Address addressBean) {
        addressDao.delete(addressBean);
        List<Address> addressBeans = addressDao.queryBuilder().list();
        if (addressBeans == null) {
            view.noData();
        } else {
            if (addressBeans.size() == 0) {
                view.noData();
            } else {
                view.getAddresses(addressBeans);

            }
        }
    }
}
