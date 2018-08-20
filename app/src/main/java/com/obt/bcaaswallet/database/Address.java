package com.obt.bcaaswallet.database;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "ADDRESS".
 */
@Entity
public class Address {

    @Id
    private Long id;
    private String alias;
    private String name;
    private String address;

    @Generated(hash = 388317431)
    public Address() {
    }

    public Address(Long id) {
        this.id = id;
    }

    @Generated(hash = 378048691)
    public Address(Long id, String alias, String name, String address) {
        this.id = id;
        this.alias = alias;
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
