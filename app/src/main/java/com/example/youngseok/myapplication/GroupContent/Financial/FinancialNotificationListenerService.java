package com.example.youngseok.myapplication.GroupContent.Financial;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;

public class FinancialNotificationListenerService extends NotificationListenerService {




    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("NotificationListener", "[snowdeer] onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("NotificationListener", "[snowdeer] onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("NotificationListener", "[snowdeer] onDestroy()");
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Log.i("NotificationListener", "[snowdeer] onNotificationPosted() - " + sbn.toString());
        Log.i("NotificationListener", "[snowdeer] PackageName:" + sbn.getPackageName());
        Log.i("NotificationListener", "[snowdeer] PostTime:" + sbn.getPostTime());

        Notification notificatin = sbn.getNotification();
        Bundle extras = notificatin.extras;
        String title = extras.getString(Notification.EXTRA_TITLE);
        int smallIconRes = extras.getInt(Notification.EXTRA_SMALL_ICON);
        Bitmap largeIcon = ((Bitmap) extras.getParcelable(Notification.EXTRA_LARGE_ICON));
        CharSequence text = extras.getCharSequence(Notification.EXTRA_TEXT);
        String aa = extras.getString(Notification.EXTRA_TEXT);
        CharSequence subText = extras.getCharSequence(Notification.EXTRA_SUB_TEXT);

        Log.i("NotificationListener", "[snowdeer] Title:" + title);
        Log.i("NotificationListener", "[snowdeer] Text:" + text);
        Log.i("NotificationListener", "[snowdeer] Sub Text:" + subText);
        Log.i("NotificationListener","hihihihihihihihihihiihihihihihihihihihihiih");

        if(TextUtils.isEmpty(aa)){

        }
        else{
            if(sbn.getPackageName().equals("com.IBK.SmartPush.app")){   //ibk은행
                Log.e("NotificationListeneree",aa);
                String[] bb = aa.split("\\n");

                for(int index=0; index<bb.length;index++){
                    Log.e("NotificationListeneree",index+"hi"+bb[index]);
                }


            }


            else{}


        }

//        String s1 = text.toString();
//
//
//        if(TextUtils.isEmpty(s1)){
//
//        }
//        else{
//            Log.e("notificationlisteneree",s1);
//            String[] s2 = s1.split("\\n");
//
//            for(int index=0; index<s2.length;index++){
//                Log.e("notificationlisteneree",index+"ddd"+s2[index]);
//            }
//        }
//




    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i("NotificationListener", "[snowdeer] onNotificationRemoved() - " + sbn.toString());
    }
}
