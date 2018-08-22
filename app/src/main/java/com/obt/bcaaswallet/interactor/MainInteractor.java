package com.obt.bcaaswallet.interactor;

import com.obt.bcaaswallet.base.BcaasApplication;
import com.obt.bcaaswallet.gson.WalletResponseJson;
import com.obt.bcaaswallet.gson.WalletVoResponseJson;
import com.obt.bcaaswallet.http.HttpApi;
import com.obt.bcaaswallet.http.RetrofitFactory;
import com.obt.bcaaswallet.vo.ClientIpInfoVO;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/20
 * <p>
 * An相关
 * <p>
 * 重设An地址
 */
public class MainInteractor {

    //获取钱包余额以及R区块，长连接
    public void getWalletWaitingToReceiveBlock(RequestBody body, Callback<WalletResponseJson> callBackListener) {
        HttpApi httpApi = RetrofitFactory.getAnInstance().create(HttpApi.class);
        Call<WalletResponseJson> call = httpApi.getWalletWaitingToReceiveBlock(body);
        call.enqueue(callBackListener);
    }

    //重新拿去AN的信息
    public void resetAuthNode(RequestBody body, Callback<WalletVoResponseJson> callBackListener) {
        HttpApi httpApi = RetrofitFactory.getInstance().create(HttpApi.class);
        Call<WalletVoResponseJson> call = httpApi.resetAuthNodeInfo(body);
        call.enqueue(callBackListener);
    }

}
