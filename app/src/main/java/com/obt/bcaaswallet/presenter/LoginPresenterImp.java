package com.obt.bcaaswallet.presenter;

import com.google.gson.Gson;
import com.obt.bcaaswallet.R;
import com.obt.bcaaswallet.base.BasePresenterImp;
import com.obt.bcaaswallet.base.BcaasApplication;
import com.obt.bcaaswallet.constants.Constants;
import com.obt.bcaaswallet.database.WalletInfo;
import com.obt.bcaaswallet.encryption.AES;
import com.obt.bcaaswallet.gson.WalletVoResponseJson;
import com.obt.bcaaswallet.interactor.LoginInteractor;
import com.obt.bcaaswallet.ui.contracts.LoginContracts;
import com.obt.bcaaswallet.utils.GsonU;
import com.obt.bcaaswallet.utils.L;
import com.obt.bcaaswallet.utils.ListU;
import com.obt.bcaaswallet.utils.StringU;
import com.obt.bcaaswallet.vo.WalletVO;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
public class LoginPresenterImp extends BasePresenterImp implements LoginContracts.Presenter {

    private LoginContracts.View view;

    public LoginPresenterImp(LoginContracts.View view) {
        super();
        this.view = view;

    }


    @Override
    public void queryWalletInfo(String password) {
        List<WalletInfo> walletInfos = getAllWallets();
        if (ListU.isEmpty(walletInfos)) {
            view.noWalletInfo();
        } else {
            WalletInfo wallet = walletInfos.get(0);//得到当前的钱包
            // TODO: 2018/8/21 当前App可以有多个钱包在线么？还是保持唯一
            for (WalletInfo walletInfo : walletInfos) {
                //todo  如果当前可以多个账户存在，要这样去遍历得到账户？不对，这样输入错误的密码不就不知道了。
                if (StringU.equals(walletInfo.getPassword(), password)) {
                    wallet = walletInfo;
                    L.d("需要登入的钱包是==》" + wallet);

                }
            }
            String walletAddress = wallet.getBitcoinAddressStr();
            String blockService = wallet.getBlockService();
            if (StringU.isEmpty(blockService) || StringU.isEmpty(walletAddress)) {
                //TODO 对当前的参数进行判空「自定义弹框」
                //检查到当前数据库没有钱包地址数据，那么需要提示用户先创建或者导入钱包
                view.loginFailure(context.getString(R.string.localdata_exception));
            } else {
                WalletVO walletVO = new WalletVO();
                walletVO.setBlockService(blockService);
                walletVO.setWalletAddress(walletAddress);
                login(walletVO);
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

    @Override
    public void login(WalletVO walletVO) {
        LoginInteractor loginInteractor = new LoginInteractor();
        final String json = GsonU.encodeToString(walletVO);
        L.line("login===>" + json);
        try {
            String encodeJson = AES.encodeCBC_128(json);
            RequestBody body = GsonU.beanToRequestBody(walletVO);
            loginInteractor.login(encodeJson, new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Gson gson = new Gson();
                    WalletVoResponseJson walletVOResponse = gson.fromJson(response.body(), WalletVoResponseJson.class);
                    L.line(walletVOResponse);
                    parseData(walletVOResponse.getWalletVO());
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    view.loginFailure(t.getMessage());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void parseData(WalletVO walletVO) {
        //得到当前回传的信息，存储当前的accessToken
        if (walletVO == null) {
            throw new NullPointerException(" loginPresenterImp parseData walletVO is null");
        }
        String accessToken = walletVO.getAccessToken();
        Constants.LOGGER_INFO.info(accessToken);
        if (StringU.isEmpty(accessToken)) {
            view.loginFailure(getString(R.string.login_failure));
        } else {
            BcaasApplication.setAccessToken(accessToken);
            BcaasApplication.setWalletAddress(walletVO.getWalletAddress());
            // TODO: 2018/8/20 存储当前的token，具体存储方式待跟进
            updateWalletData(accessToken);
        }
    }

    private void updateWalletData(String accessToken) {
        List<WalletInfo> walletInfos = getAllWallets();
        if (ListU.isEmpty(walletInfos)) {
            view.noWalletInfo();
        } else {
            WalletInfo wallet = walletInfos.get(0);
            wallet.setAccessToken(accessToken);
            walletInfoDao.update(wallet);
            view.loginSuccess();

        }
    }
}
