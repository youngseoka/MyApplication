package com.example.youngseok.myapplication.Initial;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.youngseok.myapplication.R;

public class InitialActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        Intent intent_loading = new Intent(this,loadingActivity.class);
        startActivity(intent_loading);
    }

    @Override
    public void onBackPressed(){
    //취소버튼을 눌러도 메인액티비티로 가는게 아니라 아무 동작 안하게 설정
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

