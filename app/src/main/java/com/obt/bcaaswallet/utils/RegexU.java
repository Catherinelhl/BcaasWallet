package com.obt.bcaaswallet.utils;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/20
 */
public class RegexU {

    public static boolean isCharacter(String str) {
        if (str.matches("^[a-zA-Z]*")) {
            return true;
        }
        return false;
    }
}
