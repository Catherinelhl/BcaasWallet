package com.obt.bcaaswallet.utils;

import android.util.Log;

/**
 * BcaasWallet
 * <p>
 * com.obt.bcaaswallet.utils
 * <p>
 * created by catherine in 八月/22/2018/上午6:47
 */
public class LL {

    public static String TAG = "LogUtils";
    private static final boolean DEBUG = true;

    private static final int D = 745;
    private static final int E = 421;
    private static final int V = 674;
    private static final String CUT_OFF = "------------------------";
    private static final String CUT_OFF_END = "----------------------" +
            "--------------------------------------------------------";
    private static final String SPACE_9 = "         ";


    public static void d(String tag, String... values) {
        printf(D, tag, values);
    }

    public static void e(String tag, String... values) {
        printf(E, tag, values);
    }

    public static void v(String tag, String... values) {
        printf(V, tag, values);
    }

    private static void printf(int mark, String tag, String... values) {
        if (!DEBUG) {
            return;
        }

        //需要打印的内容
        StringBuffer value = new StringBuffer();
        for (int i = 0; i < values.length; i++) {
            value.append(values[i]);
            if (i == values.length - 1) {
                break;
            }
            value.append(", ");
        }

        // 打印
        switch (mark) {
            case D:

                printfLine(D, tag);
                Log.d(tag, SPACE_9 + value.toString());
                Log.d(tag, CUT_OFF_END);

                break;
            case E:
                printfLine(E, tag);

                Log.e(tag, SPACE_9 + value.toString());
                Log.e(tag, CUT_OFF_END);

                break;
            case V:
                printfLine(V, tag);

                Log.v(tag, SPACE_9 + value.toString());
                Log.v(tag, CUT_OFF_END);

                break;
        }


    }

    private static String getPosition(String tag) {
        StringBuilder sb = new StringBuilder();
        StackTraceElement element = getTargetStack(tag);

        if (null == element) {
            return null;
        }

        //sb.append(".")// 我电脑的AndroidStudio有点问题，必须在这加个点，在logcat中才能定位。Androidstudio升级后，这个问题不存在了。
        sb.append("(")
                .append(element.getFileName())
                .append(":")
                .append(element.getLineNumber())
                .append(")");
        return sb.toString();
    }

    private static void printfLine(int mark, String tag) {
        String startLine = CUT_OFF + getPosition(tag) + CUT_OFF;

        switch (mark) {
            case D:

                Log.d(tag, " ");
                Log.d(tag, startLine);

                break;
            case E:
                Log.e(tag, " ");
                Log.e(tag, startLine);
                break;
            case V:
                Log.v(tag, " ");
                Log.v(tag, startLine);
                break;
        }


    }

    /**
     * 获取最后调用我们log的StackTraceElement
     * @param tag 目标类的SimpleName
     * @return
     */

    private static StackTraceElement getTargetStack(String tag) {

        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {

            if (element.getClassName().contains(tag)) {
                //返回调用位置的 element
                return element;

            }

        }

        return null;
    }

}