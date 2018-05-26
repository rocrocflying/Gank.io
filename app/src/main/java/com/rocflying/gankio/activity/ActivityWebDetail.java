package com.rocflying.gankio.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.rocflying.gankio.R;

/**
 * Created by liupeng on 2018/5/26.
 */
public class ActivityWebDetail extends ActivityBase {

    private ProgressBar loadProgressBar;
    private WebView webView;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        initView();
        initData();
        initEvent();
    }

    public static void openActivity(Context context, String url) {
        Intent intent = new Intent(context, ActivityWebDetail.class);
        intent.putExtra("url", url);
        context.startActivity(intent);

    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_webdetail);
    }

    @Override
    public void initView() {
        loadProgressBar = (ProgressBar) findViewById(R.id.pg_webview);
        webView = (WebView) findViewById(R.id.wb_article);
    }

    @Override
    public void initData() {
        url = getIntent().getStringExtra("url");

    }

    @Override
    public void initEvent() {

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);// ȡ�����½����Ű�ť��ʾ
        webView.getSettings().setDefaultTextEncodingName("utf-8");

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                loadProgressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    loadProgressBar.setVisibility(View.GONE);
                } else {
                    loadProgressBar.setVisibility(View.VISIBLE);
                }
            }
        });
        if (!TextUtils.isEmpty(url)) {
            webView.loadUrl(url);
        }

    }
}
