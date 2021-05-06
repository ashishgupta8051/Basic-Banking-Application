package com.basic.bankingapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.basic.bankingapp.R;
import com.basic.bankingapp.adapter.AllTransactionAdapter;
import com.basic.bankingapp.database.TransactionDatabase;
import com.basic.bankingapp.database.UserDatabase;
import com.basic.bankingapp.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionDetails extends Fragment {
    private List<Transaction> transactionList = new ArrayList<>();
    private RecyclerView recyclerView;
    private String fromName,toName,amount,status,time;
    private LinearLayoutManager linearLayoutManager;
    private TextView textView;
    private AllTransactionAdapter allTransactionAdapter;


    public TransactionDetails() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_transaction_details, container, false);

        getActivity().setTitle("All Transactions");

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView = view.findViewById(R.id.no_transaction);
        recyclerView = view.findViewById(R.id.transactionRecycler);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        allTransactionAdapter = new AllTransactionAdapter(transactionList,textView,getActivity());
        recyclerView.setAdapter(allTransactionAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        getAllTransaction();
    }

    private void getAllTransaction() {
        transactionList.clear();
        List<Transaction> list = new TransactionDatabase(getActivity()).getAllTransactions();
        for (Transaction transaction : list){
            fromName = transaction.getFromName();
            toName = transaction.getToName();
            amount = transaction.getAmount();
            status = transaction.getStatus();
            time = transaction.getTransactionDate();

            transactionList.add(new Transaction(fromName,toName,amount,status,time));
        }
    }
}