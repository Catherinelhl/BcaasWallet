package com.obt.bcaaswallet.utils;


import com.google.gson.Gson;


/**
 * @author catherine.brainwilliam
 * @since 2018/8/16
 */
public class GsonU {

//    public <T> T decodeFromString(String json, Class<T> type) {
//
//        Gson gson = new Gson();
//        baseInfo = gson.fromJson(json,type);
//        return baseInfo;
//    }

    /*将对象转换为String*/
    public <T> String encodeToString(T bean) {
        if (bean == null) return null;
        Gson gson = new Gson();
        return gson.toJson(bean);
    }
}
