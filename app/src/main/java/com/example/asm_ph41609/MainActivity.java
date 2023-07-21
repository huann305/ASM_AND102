package com.example.asm_ph41609;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.asm_ph41609.fragment.AboutFragment;
import com.example.asm_ph41609.fragment.ChangePasswordFragment;
import com.example.asm_ph41609.fragment.ProductManagementFragment;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    Toolbar toolbar;
    NavigationView navigationView;
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.id_drawer);
        setSupportActionBar(toolbar);

        toolbar.setElevation(0);
//
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, 0, 0);
        actionBarDrawerToggle.syncState();
//
        navigationView = findViewById(R.id.id_nav);
        navigationView.setNavigationItemSelectedListener(this);


        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.kkkkk, new ProductManagementFragment()).commit();
//        replaceFragment(new ProductManagementFragment());
        setTitle("Quản lý sản phẩm");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        setTitle(item.getTitle());
        if (item.getItemId() == R.id.product_management) {
            drawerLayout.close();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.kkkkk, new ProductManagementFragment()).commit();
            return true;
        }else if(item.getItemId() == R.id.about){
            drawerLayout.close();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.kkkkk, new AboutFragment()).commit();
            return true;
        }else if(item.getItemId() == R.id.setting){
            Toast.makeText(this, "Đang cập nhật", Toast.LENGTH_SHORT).show();
            return true;
        }else if(item.getItemId() == R.id.change_password){
            drawerLayout.close();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.kkkkk, new ChangePasswordFragment()).commit();
            return true;
        }
        else if(item.getItemId() == R.id.logout){
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            finishAffinity();
            return true;
        }
        else {
            return false;
        }
    }
}