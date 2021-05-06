package com.basic.bankingapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.basic.bankingapp.R;
import com.basic.bankingapp.fragment.AllUsers;
import com.basic.bankingapp.fragment.TransactionDetails;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_activity);

//        getSupportActionBar().setTitle("Banking Application");
//        getSupportActionBar().hide();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        fragmentsTransactions(new AllUsers());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.users:
                fragment = new AllUsers();
                break;
            case R.id.transaction:
                fragment = new TransactionDetails();
                break;
        }
        if (fragment != null){
            fragmentsTransactions(fragment);
        }
        return true;
    }

    void fragmentsTransactions(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.relative_layout,fragment).commit();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}