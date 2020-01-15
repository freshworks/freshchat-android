package com.example.demoapp;


import android.app.Application;

import android.content.Context;
import com.freshchat.consumer.sdk.Freshchat;
import com.freshchat.consumer.sdk.FreshchatConfig;

public class DemoApp extends Application {

    private Freshchat freshchat;

    @Override
    public void onCreate() {
        super.onCreate();
        initialiseFreshchat();
    }


    private void initialiseFreshchat() {

        FreshchatConfig freshchatConfig = new FreshchatConfig("APP_ID", "APP_KEY");
        getFreshchatInstance(getApplicationContext()).init(freshchatConfig);
    }

    private Freshchat getFreshchatInstance(Context context) {
        if (freshchat == null) {
            freshchat = Freshchat.getInstance(context);
        }
        return freshchat;
    }
}