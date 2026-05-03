// SplashActivity.java
package com.example.tourguideapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;   // ← ADD THIS IMPORT
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // ✅ Use Looper.getMainLooper() — fixes the deprecation error
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}