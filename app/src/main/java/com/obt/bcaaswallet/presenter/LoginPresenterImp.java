package com.obt.bcaaswallet.presenter;

import com.google.gson.Gson;
import com.obt.bcaaswallet.base.BasePresenterImp;
import com.obt.bcaaswallet.bean.LoginBean;
import com.obt.bcaaswallet.constants.Constants;
import com.obt.bcaaswallet.encryption.AES;
import com.obt.bcaaswallet.ui.contracts.LoginContracts;
import com.obt.bcaaswallet.utils.L;
import com.obt.bcaaswallet.vo.WalletVO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/20
 */
public class LoginPresenterImp extends BasePresenterImp implements LoginContracts.Presenter {

    private LoginContracts.View view;

    public LoginPresenterImp(LoginContracts.View view) {
        this.view = view;
    }


    @Override
    public void login(String blockService, String walletAddress) {
        //TODO 对当前的参数进行判空「自定义弹框」
        String VERSION_SERVER_URL = Constants.Domains.TEST_DOMAINANDPORT + Constants.RequestUrl.login;

        HttpURLConnection conn = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        StringBuilder response = null;

        Gson gson = new Gson();
        LoginBean loginBean = new LoginBean(blockService, walletAddress);

        try {
            String json = gson.toJson(loginBean);

            // encryption
            String encodeJson = AES.encodeCBC_128(json);

            URL url = new URL(VERSION_SERVER_URL);
            L.d(url);
            L.d(json);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(30000);
            conn.setConnectTimeout(30000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setFixedLengthStreamingMode(encodeJson.getBytes().length);

            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");

            conn.connect();

            outputStream = new BufferedOutputStream(conn.getOutputStream());
            outputStream.write(encodeJson.getBytes());
            outputStream.flush();

            int HttpResult = conn.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK) {
                // Receive the response from the server
                inputStream = new BufferedInputStream(conn.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                L.d(response);
                WalletVO walletVO = gson.fromJson(response.toString(), WalletVO.class);
                view.loginSuccess();
                parseData(walletVO);
                System.out.println("[Start] --- response --- [Start]");
                System.out.println(response);
                System.out.println("[End] --- response --- [End]");
            }

        } catch (Exception e) {
            e.printStackTrace();
            view.loginFailure(e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (Exception e) {
                view.loginFailure(e.getMessage());
                e.printStackTrace();
            }
        }

    }

    private void parseData(WalletVO walletVO) {
        L.d(walletVO);

    }
}
