package com.obt.bcaaswallet.bean;

import com.obt.bcaaswallet.constants.Constants;

import java.io.Serializable;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/16
 */
public class SettingTypeBean implements Serializable {

    private String type;
    private Constants.SettingType tag;

    public SettingTypeBean(String type, Constants.SettingType tag) {
        super();
        this.tag = tag;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Constants.SettingType getTag() {
        return tag;
    }

    public void setTag(Constants.SettingType tag) {
        this.tag = tag;
    }

}
