package com.example.youngseok.myapplication.GroupContent.Schedule;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.youngseok.myapplication.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleHolder> {

    private ArrayList<ScheduleDTO> mSchedule;
    private Context mContext;

    public class ScheduleHolder extends RecyclerView.ViewHolder{
        protected TextView year_month_day;
        protected TextView time_hour_minute;
        protected TextView content;
        protected TextView content_detail;



        public final View mView;
        public ScheduleHolder(View view){
            super(view);

            mView=view;
            this.year_month_day=view.findViewById(R.id.year_month_day);
            this.time_hour_minute=view.findViewById(R.id.time_hour_minute);
            this.content=view.findViewById(R.id.content);
            this.content_detail=view.findViewById(R.id.content_detail);

        }
    }
    public ScheduleAdapter(Context context,ArrayList<ScheduleDTO> mSchedule){
        this.mContext=context;
        this.mSchedule=mSchedule;
    }

    @Override
    public ScheduleHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.schedule_list
                ,viewGroup,false);

        ScheduleHolder viewHolder = new ScheduleHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ScheduleHolder viewHolder, final int position){




        ScheduleDTO data = mSchedule.get(position);
        viewHolder.year_month_day.setText(data.getYear()+"."+data.getMonth()+"."+data.getDay());
        viewHolder.time_hour_minute.setText(data.getTime_hour()+"시 "+data.getTime_minute()+"분");
        viewHolder.content.setText(data.getSchedule_content());
        viewHolder.content_detail.setText(data.getSchedule_content_detail());

















    }



    @Override
    public int getItemCount(){
        return (null !=mSchedule ? mSchedule.size() :0);
    }
}

