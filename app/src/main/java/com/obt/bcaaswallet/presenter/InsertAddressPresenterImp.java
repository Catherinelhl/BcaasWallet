package com.obt.bcaaswallet.presenter;

import com.obt.bcaaswallet.R;
import com.obt.bcaaswallet.base.BasePresenterImp;
import com.obt.bcaaswallet.base.BcaasApplication;
import com.obt.bcaaswallet.database.Address;
import com.obt.bcaaswallet.database.AddressDao;
import com.obt.bcaaswallet.database.DaoSession;
import com.obt.bcaaswallet.ui.contracts.InsertAddressContract;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/17
 */
public class InsertAddressPresenterImp extends BasePresenterImp implements InsertAddressContract.Presenter {

    private InsertAddressContract.View view;

    public InsertAddressPresenterImp(InsertAddressContract.View view) {
        super();
        this.view = view;

    }

    /*將當前新添加的一條數據添加到本地數據庫*/
    @Override
    public void saveData(Address address) {
        view.showLoadingDialog(getString(R.string.loading));
        DaoSession daoSession = ((BcaasApplication) context.getApplicationContext()).getDaoSession();
        AddressDao addressDao = daoSession.getAddressDao();
        addressDao.insert(address);
        view.saveDataSuccess();
        view.hideLoadingDialog();

    }
}
