// +----------------------------------------------------------------------
// | Project:   Commons  
// +----------------------------------------------------------------------
// | CreateTime: 15/11/3  下午9:26
// +----------------------------------------------------------------------
// | Author:     xab(xab@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.obt.bcaaswallet.ui;


import android.util.Log;

import com.obt.bcaaswallet.BuildConfig;


/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class L {

    public static L getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final L INSTANCE = new L();
    }

    public static void line() {
        if(!BuildConfig.API_PRINT)return;
        d("/-----------------------------/");
    }

    public static void line(Object str) {
        if(!BuildConfig.API_PRINT)return;
        d("/-----------------------------/" + str);
    }

    public static void el() {
        if(!BuildConfig.API_PRINT)return;
        d("/-----------------------------/");
    }

    public static void el(Object str) {
        if(!BuildConfig.API_PRINT)return;
        d("/-----------------------------/" + str);
    }

    public static void i() {
        if(!BuildConfig.API_PRINT)return;
        Log.i(CommConfig.Tag, getInstance().createLog(""));
        checkPrint();
    }

    public static void i(Object o) {
        if(!BuildConfig.API_PRINT)return;
        if (o == null) {
            i();
        } else {
            i(CommConfig.Tag, String.valueOf(o));
        }
        checkPrint();
    }

    public static void i(String tag, Object o) {
        if(!BuildConfig.API_PRINT)return;
        if (o == null) {
            Log.i(tag, getInstance().createLog(""));
        } else {
            Log.i(tag, getInstance().createLog(String.valueOf(o)));
        }
        checkPrint();
    }

    public static void d() {
        if(!BuildConfig.API_PRINT)return;
        Log.d(CommConfig.Tag, "");
        checkPrint();
    }

    public static void d(Object o) {
        if(!BuildConfig.API_PRINT)return;
        if (o == null) {
            d();
        } else {
            d(CommConfig.Tag, String.valueOf(o));
        }
        checkPrint();
    }

    public static void d(String tag, Object o) {
        if(!BuildConfig.API_PRINT)return;
        if (o == null) {
            Log.d(tag, getInstance().createLog(""));
        } else {
            Log.d(tag, getInstance().createLog(String.valueOf(o)));
        }
        checkPrint();
    }

    public static void e() {
        if(!BuildConfig.API_PRINT)return;
        Log.e(CommConfig.Tag, "");
        checkPrint();
    }

    public static void e(Object o) {
        if(!BuildConfig.API_PRINT)return;
        if (o == null) {
            e();
        } else {
            d(CommConfig.Tag, String.valueOf(o));
        }
    }

    public static void e(String tag, Object o) {
        if(!BuildConfig.API_PRINT)return;
        if (o == null) {
            Log.e(tag, getInstance().createLog(""));
        } else {
            Log.e(tag, getInstance().createLog(String.valueOf(o)));
        }
        checkPrint();
    }

    protected String getMethodNames(StackTraceElement[] sElements) {
        String className = "";
        int index = 3;
        try {
            className = getName(sElements[index++]);
            if (StringUtils.startsWithAny(className
                    , "com.boxfish.stu.utils.tools.L."
                    , "cn.xabad.commons.tools.CommLog.")) {
                className = getName(sElements[index++]);
            }
            if (StringUtils.startsWithAny(className
                    , "com.boxfish.stu.utils.tools.L."
                    , "cn.xabad.commons.tools.CommLog.")) {
                className = getName(sElements[index++]);
                if (StringUtils.startsWithAny(className
                        , "com.boxfish.stu.utils.tools.L."
                        , "cn.xabad.commons.tools.CommLog.")) {
                    className = getName(sElements[index++]);
                    if (StringUtils.startsWithAny(className
                            , "com.boxfish.stu.utils.tools.L."
                            , "cn.xabad.commons.tools.CommLog.")) {
                        className = getName(sElements[index++]);
                    }
                }
            }
        } catch (Exception e) {
        }
        return className;
    }

    private String getName(StackTraceElement element) {
//        String className = element.getFileName();
//        String methodName = element.getMethodName();
//        int lineNumber = element.getLineNumber();
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(methodName).append("(").append(className).append(":").append(lineNumber).append(")#");
        return element.toString();
    }
    private static void checkPrint(){

    }
    private String createLog(String log) {
        if (BuildConfig.API_PRINT) {
            return "[ " + getMethodNames(Thread.currentThread().getStackTrace()) + " ] " + log;
        } else {
            return log;
        }
    }
}
