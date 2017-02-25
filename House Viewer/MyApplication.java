package com.example.halper.listlab;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
/**
 * Created by halper on 10/5/2016.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
