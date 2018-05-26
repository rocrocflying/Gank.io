package com.rocflying.gankio.Net;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by liupeng on 2018/5/26.
 */
public class NetUtils {

    private OkHttpClient okHttpClient;

    public static NetUtils netUtils;

    public  static NetUtils  getInstance() {
        return NetHolders.netUtils;
    }

    public NetUtils() {

        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }

    }

    private static class NetHolders {
        private static final NetUtils netUtils = new NetUtils();
    }

    public void doPost(String url, RequestBody body, NetWorkCallBack callBack) {
        Request request = new Request.Builder().post(body).url(url).build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new HttpCallBack(callBack));


    }

    public void doGet(String url, NetWorkCallBack callBack) {
        Request request = new Request.Builder().url(url).get().build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new HttpCallBack(callBack));

    }

    public interface NetWorkCallBack {
        void onResponseFinish(int code, String result);
    }

}
