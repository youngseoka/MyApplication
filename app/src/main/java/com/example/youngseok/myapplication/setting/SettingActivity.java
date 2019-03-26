package com.example.youngseok.myapplication.setting;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.youngseok.myapplication.Initial.InitialActivity;
import com.example.youngseok.myapplication.MainActivity;
import com.example.youngseok.myapplication.MygroupActivity;
import com.example.youngseok.myapplication.R;
import com.example.youngseok.myapplication.make_group.CustomAdapter;
import com.example.youngseok.myapplication.make_group.MakeGroupActivity;
import com.example.youngseok.myapplication.make_group.basicGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.youngseok.myapplication.Initial.InitialActivity.save_my_id;

public class SettingActivity extends AppCompatActivity {
    Toolbar toolbar;

    ImageButton timeline;
    ImageButton mygroup;
    ImageButton makegroup;
    ImageButton invatefriend;
    ImageButton myset;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        toolbar = findViewById(R.id.toolbar);

        SimpleDateFormat format2 = new SimpleDateFormat ( "yyyy년 MM월 dd일");
        Date time = new Date();
        String time2 = format2.format(time);
        toolbar.setSubtitle(time2);

        timeline=findViewById(R.id.timeline_btn);
        mygroup=findViewById(R.id.new_my);
        makegroup=findViewById(R.id.new_make);
        invatefriend=findViewById(R.id.invite_btn);
        myset=findViewById(R.id.setting_btn);

        String keyword = save_my_id;


        timeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_main = new Intent(SettingActivity.this,MainActivity.class);
                startActivity(go_main);
                overridePendingTransition(0,0);
                finish();
            }
        });

        mygroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_mygroup = new Intent(SettingActivity.this,MygroupActivity.class);
                startActivity(go_mygroup);
                overridePendingTransition(0,0);
                finish();
            }
        });

        makegroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent go_make = new Intent(SettingActivity.this,MakeGroupActivity.class);
                startActivity(go_make);
                overridePendingTransition(0,0);
                finish();



            }
        });

        myset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_set = new Intent(SettingActivity.this,SettingActivity.class);
                startActivity(go_set);
                overridePendingTransition(0,0);
                finish();
            }
        });


        Button logout = findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this,InitialActivity.class);
                startActivity(intent);
                SharedPreferences saveLoginData = getSharedPreferences("appData", MODE_PRIVATE);
                SharedPreferences.Editor editor = saveLoginData.edit();
                editor.clear();
                editor.commit();
                finish();






            }
        });





    }







    @Override
    protected void onStart(){
        super.onStart();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
    @Override
    protected void onPause(){
        super.onPause();
    }
    @Override
    protected void onRestart(){
        super.onRestart();
    }
    @Override
    protected void onResume(){
        super.onResume();
    }
    @Override
    protected void onStop(){
        super.onStop();
    }

}