package com.example.a.Listener;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


import com.example.a.woofui.R;
import com.example.a.woofui.WalkActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by apple on 2018/3/11.
 */


public class FCMListenerService extends FirebaseMessagingService {

    static int count=0;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d("FCM","-----------on MessageReceived------------ ");
        if (remoteMessage.getData().size() > 0){
            Log.d("FCM","--------------Message data payload" + remoteMessage.getData());

        }
        //if(remoteMessage.getData() != null){
            Intent intent = new Intent(this, WalkActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                    PendingIntent.FLAG_ONE_SHOT);

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            if(Build.VERSION.SDK_INT>=26) {
                NotificationChannel mChannel = new NotificationChannel("woof", "test", NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(mChannel);
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "woof")
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle("WOOF")
                        .setContentText(remoteMessage.getData().get("body").toString())
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

                notificationManager.notify(0, notificationBuilder.build());
            }
            else
            {
                final Notification.Builder notif = new Notification.Builder(getApplicationContext())
                        .setContentTitle(remoteMessage.getData().get("title").toString())
                        .setContentText(remoteMessage.getData().get("body").toString())
                        //      .setTicker(getString(R.string.tick)) removed, seems to not show at all
                        //      .setWhen(System.currentTimeMillis()) removed, match default
                        //      .setContentIntent(contentIntent) removed, I don't neet it
                        //.setColor(Color.parseColor("#00000")) //ok
                        .setPriority(Notification.PRIORITY_HIGH)
                        .setSmallIcon(R.drawable.cc) //ok
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.dp1));

                notificationManager.notify(count++,notif.build());
            }
        //}
    }
}


