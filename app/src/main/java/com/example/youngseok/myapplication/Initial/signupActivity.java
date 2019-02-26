package com.example.youngseok.myapplication.Initial;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.youngseok.myapplication.Initial.join.idValidate;
import com.example.youngseok.myapplication.R;

import org.json.JSONObject;

public class signupActivity extends Activity {

    private LinearLayout layout_1, layout_2, layout_3,layout_4,layout_5,layout_6;
    private Button agree_btn,disagree_btn;
    //레이아웃들

    private String name,id,password,nickname,email,phone;
    private Boolean validate = false;   //아이디 중복확인시 boolean으로 중복확인



    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_signup);

        layout_1=findViewById(R.id.layout_1);
        layout_2=findViewById(R.id.layout_2);
        layout_3=findViewById(R.id.layout_3);
        layout_4=findViewById(R.id.layout_4);
        layout_5=findViewById(R.id.layout_5);
        layout_6=findViewById(R.id.layout_6);

        agree_btn=findViewById(R.id.agree_btn);
        //동의 버튼 누르면 위에 약관들 다 사라지고 회원가입 폼 나타남
        agree_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_1.setVisibility(View.GONE);
                layout_2.setVisibility(View.GONE);
                layout_3.setVisibility(View.GONE);
                layout_4.setVisibility(View.GONE);
                layout_6.setVisibility(View.VISIBLE);
            }
        });

        disagree_btn=findViewById(R.id.disagree_btn);
        //기본 디폴트 화면인데 동의 누른상태에서 반대를 누르면 폼 다 사라지고 다시 약관 나타나게함
        disagree_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_1.setVisibility(View.VISIBLE);
                layout_2.setVisibility(View.VISIBLE);
                layout_3.setVisibility(View.VISIBLE);
                layout_4.setVisibility(View.VISIBLE);
                layout_6.setVisibility(View.GONE);
            }
        });


        final EditText name = findViewById(R.id.signup_name);
        final EditText id = findViewById(R.id.signup_id);
        final EditText password = findViewById(R.id.signup_passwd);
        final EditText password_chk = findViewById(R.id.signup_passwd_chk);
        final EditText nickname = findViewById(R.id.signup_nick);
        final EditText email = findViewById(R.id.signup_email);
        final EditText phone = findViewById(R.id.signup_phone);

        final Button idvalidate_btn = findViewById(R.id.signup_id_btn);
        idvalidate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID=id.getText().toString();
                if(validate){
                    return;//검증 완료시
                }

                if(userID.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(signupActivity.this);
                    AlertDialog dialog = builder.setMessage("아이디를 입력해주세요").setPositiveButton("ok",null).create();
                    dialog.show();
                    return;

                }

                Response.Listener<String> responseListener = new Response.Listener<String>(){


                    @Override
                    public void onResponse(String response){
                        try{


                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success =jsonResponse.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(signupActivity.this);
                                AlertDialog dialog = builder.setMessage("사용가능합니다").setPositiveButton("ok",null).create();
                                dialog.show();

                                id.setEnabled(false);
                                validate=true;

                                idvalidate_btn.setBackgroundColor(getResources().getColor(R.color.colorGray));

                            }
                            else{
                                AlertDialog.Builder builder=new AlertDialog.Builder(signupActivity.this);
                                AlertDialog dialog = builder.setMessage("이미 존재하는 아이디 입니다.").setNegativeButton("ok",null).create();
                                dialog.show();
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };

                idValidate idvalidate = new idValidate(userID,responseListener);
                RequestQueue queue = Volley.newRequestQueue(signupActivity.this);
                queue.add(idvalidate);

            }
        });







    }
}
