package com.basic.bankingapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.basic.bankingapp.R;
import com.basic.bankingapp.model.Users;

import java.util.List;

public class SendAmountToUserAdapter extends RecyclerView.Adapter<SendAmountToUserAdapter.ViewHolder>{

    private List<Users> usersList;
    private String fromUserAcNo;
    private OnUserListener onUserListener;

    public SendAmountToUserAdapter(List<Users> usersList, String fromUserAcNo, OnUserListener onUserListener) {
        this.usersList = usersList;
        this.fromUserAcNo = fromUserAcNo;
        this.onUserListener = onUserListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alll_users_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view,onUserListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Users users = usersList.get(position);

        if (fromUserAcNo.equals(String.valueOf(users.getAccountNumber()))){
            holder.relativeLayout.setVisibility(View.GONE);
            holder.cardView.setVisibility(View.GONE);
        }else {
            holder.Name.setText(users.getName());
            holder.Money.setText("Rs. "+users.getBalance()+" /-");
        }
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView Name,Money;
        CardView cardView;
        RelativeLayout relativeLayout;
        OnUserListener onUserListener;
        public ViewHolder(@NonNull View itemView, OnUserListener onUserListener) {
            super(itemView);

            Name = itemView.findViewById(R.id.userName);
            Money = itemView.findViewById(R.id.money);
            relativeLayout = itemView.findViewById(R.id.hideRelativeLayout);
            cardView = itemView.findViewById(R.id.hideCardView);

            this.onUserListener = onUserListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onUserListener.onUserClick(getAdapterPosition());
        }
    }

    public interface OnUserListener {
        void onUserClick(int position);
    }


}
