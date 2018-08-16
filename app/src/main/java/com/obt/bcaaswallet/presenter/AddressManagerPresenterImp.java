package com.obt.bcaaswallet.presenter;

import com.obt.bcaaswallet.bean.AddressBean;
import com.obt.bcaaswallet.ui.contracts.AddressManagerContract;

import java.util.ArrayList;
import java.util.List;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/16
 */
public class AddressManagerPresenterImp implements AddressManagerContract.Presenter {

    private AddressManagerContract.View view;

    public AddressManagerPresenterImp(AddressManagerContract.View view) {
        this.view = view;
    }

    @Override
    public List<AddressBean> initAddressList() {
        List<AddressBean> addressBeans = new ArrayList<>();
        //TODO  应用当前的上下文
        AddressBean addressBean = new AddressBean("Cathy", "1sdjfhsjkflsdk=====");
        AddressBean addressBean2 = new AddressBean("William", "2sdjfhsjkflsdk=====");
        AddressBean addressBean3 = new AddressBean("Del", "3sdjfhsjkflsdk=====");
        AddressBean addressBean4 = new AddressBean("guess", "4sdjfhsjkflsdk=====");
        addressBeans.add(addressBean);
        addressBeans.add(addressBean2);
        addressBeans.add(addressBean3);
        addressBeans.add(addressBean4);
        return addressBeans;
    }
}
