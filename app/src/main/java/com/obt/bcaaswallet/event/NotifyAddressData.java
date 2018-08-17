package com.obt.bcaaswallet.event;

import java.io.Serializable;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/17
 */
public class NotifyAddressData implements Serializable{
    private boolean isNotify;
    public NotifyAddressData(boolean isNotify){
        this.isNotify=isNotify;
    }

    public boolean isNotify() {
        return isNotify;
    }
}
