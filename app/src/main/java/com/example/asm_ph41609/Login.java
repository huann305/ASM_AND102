package com.example.asm_ph41609;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_ph41609.DAO.UserDAO;
import com.example.asm_ph41609.model.User;

import java.util.List;

public class Login extends AppCompatActivity {

    UserDAO userDAO;
    List<User> list;
    EditText etUsername;
    EditText etPassword;
    Button btnLogin;
    TextView tvForgotPassword;
    TextView tvSignUpLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.et_username_login);
        etPassword = findViewById(R.id.et_password_login);
        btnLogin = findViewById(R.id.btn_login);
        tvForgotPassword = findViewById(R.id.tv_forgot_pass);
        tvSignUpLogin = findViewById(R.id.tv_signup);

        userDAO = new UserDAO(this);
        list = userDAO.GetAllListUser();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                for (int i = 0; i < list.size(); i++){
                    if(!username.equals(list.get(i).getUsername())){
                        Toast.makeText(Login.this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(!password.equals(list.get(i).getPassword())){
                        Toast.makeText(Login.this, "Mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}