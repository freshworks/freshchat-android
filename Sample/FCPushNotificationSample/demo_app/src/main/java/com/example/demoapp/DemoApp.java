package com.example.demoapp;


import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.example.demoapp.activity.HomeActivity;
import com.freshchat.consumer.sdk.Freshchat;
import com.freshchat.consumer.sdk.FreshchatCallbackStatus;
import com.freshchat.consumer.sdk.FreshchatConfig;
import com.freshchat.consumer.sdk.FreshchatNotificationConfig;
import com.freshchat.consumer.sdk.UnreadCountCallback;
import com.freshchat.consumer.sdk.exception.MethodNotAllowedException;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

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

        /**
         * This is the Firebase push notification server key for this sample app.
         * Please save this in your Freshchat account to test push notifications in Sample app.
         *
         * Server Key:
         * AAAAWQmY32o:APA91bF_X9S3rrfBLT5kUVaZ2uftEIpqALXu3z05SoTZhAikINhGmUsd62jQE374vgHLZyILA3lhsAxLJSQlivsZcK_yY_DAjdAQTesUpabs1_5XiRsonrWDm5envw24nQSPTDG81g1w
         *
         * Note: This is the push notification server key for sample app. You need to use your own server key for testing in your application
         */

        Uri soundUri = Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.musical003);

        FreshchatNotificationConfig notificationConfig = new FreshchatNotificationConfig()
                .setNotificationSoundEnabled(true)
                .setNotificationSound(soundUri)
                .setImportance(NotificationManagerCompat.IMPORTANCE_MAX);

        getFreshchatInstance(getApplicationContext()).setNotificationConfig(notificationConfig);
    }

    private Freshchat getFreshchatInstance(Context context) {
        if (freshchat == null) {
            freshchat = Freshchat.getInstance(context);
        }
        return freshchat;
    }
}
