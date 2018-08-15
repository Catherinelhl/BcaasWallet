package com.obt.bcaaswallet.utils;

import android.text.TextUtils;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/15
 */
public class StringU {

    public static boolean isEmpty(String content) {
        return TextUtils.isEmpty(content);
    }

    public static boolean notEmpty(String content) {
        return !isEmpty(content);
    }
}
