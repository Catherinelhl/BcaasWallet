package com.obt.bcaaswallet.http;

import com.obt.bcaaswallet.constants.Constants;
import com.obt.bcaaswallet.gson.WalletVoResponseJson;
import com.obt.bcaaswallet.vo.WalletVO;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/20
 * <p>
 * 请求网络的所有接口
 */
public interface HttpApi {

    /*SFN：登入*/
    //    @FormUrlEncoded
    @POST(Constants.RequestUrl.login)
    Call<String> login(@Body RequestBody requestBody);

    /*SFN：登出*/
    @POST(Constants.RequestUrl.logout)
    Call<WalletVoResponseJson> logout(@Body RequestBody requestBody);

    /*SFN：验证当前token是否过期*/
    @POST(Constants.RequestUrl.verify)
    Call<WalletVoResponseJson> verify(@Body RequestBody requestBody);

    /*SFN：當錢包與AuthNode無法通信時調用,取得新的AuthNode IP資訊*/
    @POST(Constants.RequestUrl.resetAuthNodeInfo)
    Call<WalletVoResponseJson> resetAuthNodeInfo(@Body RequestBody requestBody);

}
