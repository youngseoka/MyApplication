package com.example.youngseok.myapplication.GroupContent.Financial;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
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

import com.example.youngseok.myapplication.R;
import com.example.youngseok.myapplication.make_group.CustomAdapter;
import com.example.youngseok.myapplication.make_group.basicGroup;

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

    private ArrayList<financialDTO> mArrayList;
    private FinancialAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private int count = 0;

    private String mJsonString;

    private String master_key;
    private String master_id;

    private TextView result_tv;
    private Button not_match_btn;


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
        mArrayList=new ArrayList<>();

        result_tv = findViewById(R.id.result_tv);


        mAdapter = new FinancialAdapter(FinancialActivity.this,mArrayList);
        mRecyclerView = findViewById(R.id.financial_recycler);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.notifyDataSetChanged();

        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(),1));

        GetData task = new GetData();
        task.execute("http://192.168.43.34/group_content/financial/financial_show.php",master_key);

        not_match_btn=findViewById(R.id.not_match_btn);


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



    private class GetData extends AsyncTask<String, Void, String> {

        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);



            if(result==null){

            }
            else{
                mJsonString=result;
                showResult();
            }
            Log.e("TTTT",String.valueOf(mArrayList.size()));

            String account_result;
            int account_result_int=0;

            for(int index=0; index<mArrayList.size();index++){
                if(mArrayList.get(index).getMoney_type().equals("입금")){
                    account_result_int+=Integer.valueOf(mArrayList.get(index).getMoney());
                }
            }

            for(int index=0;index<mArrayList.size();index++){
                if(mArrayList.get(index).getMoney_type().equals("출금")){
                    account_result_int-=Integer.valueOf(mArrayList.get(index).getMoney());
                }
           }

            account_result = String.valueOf(account_result_int);

            result_tv.setText(account_result+" 원");


            Log.e("ddkkddkk",mArrayList.get(mArrayList.size()-1).getResult());
            if(account_result.equals(mArrayList.get(mArrayList.size()-1).getResult())){
                not_match_btn.setVisibility(View.GONE);
            }
            else {

                not_match_btn.setVisibility(View.VISIBLE);
                not_match_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(FinancialActivity.this);
                        builder.setTitle("잔액이 일치하지 않습니다.");
                        builder.setMessage("통장 잔액과 어플 잔액이 일치하지 않습니다."+"\n"+"마지막으로 확인된 통장 잔액은 "+mArrayList.get(mArrayList.size()-1).getResult()+" 원 입니다.");
                        builder.setPositiveButton("확인",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });

                        builder.show();
                    }
                });
            }







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
        String TAG_master_key = "master_key";
        String TAG_account = "account";
        String TAG_money_type ="money_type";
        String TAG_money="money";
        String TAG_money_explain="money_explain";
        String TAG_account_time="account_time";
        String TAG_bank_or_hand="bank_or_hand";
        String TAG_content_edit="content_edit";
        String TAG_result="result";


        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);



                String master_key = item.getString(TAG_master_key);
                String account = item.getString(TAG_account);
                String money_type = item.getString(TAG_money_type);
                String money = item.getString(TAG_money);
                String money_explain=item.getString(TAG_money_explain);
                String account_time=item.getString(TAG_account_time);
                String bank_or_hand=item.getString(TAG_bank_or_hand);
                String content_edit=item.getString(TAG_content_edit);
                String result=item.getString(TAG_result);

                financialDTO financialdto =new financialDTO();

                financialdto.setMaster_key(master_key);
                financialdto.setAccount(account);
                financialdto.setMoney_type(money_type);
                financialdto.setMoney(money);
                financialdto.setMoney_explain(money_explain);
                financialdto.setAccount_time(account_time);
                financialdto.setBank_or_hand(bank_or_hand);
                financialdto.setContent_edit(content_edit);
                financialdto.setResult(result);



                mArrayList.add(financialdto);
                mAdapter.notifyDataSetChanged();
            }
            Log.e("TTTT",String.valueOf(mArrayList.size()));



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
        Log.e("DDDD","dd");
    }
    @Override
    protected void onStop(){
        super.onStop();
    }
}
