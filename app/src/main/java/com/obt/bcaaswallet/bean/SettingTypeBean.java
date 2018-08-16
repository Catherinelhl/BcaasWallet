package com.obt.bcaaswallet.bean;

import com.obt.bcaaswallet.contants.Contants;

import java.io.Serializable;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/16
 */
public class SettingTypeBean implements Serializable {

    private String type;
    private Contants.SettingType tag;

    public SettingTypeBean(String type, Contants.SettingType tag) {
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

    public Contants.SettingType getTag() {
        return tag;
    }

    public void setTag(Contants.SettingType tag) {
        this.tag = tag;
    }

}
