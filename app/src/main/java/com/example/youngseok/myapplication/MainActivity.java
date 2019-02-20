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

        Intent intent_go_initial = new Intent(this,InitialActivity.class);
        intent_go_initial.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivityForResult(intent_go_initial,1024);
        // 시작과 동시에 initialActivity로 엑티비티 전환하기 flag로 1024를 줘서 initial에서도 1024를 찾아서 여기로 다시 돌아온다.

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
