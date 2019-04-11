package com.example.youngseok.myapplication.GroupContent;


import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.youngseok.myapplication.GroupContent.chat.ChattingActivity;
import com.example.youngseok.myapplication.GroupContent.member_list.Member_listActivity;
import com.example.youngseok.myapplication.MainActivity;
import com.example.youngseok.myapplication.MygroupActivity;
import com.example.youngseok.myapplication.R;
import com.example.youngseok.myapplication.invite.InviteActivity;
import com.example.youngseok.myapplication.make_group.CustomAdapter;
import com.example.youngseok.myapplication.make_group.MakeGroupActivity;
import com.example.youngseok.myapplication.make_group.basicGroup;
import com.example.youngseok.myapplication.setting.SettingActivity;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.acl.Group;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.youngseok.myapplication.Initial.InitialActivity.save_my_id;

public class GroupContentActivity extends AppCompatActivity {
    Toolbar toolbar;

    ImageButton timeline;
    ImageButton mygroup;
    ImageButton makegroup;
    ImageButton invitefriend;
    ImageButton myset;

    Button Chat_btn;

    private String group_name;
    private String master_key;

    private TextView member_number;

    private String mJsonString;

    private ArrayList<GroupContentDTO> mArraylist_content;

    Button member_list;
    Button moim_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupcontent);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        toolbar = findViewById(R.id.toolbar);

        SimpleDateFormat format2 = new SimpleDateFormat ( "yyyy년 MM월 dd일");
        Date time = new Date();
        String time2 = format2.format(time);
        toolbar.setSubtitle(time2);

        timeline=findViewById(R.id.timeline_btn);
        mygroup=findViewById(R.id.new_my);
        makegroup=findViewById(R.id.new_make);
        invitefriend=findViewById(R.id.invite_btn);
        myset=findViewById(R.id.setting_btn);

        Chat_btn = findViewById(R.id.Chat_btn);

        member_list=findViewById(R.id.member_list);
        moim_exit=findViewById(R.id.moim_exit);


        timeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_main = new Intent(GroupContentActivity.this,MainActivity.class);
                startActivity(go_main);
                overridePendingTransition(0,0);
                finish();
            }
        });

        mygroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_mygroup = new Intent(GroupContentActivity.this,MygroupActivity.class);
                startActivity(go_mygroup);
                overridePendingTransition(0,0);
                finish();
            }
        });

        makegroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent go_make = new Intent(GroupContentActivity.this,MakeGroupActivity.class);
                startActivity(go_make);
                overridePendingTransition(0,0);
                finish();



            }
        });
        myset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_set = new Intent(GroupContentActivity.this,SettingActivity.class);
                startActivity(go_set);
                overridePendingTransition(0,0);
                finish();
            }
        });

        invitefriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_invite = new Intent(GroupContentActivity.this,InviteActivity.class);
                startActivity(go_invite);
                overridePendingTransition(0,0);
                finish();
            }
        });

        Chat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_chat = new Intent(GroupContentActivity.this,ChattingActivity.class);
                go_chat.putExtra("group_name",group_name);
                go_chat.putExtra("master_key",master_key);
                startActivity(go_chat);
                overridePendingTransition(0,0);
            }
        });

        member_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_member_list = new Intent(GroupContentActivity.this,Member_listActivity.class);
                go_member_list.putExtra("array",mArraylist_content);
                go_member_list.putExtra("master_key",master_key);
                startActivity(go_member_list);
                overridePendingTransition(0,0);
            }
        });
        moim_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(GroupContentActivity.this);
                builder.setTitle(group_name);
                builder.setMessage("정말로 이 모임을 나가시겠어요?");
                builder.setPositiveButton("예",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {


                                Response.Listener<String> responseListener = new Response.Listener<String>(){

                                    @Override
                                    public void onResponse(String response){
                                        try{


                                            JSONObject jsonResponse = new JSONObject(response);
                                            boolean success =jsonResponse.getBoolean("success");
                                            if(success){

                                               Intent go_main =new Intent(GroupContentActivity.this,MygroupActivity.class);
                                               startActivity(go_main);
                                               finish();
                                            }
                                            else{


                                            }
                                        }
                                        catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                //volley 라이브러리 이용해서 실제 서버와 통신
                                Moim_exitRequest moim_exitRequest = new Moim_exitRequest(save_my_id,master_key,responseListener);
                                RequestQueue queue = Volley.newRequestQueue(GroupContentActivity.this);
                                queue.add(moim_exitRequest);



                                Toast.makeText(getApplicationContext(),"모임 탈퇴를 완료하였습니다.",Toast.LENGTH_LONG).show();
                            }
                        });
                builder.setNegativeButton("아니오",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();



            }
        });








        Intent intent = getIntent();
        group_name=intent.getStringExtra("group_name");
        master_key=intent.getStringExtra("group_master_key");
        Log.e("groupname",group_name);
        Log.e("groupmaster",master_key);

        toolbar.setTitle(group_name);

        member_number=findViewById(R.id.member_number);

        mArraylist_content=new ArrayList<>();

        GetData task = new GetData();
        task.execute("http://192.168.43.34/group_content/groupContentRequest.php",master_key);



    }



    private class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(GroupContentActivity.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();

            if(result==null){

            }
            else{
                mJsonString=result;
                showResult();

            }

            String msg = String.valueOf(mArraylist_content.size());
            member_number.setText(msg+"명");

        }

        @Override
        protected String doInBackground(String... params) {

            String serverURL = params[0];
            String postParameters = "master_key=" + params[1];


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }

                bufferedReader.close();

                return sb.toString().trim();


            } catch (Exception e) {

                errorString = e.toString();

                return null;
            }

        }

    }
    private void showResult(){

        String TAG_JSON="youngseok";
        String TAG_id = "id";
        String TAG_name = "name";
        String TAG_joiner ="joiner";


        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);

                String id = item.getString(TAG_id);
                String name = item.getString(TAG_name);
                String joiner = item.getString(TAG_joiner);

                GroupContentDTO groupcontentdto = new GroupContentDTO();

                groupcontentdto.setGroup_id(id);
                groupcontentdto.setGroup_name(name);
                groupcontentdto.setGroup_joiner(joiner);

                mArraylist_content.add(groupcontentdto);
            }
            Log.e("20180408",String.valueOf(mArraylist_content.size()));

            for (int index=0;index<mArraylist_content.size();index++){
                Log.e("20180408",mArraylist_content.get(index).getGroup_id());
                Log.e("20180408",mArraylist_content.get(index).getGroup_name());
                Log.e("20180408",mArraylist_content.get(index).getGroup_joiner());
            }





        } catch (JSONException e) {

        }


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
