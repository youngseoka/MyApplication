package com.example.youngseok.myapplication.make_group;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.youngseok.myapplication.R;
import com.example.youngseok.myapplication.recycler_drag_drop.ItemTouchHelperListener;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>


implements ItemTouchHelperListener{

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if(fromPosition < 0 || fromPosition >= mbasic.size() || toPosition < 0 || toPosition >= mbasic.size()){
            Log.e("Tlqkf","TlqkfTlqkfTlqkf1111");
            return false;
        }

        Log.e("Tlqkf","TlqkfTlqkfTlqkf222");
        basicGroup fromItem = mbasic.get(fromPosition);

        Log.e("Tlqkf","TlqkfTlqkfTlqkf3333");
        mbasic.remove(fromPosition);
        Log.e("Tlqkf","TlqkfTlqkfTlqkf44");
        mbasic.add(toPosition, fromItem);
        Log.e("Tlqkf","TlqkfTlqkfTlqkf5555");

        notifyItemMoved(fromPosition, toPosition);

        //여기다가 데이터베이스 순서 바꾸는걸 넣자.


        return true;
    }

    @Override
    public void onItemRemove(int position) {
        mbasic.remove(position);
        notifyItemRemoved(position);
    }



    private ArrayList<basicGroup> mbasic;
    private Context mContext;

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        protected TextView group_name;
        protected TextView group_content;
        protected TextView group_sumnail;
        protected ImageView group_picture;

        public CustomViewHolder(View view){
            super(view);

            this.group_name=view.findViewById(R.id.textview_group_name);
            this.group_content=view.findViewById(R.id.textview_group_content);
            this.group_sumnail=view.findViewById(R.id.textview_group_sumnail);
            this.group_picture=view.findViewById(R.id.imageview_group_profile);
        }
    }
    public CustomAdapter(Context context,ArrayList<basicGroup> basic){
        this.mContext=context;
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



        viewHolder.group_name.setText(mbasic.get(position).getGroup_name());
        viewHolder.group_content.setText(mbasic.get(position).getGroup_content());
        viewHolder.group_sumnail.setText(mbasic.get(position).getGroup_sumnail());
        Glide.with(mContext).asBitmap().load(mbasic.get(position).getGroup_picture()).override(300,300).into(viewHolder.group_picture);


    }

    @Override
    public int getItemCount(){
        return (null !=mbasic ? mbasic.size() :0);
    }
}
