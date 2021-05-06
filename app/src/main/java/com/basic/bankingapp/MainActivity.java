package com.basic.bankingapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.basic.bankingapp.activity.BottomNavigationActivity;

public class MainActivity extends AppCompatActivity {
    private ImageView image;
    private TextView textView;
    private CharSequence charSequence;
    private int index;
    private long delay = 200;
    private Handler handler = new Handler(Looper.myLooper());

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity);

        getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.color_background));//set status bar background
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        image = findViewById(R.id.bank_image);
        textView = findViewById(R.id.text);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.animation);
        image.setAnimation(animation);

        animationText("Basic Banking App ");

        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, BottomNavigationActivity.class));
                finish();
            }
        },4000);

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            textView.setText(charSequence.subSequence(0,index++));
            if (index <= charSequence.length()){
                handler.postDelayed(runnable,delay);
            }
        }
    };

    public void animationText(CharSequence cs){
        charSequence = cs;
        index = 0;
        textView.setText("");
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable,delay);
    }
}