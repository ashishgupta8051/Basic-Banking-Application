package com.basic.bankingapp.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basic.bankingapp.R;

public class UserDetails extends AppCompatActivity {
    private TextView nameTxt,phoneTxt,balanceTxt,emailTxt,accountTxt,ifscTxt,addressTxt,dobTxt;
    private Button transaction;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        getSupportActionBar().setTitle("User Details");

        nameTxt = findViewById(R.id.name);
        phoneTxt = findViewById(R.id.phone);
        balanceTxt = findViewById(R.id.balance);
        emailTxt = findViewById(R.id.email);
        accountTxt = findViewById(R.id.accountNo);
        ifscTxt = findViewById(R.id.ifsc);
        addressTxt = findViewById(R.id.address);
        dobTxt = findViewById(R.id.dob);

        transaction = findViewById(R.id.transactions);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null){
            nameTxt.setText(extras.getString("NAME"));
            phoneTxt.setText(extras.getString("PHONE"));
            balanceTxt.setText(extras.getString("BALANCE"));
            emailTxt.setText(extras.getString("EMAIL"));
            accountTxt.setText(extras.getString("ACCOUNT"));
            ifscTxt.setText(extras.getString("IFSC"));
            addressTxt.setText(extras.getString("ADDRESS"));
            dobTxt.setText(extras.getString("DOB"));
        }else {
            Log.d("Details","Empty details !!!");
        }

        transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = extras.getString("BALANCE");
                enterAmount(amount);
            }
        });
    }

    private void enterAmount(String amount) {
        AlertDialog.Builder builder = new AlertDialog.Builder(UserDetails.this);
        View view = getLayoutInflater().inflate(R.layout.amount_box,null);
        builder.setView(view).setCancelable(false);

        EditText transferAmountEdt;
        Button Cancel,Transfer;
        TextView viewBalanceTxt;

        transferAmountEdt = view.findViewById(R.id.add_amount);
        Cancel = view.findViewById(R.id.cancel);
        Transfer = view.findViewById(R.id.transfer);
        viewBalanceTxt = view.findViewById(R.id.view_balance);

        viewBalanceTxt.setText(amount);

        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
        alertDialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Toast;
        alertDialog.show();

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                AlertDialog.Builder builder_exitbutton = new AlertDialog.Builder(UserDetails.this);
                builder_exitbutton.setTitle("Do you want to cancel the transaction?").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                                Toast.makeText(UserDetails.this, "Transaction Cancelled!", Toast.LENGTH_LONG).show();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        enterAmount(amount);
                    }
                });
                AlertDialog alertexit = builder_exitbutton.create();
                alertexit.show();
            }
        });

        Transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentBalance = Integer.parseInt(amount);

                if (TextUtils.isEmpty(transferAmountEdt.getText().toString())){
                    transferAmountEdt.requestFocus();
                    transferAmountEdt.setError("Amount can't be empty");
                }else if (transferAmountEdt.getText().toString().contentEquals("0")){
                    transferAmountEdt.requestFocus();
                    transferAmountEdt.setError("Invalid Amount");
                }else if (Integer.parseInt(transferAmountEdt.getText().toString()) > currentBalance){
                    transferAmountEdt.requestFocus();
                    transferAmountEdt.setError("Your account don't have enough balance");
                }else {
                    Intent intent = new Intent(UserDetails.this,SendAmountToUser.class);
                    intent.putExtra("FROM_USER_ACCOUNT_NO", accountTxt.getText().toString());
                    intent.putExtra("FROM_USER_NAME", nameTxt.getText().toString());
                    intent.putExtra("FROM_USER_ACCOUNT_BALANCE", balanceTxt.getText().toString());
                    intent.putExtra("TRANSFER_AMOUNT", transferAmountEdt.getText().toString());
                    startActivity(intent);
                    finish();
                    alertDialog.dismiss();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), BottomNavigationActivity.class));
        finish();
    }
}