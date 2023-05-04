package com.example.freshchatsampleappkotlin

import android.util.Log
import com.freshchat.consumer.sdk.Freshchat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(s: String) {
        super.onNewToken(s)

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(s)
    }

    fun sendRegistrationToServer(token: String) {
        Freshchat.getInstance(this).setPushRegistrationToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            Log.d("Reva", "Message data payload: " + remoteMessage.data)
        }
        if (Freshchat.isFreshchatNotification(remoteMessage)) {
            Freshchat.handleFcmMessage(this, remoteMessage)
        } else {
            //Handle notifications for app
        }
    }
}
