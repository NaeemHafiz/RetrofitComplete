package com.Abdul.retrofitdemo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Abdul.retrofitdemo.network.IApi;
import com.Abdul.retrofitdemo.models.LoginUser;
import com.Abdul.retrofitdemo.R;
import com.Abdul.retrofitdemo.network.RetrofitClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin;
    IApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
    }

    private void userLogin() {

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        api = RetrofitClient.getRetrofitInstance().create(IApi.class);
        Call<LoginUser> call = api.loginUser(email, password);
        call.enqueue(new Callback<LoginUser>() {
            @Override
            public void onResponse(@NotNull Call<LoginUser> call, @NotNull Response<LoginUser> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, getErrorMessage(response), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginUser> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getErrorMessage(Response error) {
        String message = "";
        try {
            assert error.errorBody() != null;
            String body = error.errorBody().string();
            JsonObject obj = new JsonParser().parse(body).getAsJsonObject();
            message = obj.get("message").getAsString();
        } catch (IOException e) {
            e.printStackTrace();
            message = "Catch Error Try not work";
        }
        return message;
    }
}

