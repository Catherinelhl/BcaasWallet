package com.obt.bcaaswallet.contants;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/15
 * <p>
 * 存放当前的key/log信息
 */
public class Contants {

    public static class ValueMaps {
        public static final int brandSleepTime = 2000;//应用启动页睡眠时间
        public static final int sleepTime500 = 500;
        public static final int sleepTime1000 = 1000;
        public static final int sleepTime2000 = 2000;
        public static final int sleepTime3000 = 3000;
        public static final int sleepTime4000 = 4000;
        public static final int sleepTime5000 = 5000;

    }

    public enum SettingType {//定义一下设置的类型
        CHECKWALLETINFO,
        MODIFYPOSSWORD,
        MODIFYAUTH,
        ADRESSMANNAGE
    }

    public static class KeyMaps {
        public static final String CURRENCY="currency";
        public static final String ALLCURRENCY="allCurrency";
    }
}
