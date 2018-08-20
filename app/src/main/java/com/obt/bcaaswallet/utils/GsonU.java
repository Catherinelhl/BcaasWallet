package com.obt.bcaaswallet.utils;


import com.google.gson.Gson;
import com.obt.bcaaswallet.http.ParameterizedTypeImpl;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import retrofit2.adapter.rxjava.Result;


/**
 * @author catherine.brainwilliam
 * @since 2018/8/16
 */
public class GsonU {
    //解析数据是object的情况
    public static <T> Result<T> fromJsonObject(String response, Class<T> clazz) {
        Gson gson = new Gson();
        Type type = new ParameterizedTypeImpl(Result.class, new Class[]{clazz});
        return gson.fromJson(response, type);
    }

    public static <T> Result<T> fromJsonObject(Reader reader, Class<T> clazz) {
        Gson gson = new Gson();
        Type type = new ParameterizedTypeImpl(Result.class, new Class[]{clazz});
        return gson.fromJson(reader, type);
    }

    //解析数据是数组的情况
    public static <T> Result<List<T>> fromJsonArray(Reader reader, Class<T> clazz) {
        Gson gson = new Gson();
        // 生成List<T> 中的 List<T>
        Type listType = new ParameterizedTypeImpl(List.class, new Class[]{clazz});
        // 根据List<T>生成完整的Result<List<T>>
        Type type = new ParameterizedTypeImpl(Result.class, new Type[]{listType});
        return gson.fromJson(reader, type);
    }


    /*将对象转换为String*/
    public static <T> String encodeToString(T bean) {
        if (bean == null) return null;
        Gson gson = new Gson();
        return gson.toJson(bean);
    }
}
