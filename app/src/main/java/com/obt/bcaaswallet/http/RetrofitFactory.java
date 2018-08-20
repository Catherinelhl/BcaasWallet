package com.obt.bcaaswallet.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.obt.bcaaswallet.constants.Constants;
import com.obt.bcaaswallet.http.factory.StringConverterFactory;
import com.obt.bcaaswallet.http.interceptor.OkHttpInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/20
 */
public class RetrofitFactory {
//
//    // 添加公共参数拦截器
//    BasicParamsInterceptor basicParamsInterceptor = new BasicParamsInterceptor.Builder()
//            .addHeaderParam("userName", "")//添加公共参数
//            .addHeaderParam("device", "")
//            .build();
//
// builder.addInterceptor(basicParamsInterceptor);

    private static Retrofit stringInstance;
    private static OkHttpClient client;

    public static Retrofit getStringInstance() {
        String BASE_URL = Constants.Domains.TEST_DOMAINANDPORT;

        if (client == null) {
            client = new OkHttpClient.Builder()
                    .connectTimeout(30000, TimeUnit.SECONDS)
                    .readTimeout(30000, TimeUnit.SECONDS)
                    .writeTimeout(30000, TimeUnit.SECONDS)
                    .addInterceptor(new OkHttpInterceptor())
                    .build();
        }

        if (stringInstance == null) {
            stringInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
//                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(new StringConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//Observble
                    .build();
        }
        return stringInstance;
    }

    private static Gson gson = new GsonBuilder().setLenient().create();

}
