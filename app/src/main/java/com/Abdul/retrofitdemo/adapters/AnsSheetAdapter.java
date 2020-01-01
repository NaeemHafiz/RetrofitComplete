package com.Abdul.retrofitdemo.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Abdul.retrofitdemo.R;
import com.Abdul.retrofitdemo.models.Employee;

import java.util.ArrayList;

public class AnsSheetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Employee> employees;
    private Callback callback;

    public AnsSheetAdapter(Context context, ArrayList<Employee> ansSheets, Callback callback) {
        this.context = context;
        this.employees = ansSheets;
        this.callback = callback;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.row_answer_sheet, parent, false);
        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((BookViewHolder) holder).bind(position);
    }

    @Override
    public int getItemCount() {
        Log.d("ADAPTER", "size is " + employees.size());
        return employees.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {

        TextView tvFnmae, tvLname;
        LinearLayout llITem;

        private BookViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFnmae = itemView.findViewById(R.id.fname);
            tvLname = itemView.findViewById(R.id.lname);
            llITem = itemView.findViewById(R.id.llitem);
        }

        private void bind(int pos) {
            Employee employee = employees.get(pos);
            tvFnmae.setText(employee.getFirst_name());
            tvLname.setText(employee.getLast_name());
            initClickListener();
        }
        private void initClickListener() {

            llITem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    public interface Callback {
        void onItemClick(int pos);
    }
}
