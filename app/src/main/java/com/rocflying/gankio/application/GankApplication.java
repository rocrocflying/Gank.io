package com.rocflying.gankio.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by liupeng on 2018/6/14.
 */
public class GankApplication extends Application {

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
