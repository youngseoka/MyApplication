package com.example.youngseok.myapplication.Initial;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.youngseok.myapplication.R;

public class InitialActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
    }

    @Override
    public void onBackPressed(){

    }



    @Override
    protected void onStart(){
        super.onStart();
        android.util.Log.i("test","onStart_initial");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        android.util.Log.i("test","onDestroy_initial");
    }
    @Override
    protected void onPause(){
        super.onPause();
        android.util.Log.i("test","onPause_initial");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        android.util.Log.i("test","onRestart_initial");
    }
    @Override
    protected void onResume(){
        super.onResume();
        android.util.Log.i("test","onResume_initial");

    }
    @Override
    protected void onStop(){
        super.onStop();
        android.util.Log.i("test","onStop_initial");
    }


}

