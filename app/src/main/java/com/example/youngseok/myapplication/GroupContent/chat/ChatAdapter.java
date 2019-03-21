package com.example.youngseok.myapplication.GroupContent.chat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.youngseok.myapplication.R;

import java.util.ArrayList;

import static com.example.youngseok.myapplication.Initial.InitialActivity.save_my_id;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder>{




    private ArrayList<ChatDTO> mchat;

    private Context mContext;

    public class ChatHolder extends RecyclerView.ViewHolder{

        protected TextView userName;
        protected TextView message;

        public final View mView;
        public ChatHolder(View view){
            super(view);

            mView=view;

            this.userName=view.findViewById(R.id.chat_name);
            this.message=view.findViewById(R.id.chat_content);
        }
    }
    public ChatAdapter(Context context,ArrayList<ChatDTO> mchat){
        this.mContext=context;
        this.mchat=mchat;

    }

    @Override
    public ChatHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.chat_list,viewGroup,false);

        ChatHolder viewHolder = new ChatHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder viewHolder, final int position){

            Log.e("check1",mchat.get(position).getUserName());
            Log.e("check2",save_my_id);

        if((mchat.get(position).getUserName()).equals(save_my_id)) {
            Log.e("check","aaaaa");
           // viewHolder.userName.setText(mchat.get(position).getUserName());
            viewHolder.message.setText(mchat.get(position).getMessage());
        }

        else
        {Log.e("check","bbbbbb");
            viewHolder.userName.setText(mchat.get(position).getUserName());
            viewHolder.message.setText(mchat.get(position).getMessage());
        }

    }

    @Override
    public int getItemCount(){
        return (null !=mchat ? mchat.size() :0);
    }
}