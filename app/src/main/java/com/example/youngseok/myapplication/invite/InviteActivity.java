package com.example.youngseok.myapplication.invite;



import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.youngseok.myapplication.MainActivity;
import com.example.youngseok.myapplication.MygroupActivity;
import com.example.youngseok.myapplication.R;

import com.example.youngseok.myapplication.make_group.MakeGroupActivity;
import com.example.youngseok.myapplication.make_group.basicGroup;
import com.example.youngseok.myapplication.setting.SettingActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import static com.example.youngseok.myapplication.Initial.InitialActivity.save_my_id;

public class InviteActivity extends AppCompatActivity {
    Toolbar toolbar;

    ImageButton timeline;
    ImageButton mygroup;
    ImageButton makegroup;
    ImageButton invitefriend;
    ImageButton myset;

    private ArrayList<basicGroup> mArrayList;
    private ArrayList<InviteDTO> datas;
    private ArrayList<InviteDTO> datas_temp;

    private InviteAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private int count = 0;

    private ArrayList<InviteDTO> mArrayList_Invite;

    private String mJsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
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

        String keyword = save_my_id;


        timeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_main = new Intent(InviteActivity.this,MainActivity.class);
                startActivity(go_main);
                overridePendingTransition(0,0);
                finish();
            }
        });

        mygroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_mygroup = new Intent(InviteActivity.this,MygroupActivity.class);
                startActivity(go_mygroup);
                overridePendingTransition(0,0);
                finish();
            }
        });

        makegroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent go_make = new Intent(InviteActivity.this,MakeGroupActivity.class);
                startActivity(go_make);
                overridePendingTransition(0,0);
                finish();
            }
        });
        invitefriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_invite = new Intent(InviteActivity.this,InviteActivity.class);
                startActivity(go_invite);
                overridePendingTransition(0,0);
                finish();
            }
        });
        myset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_set = new Intent(InviteActivity.this,SettingActivity.class);
                startActivity(go_set);
                overridePendingTransition(0,0);
                finish();
            }
        });



        datas = InviteLoader.getData(this);

        datas_temp = new ArrayList<>();




        Comparator<InviteDTO> cpmasc = new Comparator<InviteDTO>() {
            @Override
            public int compare(InviteDTO o1, InviteDTO o2) {
                return o1.getPhonebook_name().compareTo(o2.getPhonebook_name());
            }
        };
        Collections.sort(datas,cpmasc);



        mArrayList_Invite=new ArrayList<>();








        mAdapter = new InviteAdapter(this,datas);

        RecyclerView recyclerview = findViewById(R.id.invite_recycle);
        recyclerview.setAdapter(mAdapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.notifyDataSetChanged();


        GetData task = new GetData();
        task.execute( "http://192.168.43.34/Invite/phone_Validate.php", "");













    }




    private class GetData extends AsyncTask<String, Void, String> {


        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);




            if (result == null){


            }
            else {

                mJsonString = result;
                showResult();
            }
        }
        @Override
        protected String doInBackground(String... params) {

            String serverURL = params[0];
            String postParameters = params[1];


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
        String TAG_PHONE = "phone";


        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);

                String phone = item.getString(TAG_PHONE);

                InviteDTO invitedto = new InviteDTO();

                invitedto.setPhonebook_phone(phone);

                mArrayList_Invite.add(invitedto);


            }




        } catch (JSONException e) {
        }


        Log.e("tmdthd",String.valueOf(datas.size()));

        int count_btn=0;
        for(int index=0;index<datas.size();){


            for(int jndex=0;jndex<mArrayList_Invite.size();){


                if(datas.get(index).getPhonebook_phone().equals(mArrayList_Invite.get(jndex).getPhonebook_phone())){

                    count_btn++;
                    datas_temp.add(0,datas.get(index));

                    datas.add(0,datas.get(index));
                    index++;
                    datas.remove(index);
                    break;

                }
                else{
                    jndex++;


                }
            }
            index++;
        }
        Log.e("tmdthd",String.valueOf(count_btn));
        for(int cndex=0; cndex<count_btn;cndex++){
            datas.get(0).setCount(1);
            Log.e("tmdth",datas.get(cndex).getPhonebook_phone());
            datas.remove(datas.get(0));
        }




        Comparator<InviteDTO> cpmasc = new Comparator<InviteDTO>() {
            @Override
            public int compare(InviteDTO o1, InviteDTO o2) {


                return o1.getPhonebook_name().compareTo(o2.getPhonebook_name());
            }
        };
        Collections.sort(datas_temp,cpmasc);

        datas.addAll(0,datas_temp);


        Log.e("tmdthd","endddddisssco");
        mAdapter.notifyDataSetChanged();


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
