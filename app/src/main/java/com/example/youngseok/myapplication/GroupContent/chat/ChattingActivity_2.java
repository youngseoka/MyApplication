package com.example.youngseok.myapplication.GroupContent.chat;



import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.youngseok.myapplication.MainActivity;
import com.example.youngseok.myapplication.MygroupActivity;
import com.example.youngseok.myapplication.R;
import com.example.youngseok.myapplication.make_group.CustomAdapter;
import com.example.youngseok.myapplication.make_group.MakeGroupActivity;
import com.example.youngseok.myapplication.make_group.basicGroup;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.youngseok.myapplication.Initial.InitialActivity.save_my_id;

public class ChattingActivity_2 extends AppCompatActivity {
    Toolbar toolbar;

    ImageButton timeline;
    ImageButton mygroup;
    ImageButton makegroup;
    ImageButton invatefriend;
    ImageButton myset;



    private String CHAT_NAME;
    private String USER_NAME;

    private RecyclerView chat_view;
    private EditText chat_edit;
    private Button chat_send;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    private ArrayList<ChatDTO> chatArrayList;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_2);
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
                Intent go_main = new Intent(ChattingActivity_2.this,MainActivity.class);
                startActivity(go_main);
                overridePendingTransition(0,0);
                finish();
            }
        });

        mygroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_mygroup = new Intent(ChattingActivity_2.this,MygroupActivity.class);
                startActivity(go_mygroup);
                overridePendingTransition(0,0);
                finish();
            }
        });

        makegroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent go_make = new Intent(ChattingActivity_2.this,MakeGroupActivity.class);
                startActivity(go_make);
                overridePendingTransition(0,0);
                finish();



            }
        });








        chat_view = findViewById(R.id.chat_view);
        chat_edit = (EditText) findViewById(R.id.chat_edit);
        chat_send = (Button) findViewById(R.id.chat_sent);

        // 로그인 화면에서 받아온 채팅방 이름, 유저 이름 저장
        Intent intent = getIntent();
        CHAT_NAME = intent.getStringExtra("chatName");
        USER_NAME = intent.getStringExtra("userName");

        // 채팅 방 입장
        openChat(CHAT_NAME);




        // 메시지 전송 버튼에 대한 클릭 리스너 지정
        chat_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chat_edit.getText().toString().equals(""))
                    return;

                ChatDTO chat = new ChatDTO(USER_NAME, chat_edit.getText().toString()); //ChatDTO를 이용하여 데이터를 묶는다.
                databaseReference.child("chat").child(CHAT_NAME).push().setValue(chat); // 데이터 푸쉬
                chat_edit.setText(""); //입력창 초기화

            }
        });



    }


    private void addMessage(DataSnapshot dataSnapshot, ArrayList adapter) {
        ChatDTO chatDTO = dataSnapshot.getValue(ChatDTO.class);

        adapter.add(0,chatDTO);
    }



    private void openChat(String chatName) {
        // 리스트 어댑터 생성 및 세팅
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);



        chat_view.setLayoutManager(mLayoutManager);
        chatArrayList=new ArrayList<>();

        chatAdapter = new ChatAdapter(this,chatArrayList);

        chat_view.setAdapter(chatAdapter);
        chatAdapter.notifyDataSetChanged();

        // 데이터 받아오기 및 어댑터 데이터 추가 및 삭제 등..리스너 관리
        databaseReference.child("chat").child(chatName).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                addMessage(dataSnapshot, chatArrayList);
                chatAdapter.notifyDataSetChanged();
                Log.e("LOG", "s:"+s);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
          //      removeMessage(dataSnapshot, chatAdapter);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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
