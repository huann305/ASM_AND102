package com.example.asm_ph41609.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asm_ph41609.DAO.ProductDAO;
import com.example.asm_ph41609.R;
import com.example.asm_ph41609.adapter.ProductAdapter;
import com.example.asm_ph41609.model.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProductManagementFragment extends Fragment {
    ProductDAO productDAO;
    RecyclerView recyclerView;
    List<Product> list;
    ProductAdapter adapter;
    FloatingActionButton btnAdd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);



        recyclerView = view.findViewById(R.id.rv_main);
        btnAdd = view.findViewById(R.id.btn_add);

        productDAO = new ProductDAO(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        GetData();


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_add_product);

                EditText etName = dialog.findViewById(R.id.et_name_add);
                EditText etCost = dialog.findViewById(R.id.et_cost_add);
                EditText etQuantity = dialog.findViewById(R.id.et_quantity_add);
                Button btnAddAdd = dialog.findViewById(R.id.btn_add_add);

                btnAddAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = etName.getText().toString();
                        String cost = etCost.getText().toString();
                        String quantity = etQuantity.getText().toString();

                        productDAO.AddProduct(new Product(0, name, Integer.parseInt(cost), Integer.parseInt(quantity)));
                        adapter.notifyDataSetChanged();
                        GetData();
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.show();
            }
        });
        return view;
    }

    public void GetData(){
        adapter = new ProductAdapter(getContext());
        recyclerView.setAdapter(adapter);
    }
}