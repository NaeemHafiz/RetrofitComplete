package com.Abdul.retrofitdemo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Abdul.retrofitdemo.network.IApi;
import com.Abdul.retrofitdemo.R;
import com.Abdul.retrofitdemo.models.RegisterUserResponse;
import com.Abdul.retrofitdemo.network.RetrofitClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    EditText etName, etEmail, etPassword, etConfirmPassword;
    Button btnSignp;
    IApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnSignp = findViewById(R.id.btnSignup);
        btnSignp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSignup();
            }
        });
    }

    private void userSignup() {

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();
        api = RetrofitClient.getRetrofitInstance().create(IApi.class);
        Call<RegisterUserResponse> call = api.signupUser(name, email, password, confirmPassword);
        call.enqueue(new Callback<RegisterUserResponse>() {
            @Override
            public void onResponse(@NotNull Call<RegisterUserResponse> call, @NotNull Response<RegisterUserResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SignupActivity.this, response.body().getMesssage(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(SignupActivity.this, getErrorMessage(response), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterUserResponse> call, Throwable t) {
                Toast.makeText(SignupActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
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
