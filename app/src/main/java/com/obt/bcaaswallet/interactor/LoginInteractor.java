package com.obt.bcaaswallet.interactor;

import com.obt.bcaaswallet.http.HttpApi;
import com.obt.bcaaswallet.http.RetrofitFactory;
import com.obt.bcaaswallet.vo.WalletVO;

import retrofit2.Call;
import retrofit2.Callback;


/**
 * @author catherine.brainwilliam
 * @since 2018/8/20
 * <p>
 * 登录的网络请求
 */
public class LoginInteractor {

    public void login(String walletInfo, Callback<WalletVO> callBackListener) {
        HttpApi httpApi = RetrofitFactory.getStringInstance().create(HttpApi.class);
        Call<WalletVO> call = httpApi.login(walletInfo);
        call.enqueue(callBackListener);
    }
}
