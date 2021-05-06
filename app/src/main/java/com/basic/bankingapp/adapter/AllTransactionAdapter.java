package com.basic.bankingapp.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.basic.bankingapp.R;
import com.basic.bankingapp.model.Transaction;

import java.util.List;

public class AllTransactionAdapter extends RecyclerView.Adapter<AllTransactionAdapter.TransactionViewHolder> {

    private List<Transaction> transactionList;
    private TextView emptyTxt;
    private Activity activity;

    public AllTransactionAdapter(List<Transaction> transactionList, TextView emptyTxt, Activity activity) {
        this.transactionList = transactionList;
        this.emptyTxt = emptyTxt;
        this.activity = activity;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_transaction_list,parent,false);
        TransactionViewHolder transactionViewHolder = new TransactionViewHolder(view);
        return transactionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Transaction transaction = transactionList.get(position);

        if (transactionList.get(position).getStatus().equals("Cancelled")){
            holder.fromName.setText(transaction.getFromName());
            holder.toName.setText(transaction.getToName());
            holder.amount.setText("Rs. "+transaction.getAmount()+" /-");
            holder.status.setTextColor(Color.parseColor("#f40404"));
            holder.status.setText(transaction.getStatus());
            holder.time.setText(transaction.getTransactionDate());
        }else {
            holder.fromName.setText(transaction.getFromName());
            holder.toName.setText(transaction.getToName());
            holder.amount.setText("Rs. "+transaction.getAmount()+" /-");
            holder.status.setTextColor(Color.parseColor("#4BB543"));
            holder.status.setText(transaction.getStatus());
            holder.time.setText(transaction.getTransactionDate());
        }

    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    class TransactionViewHolder extends RecyclerView.ViewHolder{
        TextView fromName,toName,amount,status,time;
        CardView cardView;
        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);

            fromName = itemView.findViewById(R.id.fromName);
            toName = itemView.findViewById(R.id.toName);
            amount = itemView.findViewById(R.id.transferAmount);
            status = itemView.findViewById(R.id.transferStatus);
            time = itemView.findViewById(R.id.timing);

            cardView = itemView.findViewById(R.id.cardView);
        }
    }

}
