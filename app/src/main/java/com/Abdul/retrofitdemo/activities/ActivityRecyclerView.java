package com.Abdul.retrofitdemo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.Abdul.retrofitdemo.network.IApi;
import com.Abdul.retrofitdemo.R;
import com.Abdul.retrofitdemo.network.RetrofitClient;
import com.Abdul.retrofitdemo.adapters.AnsSheetAdapter;
import com.Abdul.retrofitdemo.models.Employee;
import com.Abdul.retrofitdemo.models.GetEmpResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class ActivityRecyclerView extends AppCompatActivity implements AnsSheetAdapter.Callback {

    RecyclerView rvList;
    private AnsSheetAdapter asAdapter;
    private ArrayList<Employee> employees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        rvList = findViewById(R.id.rvList);
        initAdapter();
        getAnswers();
    }

    private void initAdapter() {
        employees = new ArrayList<>();
        asAdapter = new AnsSheetAdapter(ActivityRecyclerView.this, employees, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvList.setLayoutManager(linearLayoutManager);
        rvList.setAdapter(asAdapter);
    }

    public void getAnswers() {
        IApi api = RetrofitClient.getRetrofitInstance().create(IApi.class);
        Call<GetEmpResponse> responseCall = api.getEmployees();
        responseCall.enqueue(new Callback<GetEmpResponse>() {
            @Override
            public void onResponse(Call<GetEmpResponse> call, retrofit2.Response<GetEmpResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ActivityRecyclerView.this, "helo", Toast.LENGTH_SHORT).show();
                    employees.addAll(response.body().getData());
                    asAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<GetEmpResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onItemClick(int pos) {
        Employee employee = new Employee();
        Intent intent = new Intent(ActivityRecyclerView.this, DetailSignupActivity.class);
        intent.putExtra("data", employee);
        startActivity(intent);
        Toast.makeText(ActivityRecyclerView.this, "Chapter is locked" + pos, Toast.LENGTH_SHORT).show();

    }
}
