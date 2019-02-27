package com.example.youngseok.myapplication.Initial;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.youngseok.myapplication.R;

public class InitialActivity extends Activity {

    private Button signup_btn;  //회원가입 버튼

    public static String instance_save_id = ""; //static 선언해서 회원가입시 아이디 전달받게할거임. 처음에는 아무 문자 표시안함.

    private EditText insert_id; //아이디 입력창
    private CheckBox remember_id;   //아이디 기억 체크박스
    private SharedPreferences appData;  //로그인 기억 체크박스, 아이디값 저장하는 변수
    private boolean saveLoginData;  //체크박스 체크했는지 안했는지 확인하는친구

    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        Intent intent_loading = new Intent(this,loadingActivity.class);
        startActivity(intent_loading);
        //로딩화면으로 이동

        signup_btn = findViewById(R.id.signup_btn);
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v_signup_btn) {
                Intent go_signup = new Intent(getApplicationContext(),signupActivity.class);
                go_signup.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivityForResult(go_signup,1000);
                //회원가입 버튼 누를시 회원가입 페이지로 이동
            }
        });

        insert_id = findViewById(R.id.insert_id);
        remember_id = findViewById(R.id.Remember_id);
        appData = getSharedPreferences("appData",MODE_PRIVATE);

        loadData();
        if(saveLoginData){
            insert_id.setText(id);
            remember_id.setChecked(saveLoginData);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

    }




    @Override
    public void onBackPressed(){
    //취소버튼을 눌러도 메인액티비티로 가는게 아니라 아무 동작 안하게 설정
    }

    private void saveData(){
        SharedPreferences.Editor editor = appData.edit();
        editor.putBoolean("SAVE_LOGIN_DATA",remember_id.isChecked());
        editor.putString("ID",insert_id.getText().toString().trim());
        editor.apply();
    }
    private void loadData(){
        saveLoginData=appData.getBoolean("SAVE_LOGIN_DATA",false);

        id = appData.getString("ID","");
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
        insert_id.setText(instance_save_id);
        loadData();
        if(saveLoginData){
            insert_id.setText(id);
            remember_id.setChecked(saveLoginData);
        }

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
        saveData();
        android.util.Log.i("test","onStop_initial");
    }


}

