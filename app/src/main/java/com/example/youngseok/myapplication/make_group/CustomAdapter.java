package com.example.youngseok.myapplication.make_group;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.youngseok.myapplication.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<basicGroup> mbasic;

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        protected TextView group_name;
        protected TextView group_content;
        protected TextView group_sumnail;

        public CustomViewHolder(View view){
            super(view);

            this.group_name=view.findViewById(R.id.textview_group_name);
            this.group_content=view.findViewById(R.id.textview_group_content);
            this.group_sumnail=view.findViewById(R.id.textview_group_sumnail);
        }
    }
    public CustomAdapter(ArrayList<basicGroup> basic){
        this.mbasic=basic;

    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.basic_list,viewGroup,false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewHolder, int position){

        viewHolder.group_name.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
        viewHolder.group_content.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
        viewHolder.group_sumnail.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);

        viewHolder.group_name.setGravity(Gravity.CENTER);
        viewHolder.group_content.setGravity(Gravity.CENTER);
        viewHolder.group_sumnail.setGravity(Gravity.CENTER);

        viewHolder.group_name.setText(mbasic.get(position).getGroup_name());
        viewHolder.group_content.setText(mbasic.get(position).getGroup_content());
        viewHolder.group_sumnail.setText(mbasic.get(position).getGroup_sumnail());


    }

    @Override
    public int getItemCount(){
        return (null !=mbasic ? mbasic.size() :0);
    }
}
