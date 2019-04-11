package com.example.youngseok.myapplication.GroupContent.member_list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.youngseok.myapplication.R;

import java.util.ArrayList;

public class Member_listAdapter extends RecyclerView.Adapter<Member_listAdapter.Member_listHolder> {

    private ArrayList<Member_listDTO> mMember_list;
    private Context mContext;

    public class Member_listHolder extends RecyclerView.ViewHolder{


        protected TextView name;
        protected TextView nickname;
        protected TextView id;
        protected TextView phone;
        protected TextView status;

        public final View mView;
        public Member_listHolder(View view){
            super(view);

            mView=view;

//            this.Phonebook_name=view.findViewById(R.id.invite_name);
//            this.Phonebook_phone=view.findViewById(R.id.invite_number);
//            this.invite_btn=view.findViewById(R.id.invite_btn);
            this.name=view.findViewById(R.id.member_name);
            this.nickname=view.findViewById(R.id.member_nick);
            this.id=view.findViewById(R.id.member_id);
            this.phone=view.findViewById(R.id.member_phone);
            this.status=view.findViewById(R.id.member_status);
        }
    }
    public Member_listAdapter(Context context,ArrayList<Member_listDTO> mGroup){
        this.mContext=context;
        this.mMember_list=mGroup;
    }

    @Override
    public Member_listHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.member_list
                ,viewGroup,false);

        Member_listHolder viewHolder = new Member_listHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Member_listHolder viewHolder, final int position){



        viewHolder.name.setText(mMember_list.get(position).getName());
        viewHolder.nickname.setText(mMember_list.get(position).getNickname());
        viewHolder.id.setText(mMember_list.get(position).getId());
        viewHolder.phone.setText(mMember_list.get(position).getPhone());
        viewHolder.status.setText(mMember_list.get(position).getStatus());



//
//
//
//        viewHolder.invite_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//
//                Log.e("adjlskfjwe",minvite.get(position).getPhonebook_name());
//                if(minvite.get(position).getCount()==1){
//                    Log.e("adjilsk","111");
//
//
//                }
//                else{
//                    View view = LayoutInflater.from(mContext).inflate(R.layout.invite_dialog,null,false);
//                    builder.setView(view);
//                    final AlertDialog alertdialog = builder.create();
//
//                    Button sms_btn = view.findViewById(R.id.sms_btn);
//                    Button kakao_btn = view.findViewById(R.id.kakao_btn);
//                    sms_btn.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Log.e("20180408","smsbtnclick");
//
//                            String sms_text ="모임모임 어플로 초대합니다~ 우리 같이 모임 만들어요! \n"+"https://play.google.com/store/search?q=%EB%AA%A8%EC%9E%84%EB%AA%A8%EC%9E%84&c=apps";
//                            Intent intent = new Intent(Intent.ACTION_VIEW);
//                            intent.putExtra("sms_body",sms_text);
//                            intent.putExtra("address",minvite.get(position).getPhonebook_phone());
//                            intent.setType("vnd.android-dir/mms-sms");
//                            v.getContext().startActivity(intent);
//
//                            alertdialog.dismiss();
//                        }
//                    });
//
//
//
//
//                    alertdialog.show();
//                }
//
//
//            }
//        });








    }



    @Override
    public int getItemCount(){
        return (null !=mMember_list ? mMember_list.size() :0);
    }
}


