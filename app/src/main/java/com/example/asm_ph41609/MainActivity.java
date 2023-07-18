package com.example.asm_ph41609;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asm_ph41609.DAO.ProductDAO;
import com.example.asm_ph41609.adapter.ProductAdapter;
import com.example.asm_ph41609.model.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ProductDAO productDAO;
    RecyclerView recyclerView;
    List<Product> list;
    Toolbar toolbar;
    ProductAdapter adapter;
    FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_main);
        btnAdd = findViewById(R.id.btn_add);

        productDAO = new ProductDAO(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        GetData();

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Sản Phẩm");
        toolbar.setElevation(0);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
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
                        Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });


                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.show();
            }
        });

        setSupportActionBar(toolbar);
    }

    public void GetData(){
        adapter = new ProductAdapter(this);
        recyclerView.setAdapter(adapter);
    }
}