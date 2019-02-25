package com.example.youngseok.myapplication.Initial;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.youngseok.myapplication.R;

public class signupActivity extends Activity {

    private LinearLayout layout_1, layout_2, layout_3,layout_4,layout_5,layout_6;
    private Button agree_btn,disagree_btn;
    //레이아웃들


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
    }
}
