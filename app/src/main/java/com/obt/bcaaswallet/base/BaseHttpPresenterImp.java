package com.obt.bcaaswallet.base;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import com.google.gson.Gson;
import com.obt.bcaaswallet.encryption.AES;
import com.obt.bcaaswallet.gson.WalletRequestJson;
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
 * @since 2018/8/17
 */
public abstract class BaseHttpPresenterImp extends BasePresenterImp {
    protected Context context;
    private BaseView view;

    public BaseHttpPresenterImp(BaseView view) {
        super();
        this.view = view;
    }


    protected void doRequest(String requestUrl, String requestMethod, WalletRequestJson walletRequestJson) {
        HttpURLConnection conn = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        StringBuilder response = null;

        Gson gson = new Gson();
        if (walletRequestJson == null) return;

        try {
            String json = gson.toJson(walletRequestJson);

            // encryption
            String encodeJson = AES.encodeCBC_128(json);

            URL url = new URL(requestUrl);
            L.d(url);
            L.d(json);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(30000);
            conn.setConnectTimeout(30000);
            conn.setRequestMethod(requestMethod);
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
                view.requestSuccess(walletVO);
                System.out.println("[Start] --- response --- [Start]");
                System.out.println(response);
                System.out.println("[End] --- response --- [End]");
            }

        } catch (Exception e) {
            e.printStackTrace();
            view.requestFailure(e.getMessage());
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
                view.requestFailure(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    protected abstract void parseData(WalletVO walletVO);

}
