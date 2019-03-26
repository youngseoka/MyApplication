package com.example.youngseok.myapplication.GroupContent.chat.noti;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseInstanceIDService";



    @Override

    public void onTokenRefresh() {

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("MyFCM", "FCM token: " + FirebaseInstanceId.getInstance().getToken());




        sendRegistrationToServer(refreshedToken);

    }



    private void sendRegistrationToServer(String token) {

    }

}


