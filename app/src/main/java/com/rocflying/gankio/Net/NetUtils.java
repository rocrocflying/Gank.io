package com.rocflying.gankio.Net;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Looper;
import android.provider.MediaStore;
import android.widget.Toast;

import com.rocflying.gankio.application.GankApplication;
import com.rocflying.gankio.ui.WaitProgressDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.rocflying.gankio.Net.HttpCallBack.codeErro;
import static com.rocflying.gankio.Net.HttpCallBack.codeSuccess;

/**
 * Created by liupeng on 2018/5/26.
 */
public class NetUtils {

    private OkHttpClient okHttpClient;

    public static NetUtils netUtils;
    private NetWorkCallBack callBack;

    public static NetUtils getInstance() {
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

    public void downloadFile(final Context context, final String url, final NetWorkCallBack callBack) {
        Request request = new Request.Builder().get().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onResponseFinish(codeErro, null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream in = null;
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/gank" + File.separator + System.currentTimeMillis();

                try {
                    in = response.body().byteStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(in);
                    File imgFile = new File(path + ".jpg");
                    if (!imgFile.getParentFile().exists()) {
                        imgFile.getParentFile().mkdirs();
                    }
                    if (!imgFile.exists()) {
                        imgFile.createNewFile();
                    } else {
                        callBack.onResponseFinish(codeErro, null);
                        Toast.makeText(GankApplication.getContext(), "图片已下载", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    FileOutputStream outputStream = new FileOutputStream(imgFile);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream);
                    outputStream.flush();
                    outputStream.close();
                    Looper.prepare();
                    callBack.onResponseFinish(codeSuccess, null);
                    Toast.makeText(GankApplication.getContext(), "图片保存成功", Toast.LENGTH_SHORT).show();
                    try {
                        MediaStore.Images.Media.insertImage(context.getContentResolver(), imgFile.getAbsolutePath(), imgFile.getName(), null);
                        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(imgFile.getAbsolutePath())));
                    } catch (Exception e) {

                    }
                    Looper.loop();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public interface NetWorkCallBack {
        void onResponseFinish(int code, String result);
    }

}
