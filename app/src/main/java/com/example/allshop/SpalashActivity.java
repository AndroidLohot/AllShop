package com.example.allshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SpalashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(3*1000);

                    startActivity(new Intent(SpalashActivity.this,RegisterActivity.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });

        t.start();
    }
}
