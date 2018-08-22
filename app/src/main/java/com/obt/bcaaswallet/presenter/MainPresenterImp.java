package com.obt.bcaaswallet.presenter;

import com.google.gson.Gson;
import com.obt.bcaaswallet.R;
import com.obt.bcaaswallet.base.BasePresenterImp;
import com.obt.bcaaswallet.base.BcaasApplication;
import com.obt.bcaaswallet.constants.Constants;
import com.obt.bcaaswallet.database.ANClientIpInfo;
import com.obt.bcaaswallet.database.WalletInfo;
import com.obt.bcaaswallet.gson.WalletRequestJson;
import com.obt.bcaaswallet.gson.WalletResponseJson;
import com.obt.bcaaswallet.gson.WalletVoRequestJson;
import com.obt.bcaaswallet.gson.WalletVoResponseJson;
import com.obt.bcaaswallet.http.ReceiveThread;
import com.obt.bcaaswallet.interactor.MainInteractor;
import com.obt.bcaaswallet.listener.TCPReceiveBlockListener;
import com.obt.bcaaswallet.ui.contracts.MainContracts;
import com.obt.bcaaswallet.utils.GsonU;
import com.obt.bcaaswallet.utils.L;
import com.obt.bcaaswallet.utils.ListU;
import com.obt.bcaaswallet.utils.StringU;
import com.obt.bcaaswallet.utils.WalletU;
import com.obt.bcaaswallet.vo.ClientIpInfoVO;
import com.obt.bcaaswallet.vo.WalletVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/21
 * <p>
 * 后台需要和服务器建立长连接，进行R区块的请求
 */
public class MainPresenterImp extends BasePresenterImp implements MainContracts.Presenter {
    private MainContracts.View view;
    private MainInteractor mainInteractor;

    public MainPresenterImp(MainContracts.View view) {
        super();
        this.view = view;
        mainInteractor = new MainInteractor();
    }

    @Override
    public void getWalletWaitingToReceiveBlock() {
        WalletRequestJson walletRequestJson = new WalletRequestJson();
        final WalletInfo walletInfo = getWalletInfo();
        if (walletInfo == null) {
            view.failure(context.getString(R.string.walletdata_failure));
            return;
        }
        walletRequestJson.setWalletAddress(walletInfo.getBitcoinAddressStr());
        walletRequestJson.setBlockService(walletInfo.getBlockService());
        walletRequestJson.setAccessToken(walletInfo.getAccessToken());
        mainInteractor.getWalletWaitingToReceiveBlock(GsonU.beanToRequestBody(walletRequestJson),
                new Callback<WalletResponseJson>() {
                    @Override
                    public void onResponse(Call<WalletResponseJson> call, Response<WalletResponseJson> response) {
                        L.d("getWalletWaitingToReceiveBlock==>onResponse" + response.body());
                        WalletResponseJson walletResponseJson = response.body();
                        if (walletResponseJson.isSuccess()) {
                            view.responseSuccess();
                        } else {
                            view.failure(walletResponseJson.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<WalletResponseJson> call, Throwable t) {
                        L.d("getWalletWaitingToReceiveBlock==>onFailure" + t.getMessage());
                        // TODO: 2018/8/21 如果当前AN的接口请求不通过的时候，应该重新去SFN拉取新AN的数据
                        view.failure(t.getMessage());
                        resetAuthNodeInfo(walletInfo);

                    }
                });
    }

    @Override
    public void resetAuthNodeInfo(WalletInfo walletInfo) {
        WalletVO walletVO = WalletU.infoToVo(walletInfo);
        WalletVoRequestJson walletVoRequestJson = new WalletVoRequestJson(walletVO);
        L.d("resetAuthNodeInfo", walletVoRequestJson);
        mainInteractor.resetAuthNode(GsonU.beanToRequestBody(walletVoRequestJson), new Callback<WalletVoResponseJson>() {
            @Override
            public void onResponse(Call<WalletVoResponseJson> call, Response<WalletVoResponseJson> response) {
                L.d("resetAuthNodeInfo", response.body());
                WalletVoResponseJson walletVoResponseJson = response.body();
                if (walletVoResponseJson.getSuccess()) {
                    view.resetAuthNodeSuccess();
                    getANAddress(walletVoResponseJson.getWalletVO());
                }
            }

            @Override
            public void onFailure(Call<WalletVoResponseJson> call, Throwable t) {
                view.resetAuthNodeFailure(t.getMessage());
            }
        });
    }

    private void getANAddress(WalletVO walletVO) {
        if (walletVO == null) {
            view.failure(context.getString(R.string.null_wallet));
            return;
        }
        ClientIpInfoVO clientIpInfoVO = walletVO.getClientIpInfoVO();
        if (clientIpInfoVO == null) {
            view.failure(context.getString(R.string.null_wallet));
            return;
        }
        L.d("getANAddress", clientIpInfoVO);
        //1:遍历得到数据库ANClientIpInfo里面的数据
        //2：根据钱包地址得到与之匹配的AN ip信息
        //3：组装Ip+port，以备An访问
        //4：重新登入以及reset之后需要重新存储
        BcaasApplication.setClientIpInfoVO(clientIpInfoVO);
        // TODO: 2018/8/21 是否自己的实体类可以替代数据库的实体类 ？
        // TODO: 2018/8/21 暂时先存储需要的两个参数，到时候需要再添加
        ANClientIpInfo anClientIpInfo = new ANClientIpInfo();
        anClientIpInfo.setInternalIp(clientIpInfoVO.getInternalIp());
        anClientIpInfo.setExternalIp(clientIpInfoVO.getExternalIp());
        anClientIpInfo.setExternalPort(clientIpInfoVO.getExternalPort());
        anClientIpInfo.setRpcPort(clientIpInfoVO.getRpcPort());
        anClientIpInfo.setInternalPort(clientIpInfoVO.getInternalPort());
        clientIpInfoDao.insert(anClientIpInfo);
    }

    @Override
    public void checkANClientIPInfo(String from) {
        //根据当前的进入方式去检查此钱包的AN访问地址
        if (StringU.isEmpty(from)) {
            return;
        }
        if (StringU.equals(from, Constants.ValueMaps.FROM_BRAND)) {
            //如果当前用户是直接进入的，那么需要从数据库里面拿到之前存储的AN请求IP
            List<ANClientIpInfo> clientIpInfos = clientIpInfoDao.queryBuilder().list();
            if (ListU.isEmpty(clientIpInfos)) {
                //没有数据，需要重新reset
                view.noAnClientInfo();
                resetAuthNodeInfo(getWalletInfo());
                return;
            } else {
                ClientIpInfoVO clientIpInfoVO = new ClientIpInfoVO();
                for (ANClientIpInfo anClientIpInfo : clientIpInfos) {
                    L.d(anClientIpInfo);
                }
                ANClientIpInfo anClientIpInfo = clientIpInfos.get(0);
                clientIpInfoVO.setInternalIp(anClientIpInfo.getInternalIp());
                anClientIpInfo.setExternalIp(clientIpInfoVO.getExternalIp());
                anClientIpInfo.setExternalPort(clientIpInfoVO.getExternalPort());
                clientIpInfoVO.setRpcPort(anClientIpInfo.getRpcPort());
                anClientIpInfo.setInternalPort(clientIpInfoVO.getInternalPort());
                BcaasApplication.setClientIpInfoVO(clientIpInfoVO);

            }
        }

    }

    /*开启连线
     * 1：通过TCP传给服务器的数据不需要加密
     * 2:开始socket连线之后，然后Http请求该接口，通知服务器可以下发数据了。
     * */
    @Override
    public void startTCPConnectToGetReceiveBlock() {
        WalletInfo walletInfo = getWalletInfo();
        if (walletInfo == null) {
            return;
        }
        WalletRequestJson walletRequestJson = new WalletRequestJson(walletInfo.getAccessToken(),
                walletInfo.getBlockService(), walletInfo.getBitcoinAddressStr());
        String json = GsonU.encodeToString(walletRequestJson);
        String ip = BcaasApplication.getInternalIp();
        int port = BcaasApplication.getInternalPort();
        ReceiveThread sendActionThread = new ReceiveThread(ip, port, json + "\n", tcpReceiveBlockListener);
        sendActionThread.start();

    }

    //监听Tcp数据返回
    TCPReceiveBlockListener tcpReceiveBlockListener = new TCPReceiveBlockListener() {
        @Override
        public void httpToRequestReceiverBlock() {
            getWalletWaitingToReceiveBlock();
        }

        @Override
        public void receiveBlockData(String data) {
            L.d("tcpReceiveBlockListener", data);
            Gson gson = new Gson();
            if (StringU.notEmpty(data)) {
                WalletResponseJson walletResponseJson = gson.fromJson(data, WalletResponseJson.class);
                L.d(walletResponseJson);
            }

        }

        @Override
        public void tcpConnectFailure(String message) {
            // TODO: 2018/8/21 TCP 连接异常，发起重新连接？
        }
    };
}
