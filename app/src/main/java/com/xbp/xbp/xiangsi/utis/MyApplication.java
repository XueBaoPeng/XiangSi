package com.xbp.xbp.xiangsi.utis;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;

import cn.bmob.v3.Bmob;


/**
 * Created by Administrator on 2015/10/15.
 */
public class MyApplication extends Application {

    public static RequestQueue queue;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        queue= Volley.newRequestQueue(getApplicationContext());
        init();
    }

    public static RequestQueue getQueue(){
        return  queue;
    }

    private void init() {
        Bmob.initialize(this, "97069ef7662ed784330c54803cd9f754");

    }


}
