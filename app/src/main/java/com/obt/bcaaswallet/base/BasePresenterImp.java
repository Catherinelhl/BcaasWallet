package com.obt.bcaaswallet.base;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/17
 */
public abstract class BasePresenterImp {
    protected Context context;

    public BasePresenterImp() {
        context = BcaasApplication.context();
    }

    protected String getString(int resId) {
        return context.getString(resId);
    }

    protected Resources getString() {
        return context.getResources();
    }

    protected AssetManager getAssets() {
        return context.getAssets();
    }

}
