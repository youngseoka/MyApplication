package com.example.youngseok.myapplication.make_group;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.youngseok.myapplication.Initial.join.joinRequest;
import com.example.youngseok.myapplication.Initial.signupActivity;
import com.example.youngseok.myapplication.MainActivity;
import com.example.youngseok.myapplication.MygroupActivity;
import com.example.youngseok.myapplication.R;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.youngseok.myapplication.Initial.InitialActivity.instance_save_id;
import static com.example.youngseok.myapplication.Initial.InitialActivity.save_my_id;

public class MakeGroupActivity extends AppCompatActivity {
    Toolbar toolbar;

    ImageButton timeline;
    ImageButton mygroup;
    ImageButton makegroup;
    ImageButton invatefriend;
    ImageButton myset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makegroup);
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

        timeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_main = new Intent(MakeGroupActivity.this,MainActivity.class);
                startActivity(go_main);
                overridePendingTransition(0,0);
                finish();
            }
        });

        mygroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_mygroup = new Intent(MakeGroupActivity.this,MygroupActivity.class);
                startActivity(go_mygroup);
                overridePendingTransition(0,0);
                finish();
            }
        });

        final EditText insert_recycle_name = findViewById(R.id.insert_recycle_name);
        final EditText insert_recycle_content = findViewById(R.id.insert_recycle_content);
        final EditText insert_recycle_sumnail=findViewById(R.id.insert_recycle_sumnail);
        Button confirm = findViewById(R.id.recycler_confirm_btn);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strid = save_my_id;
                String strname = insert_recycle_name.getText().toString();
                String strcontent = insert_recycle_content.getText().toString();
                String strsumnail = insert_recycle_sumnail.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);

                            boolean success = jsonResponse.getBoolean("success");

                            if(success){



                            }
                            else{


                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };



                insertRequest insertrequest =new insertRequest(strid,strname,strcontent,strsumnail,responseListener);
                RequestQueue queue = Volley.newRequestQueue(MakeGroupActivity.this);

                queue.add(insertrequest);




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
