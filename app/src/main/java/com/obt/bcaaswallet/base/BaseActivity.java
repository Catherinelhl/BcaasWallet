package com.obt.bcaaswallet.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.obt.bcaaswallet.utils.L;
import com.obt.bcaaswallet.utils.OttoU;
import com.obt.bcaaswallet.vo.WalletVO;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/15
 */
public abstract class BaseActivity extends FragmentActivity implements BaseView {

    private Unbinder unbinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getArgs(getIntent().getExtras());
        setContentView(getContentView());
        unbinder = ButterKnife.bind(this);
        OttoU.getInstance().register(this);
        initViews();
        initListener();
    }

    public abstract int getContentView();

    public abstract void getArgs(Bundle bundle);

    public abstract void initViews();

    public abstract void initListener();

    public void showToast(int res) {
        showToast(String.valueOf(res));
    }

    public void showToast(final String toastInfo) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                L.d(toastInfo);
//                Toast.makeText(BcaasApplication.context(), toastInfo, Toast.LENGTH_SHORT).show();

            }
        });
    }

    /**
     * 从当前页面跳转到另一个页面
     *
     * @param classTo
     */
    public void intentToActivity(Class classTo) {
        intentToActivity(null, classTo);
    }

    /**
     * @param finishFrom 是否关闭上一个activity，默认是不关闭 false
     */
    public void intentToActivity(Class classTo, boolean finishFrom) {
        intentToActivity(null, classTo, finishFrom);
    }

    /**
     * @param bundle 存储当前页面需要传递的数据
     */
    public void intentToActivity(Bundle bundle, Class classTo) {
        intentToActivity(bundle, classTo, false);
    }

    public void intentToActivity(Bundle bundle, Class classTo, Boolean finishFrom) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setClass(this, classTo);
        startActivity(intent);
        if (finishFrom) {
            this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        OttoU.getInstance().unregister(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void showLoadingDialog(String loading) {
        // TODO: 2018/8/17 需要自定义一个加载弹框
    }

    @Override
    public void hideLoadingDialog() {

    }

    @Override
    public void requestSuccess(WalletVO walletVO) {

    }

    @Override
    public void requestFailure(String message) {

    }
}
