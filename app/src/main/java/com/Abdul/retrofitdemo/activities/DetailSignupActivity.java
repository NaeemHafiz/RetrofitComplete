package com.Abdul.retrofitdemo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.Abdul.retrofitdemo.R;
import com.Abdul.retrofitdemo.models.Employee;

public class DetailSignupActivity extends AppCompatActivity {
    TextView tvName;
    TextView tvEmail;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_signup);
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        Employee dene = (Employee) getIntent().getSerializableExtra("data");
        assert dene != null;
        tvName.setText(dene.getFirst_name() + " " + dene.getLast_name());
        tvEmail.setText(dene.getEmail());
    }
}
