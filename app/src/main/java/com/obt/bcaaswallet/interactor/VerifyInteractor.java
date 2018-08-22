package com.obt.bcaaswallet.interactor;

import com.obt.bcaaswallet.gson.WalletVoResponseJson;
import com.obt.bcaaswallet.http.HttpApi;
import com.obt.bcaaswallet.http.RetrofitFactory;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/20
 * <p>
 * 验证当前账户
 */
public class VerifyInteractor {

    public void verify(RequestBody body, Callback<WalletVoResponseJson> callBackListener) {
        HttpApi httpApi = RetrofitFactory.getInstance().create(HttpApi.class);
        Call<WalletVoResponseJson> call = httpApi.verify(body);
        call.enqueue(callBackListener);
    }

}
