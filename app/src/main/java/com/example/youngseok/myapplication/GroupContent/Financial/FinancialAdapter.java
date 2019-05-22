package com.example.youngseok.myapplication.GroupContent.Financial;


import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.youngseok.myapplication.R;

import java.util.ArrayList;

public class FinancialAdapter extends RecyclerView.Adapter<FinancialAdapter.FinancialHolder> {

    private ArrayList<financialDTO> mRecycler;
    private Context mContext;

    public class FinancialHolder extends RecyclerView.ViewHolder{


        protected ImageView financial_pl_mi;
        protected ImageView financial_mark;
        protected TextView money_tv;
        protected TextView money_explain_tv;
        protected TextView account_time_tv;

        public final View mView;
        public FinancialHolder(View view){
            super(view);

            mView=view;

            this.financial_mark=view.findViewById(R.id.financial_mark);
            this.financial_pl_mi=view.findViewById(R.id.financial_pl_mi);
            this.money_tv=view.findViewById(R.id.money_tv);
            this.money_explain_tv=view.findViewById(R.id.money_explain_tv);
            this.account_time_tv=view.findViewById(R.id.account_time_tv);
        }
    }
    public FinancialAdapter(Context context,ArrayList<financialDTO> mRecycler){
        this.mContext=context;
        this.mRecycler=mRecycler;
    }

    @Override
    public FinancialHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.financial_list
                ,viewGroup,false);

        FinancialHolder viewHolder = new FinancialHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final FinancialHolder viewHolder, final int position){



        financialDTO data=mRecycler.get(position);

        if(data.getMoney_type().equals("입금")){
            viewHolder.financial_pl_mi.setImageResource(R.drawable.plus);
        }
        else if (data.getMoney_type().equals("출금")){
            viewHolder.financial_pl_mi.setImageResource(R.drawable.minus);
        }else{}

        viewHolder.money_tv.setText(data.getMoney()+" 원");

        viewHolder.money_explain_tv.setText(data.getMoney_explain());

        viewHolder.account_time_tv.setText(data.getAccount_time());

        if(data.getBank_or_hand().equals("기업은행")){
            viewHolder.financial_mark.setImageResource(R.drawable.ibkone);
        }else{}










    }



    @Override
    public int getItemCount(){
        return (null !=mRecycler ? mRecycler.size() :0);
    }
}

