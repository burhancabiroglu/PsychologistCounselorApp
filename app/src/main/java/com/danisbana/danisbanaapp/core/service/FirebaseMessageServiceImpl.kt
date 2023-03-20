package com.danisbana.danisbanaapp.core.service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.widget.RemoteViews
import androidx.core.app.NotificationBuilderWithBuilderAccessor
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.danisbana.danisbanaapp.R
import com.danisbana.danisbanaapp.presentation.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


class FirebaseMessageServiceImpl: FirebaseMessagingService() {
    private val firestore: FirebaseFirestore get() = Firebase.firestore
    private val auth: FirebaseAuth get() = Firebase.auth

    override fun onCreate() {
        super.onCreate()
    }
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        updateFCMToken(auth.currentUser?.uid.toString(),token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val title = message.data.getOrDefault("title","")
        val body = message.data.getOrDefault("body","")
        handleNotification(message.sentTime.toInt(),title,body)
    }

    @SuppressLint("MissingPermission", "RestrictedApi")
    fun handleNotification(id:Int, title: String?, body: String?) {
        val intent = Intent(this,MainActivity::class.java)
        intent.addFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val channel =  NotificationChannel(
            "CHANNEL_ID",
            "CHANNEL_NAME",
            NotificationManager.IMPORTANCE_HIGH
        )

        val notificationManager = getSystemService(NotificationManager::class.java) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        val remoteView = RemoteViews(packageName, R.layout.notification_layout)

        remoteView.setTextViewText(R.id.title,title)
        remoteView.setTextViewText(R.id.body,body)

        val notificationBuilder = NotificationCompat.Builder(this,"CHANNEL_ID")
            .setContent(remoteView)
            .setCustomBigContentView(remoteView)
            .setCustomContentView(remoteView)
            .setCustomHeadsUpContentView(remoteView)
            .setSmallIcon(R.drawable.ic_lotus)
            .setChannelId("CHANNEL_ID")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setFullScreenIntent(pendingIntent, true)

        val notification =  notificationBuilder.build()
        NotificationManagerCompat.from(baseContext).notify(id, notification)
        /* runBlocking {
            delay(15000)
            notificationManager.deleteNotificationChannel("CHANNEL_ID")
        }*/
    }

    private fun updateFCMToken(uid: String, token: String) {
        val updates: Map<String,Any> = hashMapOf("cloudToken" to token.trim())
        firestore.collection("users").document(uid).update(updates)
    }
}