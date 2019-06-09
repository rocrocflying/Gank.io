package com.rocflying.gankio.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.rocflying.gankio.Net.NetUtils;
import com.rocflying.gankio.R;
import com.rocflying.gankio.ui.WaitProgressDialog;

import uk.co.senab.photoview.PhotoViewAttacher;

import static com.rocflying.gankio.Net.HttpCallBack.codeSuccess;

/**
 * Created by liupeng on 2018/5/27.
 */
public class ActivityPictureView extends ActivityBase implements View.OnClickListener {
    private ImageView picImageView;
    private String url;
    private android.support.v7.widget.Toolbar toolbar;

    private ImageView ivBack;
    private ImageView ivSave;

    @Override
    public void setContentView() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_picture_view);
        getView();
        getData();
        ivBack.setOnClickListener(this);
        ivSave.setOnClickListener(this);
    }

    public static void openActivityPictureView(Context context, String url) {
        Intent intent = new Intent(context, ActivityPictureView.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    private void getData() {
        url = getIntent().getStringExtra("url");
        Glide.with(this).load(url).centerCrop().into(picImageView);
        PhotoViewAttacher attacher = new PhotoViewAttacher(picImageView);
    }

    private void getView() {
        getSupportActionBar().hide();
        picImageView = (ImageView) findViewById(R.id.iv_pic);
        ivBack = findViewById(R.id.iv_back);
        ivSave = findViewById(R.id.iv_save);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_save:
                showDownloadDialog();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private void showDownloadDialog() {
        final WaitProgressDialog dialog = new WaitProgressDialog(this);
        dialog.setMsg(getString(R.string.title_download));
        dialog.show();
        NetUtils.getInstance().downloadFile(this,url, new NetUtils.NetWorkCallBack() {
            @Override
            public void onResponseFinish(int code, String result) {
                dialog.dismiss();
            }
        });
    }


}
