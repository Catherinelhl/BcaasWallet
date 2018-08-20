package com.obt.bcaaswallet.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.obt.bcaaswallet.constants.Constants;
import com.obt.bcaaswallet.http.factory.StringConverterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/20
 */
public class RetrofitFactory {
//    // 创建 OKHttpClient
//    OkHttpClient.Builder builder = new OkHttpClient.Builder();
//     builder.connectTimeout(DEFAULT_TIME_OUT,TimeUnit.SECONDS);//连接超时时间
//     builder.writeTimeout(DEFAULT_TIME_OUT,TimeUnit.SECONDS);//写操作 超时时间
//     builder.readTimeout(DEFAULT_TIME_OUT,TimeUnit.SECONDS);//读操作超时时间
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
                    .build();
        }

        if (stringInstance == null) {
            stringInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(new StringConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return stringInstance;
    }

    private static Gson gson = new GsonBuilder().setLenient().create();

}
