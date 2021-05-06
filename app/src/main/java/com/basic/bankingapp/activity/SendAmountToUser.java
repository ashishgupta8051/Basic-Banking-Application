package com.basic.bankingapp.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.basic.bankingapp.R;
import com.basic.bankingapp.adapter.SendAmountToUserAdapter;
import com.basic.bankingapp.database.TransactionDatabase;
import com.basic.bankingapp.database.UserDatabase;
import com.basic.bankingapp.fragment.AllUsers;
import com.basic.bankingapp.model.Users;
import com.basic.bankingapp.params.DatabaseDetails;
import com.basic.bankingapp.params.TransactionEntity;
import com.basic.bankingapp.params.UserEntity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SendAmountToUser extends AppCompatActivity implements SendAmountToUserAdapter.OnUserListener {
    private List<Users> usersList = new ArrayList<>();;
    private String fromUserName,fromUserAcNo,fromUserAcBalance,transferAmount,toUserName;
    private int toUserAcNo,toUserBalance;
    private String name,email,ifsc_Code,address,dob,balance,phone,accountnumber;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private SendAmountToUserAdapter sendAmountToUserAdapter;
    private String CurrentDate,CurrentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_amount_to_user);

        getSupportActionBar().setTitle("Transfer Money");

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null){
            fromUserName = extras.getString("FROM_USER_NAME");
            fromUserAcNo = extras.getString("FROM_USER_ACCOUNT_NO");
            fromUserAcBalance = extras.getString("FROM_USER_ACCOUNT_BALANCE");
            transferAmount = extras.getString("TRANSFER_AMOUNT");
        }else {
            Log.d("Error","Empty data !!!");
        }

        recyclerView = findViewById(R.id.selectUserRecycler);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(SendAmountToUser.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        sendAmountToUserAdapter = new SendAmountToUserAdapter(usersList,fromUserAcNo,this);
        recyclerView.setAdapter(sendAmountToUserAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getAllUsers();
    }

    private void getAllUsers() {
        usersList.clear();
        List<Users> usersList1 = new UserDatabase(this).getAllUsers();
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

    @Override
    public void onBackPressed() {
        //Getting Time
        Calendar Date = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMM-yyyy");
        CurrentDate = currentDate.format(Date.getTime());

        Calendar Time = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm aa");
        CurrentTime = currentTime.format(Time.getTime());

        String time = CurrentDate+","+CurrentTime;

        AlertDialog.Builder builder = new AlertDialog.Builder(SendAmountToUser.this);
        builder.setTitle("Do you want to cancel the transaction?").setCancelable(false)
                .setPositiveButton ("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick (DialogInterface dialogInterface, int i) {
                        new TransactionDatabase(SendAmountToUser.this).insertTransferData(fromUserName,"Not Selected",transferAmount,"Cancelled",time);

                        Toast.makeText(SendAmountToUser.this, "Transaction Cancelled!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), BottomNavigationActivity.class));
                        finish();
                    }
                }).setNegativeButton("No", null);
        AlertDialog alertExit = builder.create();
        alertExit.show();
    }

    @Override
    public void onUserClick(int position) {
        //Getting date and time
        Calendar Date = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMM-yyyy");
        CurrentDate = currentDate.format(Date.getTime());

        Calendar Time = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm aa");
        CurrentTime = currentTime.format(Time.getTime());

        String time = CurrentDate+","+CurrentTime;

        //Getting beneficiary account details
        toUserName = usersList.get(position).getName();
        toUserAcNo = usersList.get(position).getAccountNumber();
        toUserBalance = usersList.get(position).getBalance();

        Integer currentBalance = Integer.parseInt(fromUserAcBalance);
        Integer transferBalance = Integer.parseInt(transferAmount);
        Integer fromBalance = currentBalance - transferBalance;
        Integer toBalance = transferBalance + toUserBalance;

        new UserDatabase(this).updateUserAccountBalance(Integer.parseInt(fromUserAcNo),fromBalance,this);
        new UserDatabase(this).updateUserAccountBalance(toUserAcNo,toBalance,this);

        new TransactionDatabase(this).insertTransferData(fromUserName,toUserName,transferAmount,"Successful",time);

        Toast.makeText(this, "Transaction Successful!!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(SendAmountToUser.this, BottomNavigationActivity.class));
        finish();


    }
}