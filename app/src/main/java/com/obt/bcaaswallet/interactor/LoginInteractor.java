package com.obt.bcaaswallet.interactor;

import com.obt.bcaaswallet.http.HttpApi;
import com.obt.bcaaswallet.http.RetrofitFactory;
import com.obt.bcaaswallet.vo.WalletVO;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;


/**
 * @author catherine.brainwilliam
 * @since 2018/8/20
 * <p>
 * 登录的网络请求
 */
public class LoginInteractor {

    public void login(RequestBody body, Callback<String> callBackListener) {
        HttpApi httpApi = RetrofitFactory.getStringInstance().create(HttpApi.class);
        Call<String> call = httpApi.login(body);
        call.enqueue(callBackListener);
    }

    public void login(String walletInfo, Callback<String> callBackListener) {
       RequestBody body= RequestBody.create(MediaType.parse("application/json"), walletInfo);
        HttpApi httpApi = RetrofitFactory.getStringInstance().create(HttpApi.class);
        Call<String> call = httpApi.login(body);
        call.enqueue(callBackListener);
    }
}
