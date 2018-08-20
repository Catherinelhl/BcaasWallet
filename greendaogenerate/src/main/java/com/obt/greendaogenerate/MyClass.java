package com.obt.greendaogenerate;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyClass {

    public static void main(String[] args) {
               Schema schema = new Schema(1, "com.obt.bcaaswallet.database");
//
////        //創建一個地址管理的數據類，用於新增地址，刪除地址
////        Entity entity = schema.addEntity("Address");
////        entity.addIdProperty();
////        entity.addStringProperty("alias");
////        entity.addStringProperty("name");
////        entity.addStringProperty("address");

        //创建一个钱包类，用于存储当前钱包的私钥，公钥，钱包地址等信息
        Entity walletEntity = schema.addEntity("WalletInfo");
        walletEntity.addIdProperty();
        walletEntity.addStringProperty("bitcoinPublicKeyStr");
        walletEntity.addStringProperty("bitcoinPrivateKeyWIFStr");
        walletEntity.addStringProperty("bitcoinAddressStr");
        walletEntity.addStringProperty("blockService");//区块服务名称
        walletEntity.addStringProperty("accessToken");//当前账户是否需要重新登入的凭证
        walletEntity.addStringProperty("password");//当前账户是否需要重新登入的凭证


        try {
            new DaoGenerator().generateAll(schema, "app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
