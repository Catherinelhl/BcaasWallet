package com.obt.bcaaswallet.base;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.obt.bcaaswallet.bean.TransactionsBean;
import com.obt.bcaaswallet.ui.aty.MainActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/15
 */
public abstract class BaseFragment extends Fragment {

    private View rootView;
    protected Context context;
    protected Activity activity;
    private List<String> currency;
    private List<TransactionsBean> allTransactionData;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutRes(), null);
        }
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        activity = getActivity();
        assert activity != null;
        currency = ((MainActivity) activity).getCurrency();
        allTransactionData = ((MainActivity) activity).getAllCurrencyData();
        initViews(view);
        initListener();
    }

    protected List<String> getCurrency() {
        return currency;
    }

    protected List<TransactionsBean> getAllTransactionData() {
        return allTransactionData;
    }

    public abstract int getLayoutRes();//得到当前的layoutRes

    public abstract void initViews(View view);

    public abstract void initListener();


    public void showToast(String info) {
        if (activity == null) return;
        ((BaseActivity) activity).showToast(info);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void intentToActivity(Bundle bundle, Class classTo, Boolean finishFrom) {//跳转到另外一个界面
        if (activity == null) return;
        ((BaseActivity) activity).intentToActivity(bundle, classTo, finishFrom);
    }

}
