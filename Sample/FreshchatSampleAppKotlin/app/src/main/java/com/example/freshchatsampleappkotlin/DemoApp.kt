package com.example.freshchatsampleappkotlin

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.freshchat.consumer.sdk.*
import com.freshchat.consumer.sdk.FreshchatConfig

class DemoApp : Application() {
    private var freshchat: Freshchat? = null

    override fun onCreate() {
        super.onCreate()
        initialiseFreshchat()
        registerBroadcastReceiver()
    }

    private fun initialiseFreshchat() {
        val freshchatConfig = FreshchatConfig(
            "abeaa0e6-b2de-4852-9a75-90d91e64294f",
            "eb7a2fa0-edeb-468e-bbcd-3459103f30c0"
        )
        freshchatConfig.domain = "msdk.au.freshchat.com"
        getFreshchatInstance(applicationContext)?.init(freshchatConfig)

        val soundUri =
            Uri.parse("android.resource://" + applicationContext.packageName + "/" + R.raw.musical003)
        val notificationConfig = FreshchatNotificationConfig()
            .setNotificationSoundEnabled(true)
            .setNotificationSound(soundUri)
            .setImportance(NotificationManagerCompat.IMPORTANCE_MAX)
        getFreshchatInstance(applicationContext)?.setNotificationConfig(notificationConfig)
    }

    private fun getFreshchatInstance(context: Context): Freshchat? {
        if (freshchat == null) {
            freshchat = Freshchat.getInstance(context)
        }
        return freshchat
    }

    private fun registerBroadcastReceiver() {
        val intentFilterUnreadMessageCount =
            IntentFilter(Freshchat.FRESHCHAT_UNREAD_MESSAGE_COUNT_CHANGED)
        val intentFilterRestoreID = IntentFilter(Freshchat.FRESHCHAT_USER_RESTORE_ID_GENERATED)
        getLocalBroadcastManager().registerReceiver(restoreIdReceiver, intentFilterRestoreID)
        getLocalBroadcastManager().registerReceiver(
            unreadCountChangeReceiver,
            intentFilterUnreadMessageCount
        )
    }

    fun getLocalBroadcastManager(): LocalBroadcastManager {
        return LocalBroadcastManager.getInstance(applicationContext)
    }

    override fun onTerminate() {
        super.onTerminate()
        getLocalBroadcastManager().unregisterReceiver(unreadCountChangeReceiver)
        getLocalBroadcastManager().unregisterReceiver(restoreIdReceiver)
    }

    var unreadCountChangeReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Freshchat.getInstance(applicationContext)
                .getUnreadCountAsync { freshchatCallbackStatus, unreadCount ->
                    Toast.makeText(
                        applicationContext,
                        "Unread message Count - $unreadCount",
                        Toast.LENGTH_LONG
                    ).show()
                }
        }
    }

    var restoreIdReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // TODO: Save this restoreId to app's backend for restoring across platforms and session
            val restoreId = Freshchat.getInstance(applicationContext).user.restoreId
            Toast.makeText(context, "Restore id: $restoreId", Toast.LENGTH_SHORT).show()
        }
    }
}