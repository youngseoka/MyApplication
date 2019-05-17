package com.example.youngseok.myapplication.GroupContent.Financial;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;

import com.example.youngseok.myapplication.R;
import com.example.youngseok.myapplication.make_group.CustomAdapter;
import com.example.youngseok.myapplication.make_group.basicGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import static com.example.youngseok.myapplication.Initial.InitialActivity.save_my_id;

public class FinancialActivity extends AppCompatActivity {



    Toolbar toolbar;

    ImageButton timeline;
    ImageButton mygroup;
    ImageButton makegroup;
    ImageButton invitefriend;
    ImageButton myset;

    private ArrayList<basicGroup> mArrayList;
    private CustomAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private int count = 0;

    private String mJsonString;

    private String master_key;
    private String master_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        toolbar = findViewById(R.id.toolbar);

        SimpleDateFormat format2 = new SimpleDateFormat("yyyy년 MM월 dd일");
        Date time = new Date();
        String time2 = format2.format(time);
        toolbar.setSubtitle(time2);

        timeline = findViewById(R.id.timeline_btn);
        mygroup = findViewById(R.id.new_my);
        makegroup = findViewById(R.id.new_make);
        invitefriend = findViewById(R.id.invite_btn);
        myset = findViewById(R.id.setting_btn);

        String keyword = save_my_id;

        Intent intent_group_content = getIntent();
        master_id=intent_group_content.getStringExtra("master_id");
        master_key=intent_group_content.getStringExtra("master_key");


        boolean isPermissionAllowed = isNotiPermissionAllowed();

        if(!isPermissionAllowed) {
            Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            startActivity(intent);
        }





















    }

    private boolean isNotiPermissionAllowed() {
        Set<String> notiListenerSet = NotificationManagerCompat.getEnabledListenerPackages(this);
        String myPackageName = getPackageName();

        for(String packageName : notiListenerSet) {
            if(packageName == null) {
                continue;
            }
            if(packageName.equals(myPackageName)) {
                return true;
            }
        }

        return false;
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
