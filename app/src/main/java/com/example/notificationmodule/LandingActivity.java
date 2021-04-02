package com.example.notificationmodule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        Intent intent = getIntent();
        int text = intent.getIntExtra("info", 0);

        TextView textView = findViewById(R.id.textView);
        textView.setText(Integer.toString(text));

    }
}