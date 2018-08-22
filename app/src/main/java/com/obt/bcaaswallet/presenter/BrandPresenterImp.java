package com.obt.bcaaswallet.presenter;

import com.obt.bcaaswallet.base.BasePresenterImp;
import com.obt.bcaaswallet.database.WalletInfo;
import com.obt.bcaaswallet.ui.contracts.BrandContracts;
import com.obt.bcaaswallet.utils.ListU;
import com.obt.bcaaswallet.utils.StringU;
import com.obt.bcaaswallet.vo.WalletVO;

import java.util.List;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/20
 * <p>
 * 1：查询当前本地数据库，如果没有钱包数据，代表没有可解锁的钱包，提示用户创建钱包/导入钱包
 * 2：如果当前有钱包数据，然后拿到是否有「accessToken」字段，如果没有，那么就进行网络请求，进行「登入」的操作，拿到返回的数据
 * 4：得到钱包登入「accessToken」，存储到当前用户下，然后以此来判断是否需要重新「登入」
 * 5：如果有「accessToken」那么就直接进入首页
 * todo  貌似判断Token是否存在应该在Brand页面去检测
 */
public class BrandPresenterImp extends BasePresenterImp implements BrandContracts.Presenter {

    private BrandContracts.View view;

    public BrandPresenterImp(BrandContracts.View view) {
        super();
        this.view = view;

    }


    @Override
    public void queryWalletInfo() {
        List<WalletInfo> walletInfos = getAllWallets();
        if (ListU.isEmpty(walletInfos)) {
            view.noWalletInfo();
        } else {
            WalletInfo wallet = walletInfos.get(0);//得到当前的钱包
            String walletAddress = wallet.getBitcoinAddressStr();
            String blockService = wallet.getBlockService();
            String accessToken = wallet.getAccessToken();
            if (StringU.isEmpty(accessToken)) {
                //有钱包，但是没有token
                view.noWalletInfo();
            } else {
                WalletVO walletVO = new WalletVO();
                walletVO.setAccessToken(accessToken);
                walletVO.setWalletAddress(walletAddress);
                walletVO.setBlockService(blockService);
                verifyToken(walletVO);
            }

            if (StringU.isEmpty(blockService) || StringU.isEmpty(walletAddress)) {
                //检查到当前数据库没有钱包地址数据，那么需要提示用户先创建或者导入钱包
                view.noWalletInfo();
            } else {

            }

        }

    }

    //得到所有得钱包信息
    private List<WalletInfo> getAllWallets() {
        if (walletInfoDao == null) {
            throw new NullPointerException("walletInfoDao is null");
        }
        return walletInfoDao.queryBuilder().list();
    }

    //验证当前的token是否可用
    private void verifyToken(WalletVO walletVO) {
        // TODO: 2018/8/20 验证token
//        doRequest(Constants.RequestUrl.verify, MessageConstants.REQUEST_MOTHOD_POST, walletVO);
    }


}