package com.obt.bcaaswallet.http;

import com.obt.bcaaswallet.constants.Constants;
import com.obt.bcaaswallet.vo.WalletVO;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/20
 * <p>
 * 请求网络的所有接口
 */
public interface HttpApi {

    @FormUrlEncoded
    @POST(Constants.RequestUrl.login)
    Call<WalletVO> login(@Field("start") String loginInfo);

}
