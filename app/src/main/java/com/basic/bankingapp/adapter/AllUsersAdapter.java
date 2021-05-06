package com.basic.bankingapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.basic.bankingapp.R;
import com.basic.bankingapp.activity.UserDetails;
import com.basic.bankingapp.model.Users;

import java.util.List;

public class AllUsersAdapter extends RecyclerView.Adapter<AllUsersAdapter.ViewHolder>{

    private Activity activity;
    private List<Users> usersList;

    public AllUsersAdapter(Activity activity, List<Users> usersList) {
        this.activity = activity;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alll_users_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Users users = usersList.get(position);

        holder.Name.setText(users.getName());
        holder.Money.setText("Rs. "+users.getBalance()+" /-");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, UserDetails.class);
                intent.putExtra("NAME",users.getName());
                intent.putExtra("PHONE",users.getPhone());
                intent.putExtra("BALANCE",String.valueOf(users.getBalance()));
                intent.putExtra("EMAIL",users.getEmail());
                intent.putExtra("ACCOUNT",String.valueOf(users.getAccountNumber()));
                intent.putExtra("IFSC",users.getIfsc_Code());
                intent.putExtra("ADDRESS",users.getAddress());
                intent.putExtra("DOB",users.getDob());
                activity.startActivity(intent);
                activity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView Name,Money;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.userName);
            Money = itemView.findViewById(R.id.money);

        }
    }
}
