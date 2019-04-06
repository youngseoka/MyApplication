package com.example.youngseok.myapplication.invite;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.youngseok.myapplication.R;
import com.example.youngseok.myapplication.invite.phone_Validate.phone_validate;

import org.json.JSONObject;

import java.util.ArrayList;

public class InviteAdapter extends RecyclerView.Adapter<InviteAdapter.InviteHolder> {

    private ArrayList<InviteDTO> minvite;
    private Context mContext;

    public class InviteHolder extends RecyclerView.ViewHolder{

        protected TextView Phonebook_name;
        protected TextView Phonebook_phone;
        protected Button invite_btn;

        public final View mView;
        public InviteHolder(View view){
            super(view);

            mView=view;

            this.Phonebook_name=view.findViewById(R.id.invite_name);
            this.Phonebook_phone=view.findViewById(R.id.invite_number);
            this.invite_btn=view.findViewById(R.id.invite_btn);
        }
    }
    public InviteAdapter(Context context,ArrayList<InviteDTO> minvite){
        this.mContext=context;
        this.minvite=minvite;
    }

    @Override
    public InviteHolder onCreateViewHolder(ViewGroup viewGroup,int viewType){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.invite_list
        ,viewGroup,false);

        InviteHolder viewHolder = new InviteHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final InviteHolder viewHolder, final int position){





        InviteDTO data = minvite.get(position);
        viewHolder.Phonebook_name.setText(data.getPhonebook_name());
        viewHolder.Phonebook_phone.setText(data.getPhonebook_phone());

        Response.Listener<String> responseListener = new Response.Listener<String>(){

            @Override
            public void onResponse(String response){
                try{


                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success =jsonResponse.getBoolean("success");
                    if(success){

                        Log.e("tmdthd","success");
                        viewHolder.invite_btn.setText("초대하기");



                    }
                    else{
                        Log.e("tmdthd","false");
                        viewHolder.invite_btn.setText("모임에 초대하기");




                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        //volley 라이브러리 이용해서 실제 서버와 통신
        phone_validate phone_validate_1 = new phone_validate(data.getPhonebook_phone(),responseListener);
        RequestQueue queue = Volley.newRequestQueue(mContext);
        queue.add(phone_validate_1);






    }

    public void check_id(){

    }

    @Override
    public int getItemCount(){
        return (null !=minvite ? minvite.size() :0);
    }
}
