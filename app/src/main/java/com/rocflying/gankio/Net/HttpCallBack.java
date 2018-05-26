package com.rocflying.gankio.Net;

import android.os.Looper;

import java.io.IOException;
import java.util.logging.Handler;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by liupeng on 2018/5/26.
 */
public class HttpCallBack implements Callback {
    private NetUtils.NetWorkCallBack callback;
    private android.os.Handler handler = new android.os.Handler(Looper.getMainLooper());
    private final int codeErro = 0;

    public HttpCallBack(NetUtils.NetWorkCallBack callback) {
        this.callback = callback;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onResponseFinish(codeErro, null);

            }
        });

    }

    @Override
    public void onResponse(final Call call,final  Response response) throws IOException {
       final String result= response.body().string();
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onResponseFinish(response.code(),result );

            }
        });
    }
}
