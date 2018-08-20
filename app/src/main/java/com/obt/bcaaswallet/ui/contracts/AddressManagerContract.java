package com.obt.bcaaswallet.ui.contracts;

import com.obt.bcaaswallet.database.Address;

import java.util.List;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/16
 *
 */
public interface AddressManagerContract {
    interface View{
        void getAddresses(List<Address> addresses);
        void noData();
    }
    interface Presenter{
        void queryAllAddresses();
        void deleteSingleAddress(Address addressBean);
    }
}
