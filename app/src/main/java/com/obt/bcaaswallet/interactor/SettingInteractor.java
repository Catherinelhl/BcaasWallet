package com.obt.bcaaswallet.interactor;

import com.obt.bcaaswallet.http.HttpApi;
import com.obt.bcaaswallet.http.RetrofitFactory;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/20
 * <p>
 * 设置的网络
 */
public class SettingInteractor {

    public void logout(String walletAddress, Callback<String> callBackListener) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), walletAddress);
        HttpApi httpApi = RetrofitFactory.getStringInstance().create(HttpApi.class);
        Call<String> call = httpApi.login(body);
        call.enqueue(callBackListener);
    }

}
