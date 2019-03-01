package com.example.youngseok.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.youngseok.myapplication.Initial.InitialActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }





    @Override
    protected void onStart(){
        super.onStart();
        android.util.Log.i("test","onStart_main");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        android.util.Log.i("test","onDestroy_main");
    }
    @Override
    protected void onPause(){
        super.onPause();
        android.util.Log.i("test","onPause_main");

    }
    @Override
    protected void onRestart(){
        super.onRestart();
        android.util.Log.i("test","onRestart_main");
    }
    @Override
    protected void onResume(){
        super.onResume();
        android.util.Log.i("test","onResume_main");

    }
    @Override
    protected void onStop(){
        super.onStop();
        android.util.Log.i("test","onStop_main");

    }

}
