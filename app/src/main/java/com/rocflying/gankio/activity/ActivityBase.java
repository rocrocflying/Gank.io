package com.rocflying.gankio.activity;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by liupeng on 2018/5/26.
 */
public abstract class ActivityBase extends AppCompatActivity {

    public abstract void setContentView();

    public abstract void initView();

    public abstract void initData();

    public abstract void initEvent();

}
