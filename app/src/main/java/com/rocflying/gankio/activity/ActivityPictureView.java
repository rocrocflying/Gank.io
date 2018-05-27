package com.rocflying.gankio.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.rocflying.gankio.R;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by liupeng on 2018/5/27.
 */
public class ActivityPictureView extends ActivityBase {
    private ImageView picImageView;
    private String url;
    private android.support.v7.widget.Toolbar toolbar;

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
        picImageView = (ImageView) findViewById(R.id.iv_pic);

//        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setHomeButtonEnabled(true);
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
    }
}
