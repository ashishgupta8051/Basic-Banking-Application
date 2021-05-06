package com.basic.bankingapp.fragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basic.bankingapp.R;
import com.basic.bankingapp.adapter.AllUsersAdapter;
import com.basic.bankingapp.database.UserDatabase;
import com.basic.bankingapp.model.Users;
import com.basic.bankingapp.params.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class AllUsers extends Fragment {
    private UserDatabase userDatabase;
    private List<Users> usersList = new ArrayList<>();
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private AllUsersAdapter allUsersAdapter;
    private String name,email,ifsc_Code,address,dob,balance,phone,accountnumber;

    public AllUsers() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_all_users, container, false);

        getActivity().setTitle("All Users");

        userDatabase = new UserDatabase(getActivity());

        recyclerView = view.findViewById(R.id.usersRecycler);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        allUsersAdapter = new AllUsersAdapter(getActivity(),usersList);
        recyclerView.setAdapter(allUsersAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getAllUsers();
    }

    private void getAllUsers() {
        usersList.clear();
        List<Users> usersList1 = userDatabase.getAllUsers();
        for (Users users : usersList1){
            name = users.getName();
            phone = users.getPhone();
            balance = String.valueOf(users.getBalance());
            email = users.getEmail();
            accountnumber = String.valueOf(users.getAccountNumber());
            ifsc_Code = users.getIfsc_Code();
            address = users.getAddress();
            dob = users.getDob();

            Users users1 = new Users(name,phone,Integer.parseInt(balance),email,Integer.parseInt(accountnumber),ifsc_Code,address,dob);
            usersList.add(users1);
        }
    }

}