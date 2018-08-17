package com.obt.greendaogenerate;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyClass {

    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.obt.bcaaswallet.database");

        //創建一個地址管理的數據類，用於新增地址，刪除地址
        Entity entity = schema.addEntity("Address");
        entity.addIdProperty();
        entity.addStringProperty("alias");
        entity.addStringProperty("name");
        entity.addStringProperty("address");
        try {
            new DaoGenerator().generateAll(schema, "app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
