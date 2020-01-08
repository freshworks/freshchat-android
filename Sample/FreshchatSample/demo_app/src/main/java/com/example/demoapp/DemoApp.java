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

import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class DemoApp extends Application {

    private Freshchat freshchat;

    @Override
    public void onCreate() {
        super.onCreate();
        initialiseFreshchat();
        registerBroadcastReceiver();
    }

    private void registerBroadcastReceiver() {
        IntentFilter intentFilterUnreadMessagCount = new IntentFilter(Freshchat.FRESHCHAT_UNREAD_MESSAGE_COUNT_CHANGED);
        IntentFilter intentFilterRestoreID = new IntentFilter(Freshchat.FRESHCHAT_USER_RESTORE_ID_GENERATED);
        getLocalBroadcastManager().registerReceiver(restoreIdReceiver, intentFilterRestoreID);
        getLocalBroadcastManager().registerReceiver(unreadCountChangeReceiver, intentFilterUnreadMessagCount);
    }

    public LocalBroadcastManager getLocalBroadcastManager() {
        return LocalBroadcastManager.getInstance(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        getLocalBroadcastManager().unregisterReceiver(unreadCountChangeReceiver);
        getLocalBroadcastManager().unregisterReceiver(restoreIdReceiver);
    }

    BroadcastReceiver unreadCountChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Freshchat.getInstance(getApplicationContext()).getUnreadCountAsync(new UnreadCountCallback() {
                @Override
                public void onResult(FreshchatCallbackStatus freshchatCallbackStatus, int unreadCount) {
                    Toast.makeText(getApplicationContext(), "Unread message Count - " + unreadCount, Toast.LENGTH_LONG).show();
                }
            });
        }
    };

    BroadcastReceiver restoreIdReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO: Save this restoreId to app's backend for restoring across platforms and session
            String restoreId = Freshchat.getInstance(getApplicationContext()).getUser().getRestoreId();
            Toast.makeText(context, "Restore id: " + restoreId, Toast.LENGTH_SHORT).show();
        }

    };

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
