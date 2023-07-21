package com.example.asm_ph41609;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
    public static String USER_FILE = "USER_FILE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.et_username_signup);
        etPassword = findViewById(R.id.et_password_signup);
        btnLogin = findViewById(R.id.btn_signup);
        tvForgotPassword = findViewById(R.id.tv_forgot_pass);
        tvSignUpLogin = findViewById(R.id.tv_signup);

        userDAO = new UserDAO(this);
        list = userDAO.GetAllListUser();

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, FogotPass.class);
                startActivity(intent);
            }
        });

        tvSignUpLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                boolean check = false;

                for (int i = 0; i < list.size(); i++) {
                    if (username.equals(list.get(i).getUsername())) {
                        if (!password.equals(list.get(i).getPassword())) {
                            Toast.makeText(Login.this, "Mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        check = true;
                        Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        rememberUser(username, password);
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

                if(!check){
                    Toast.makeText(Login.this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void rememberUser(String u, String p ) {
        SharedPreferences pref = getSharedPreferences(USER_FILE, MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("USERNAME", u);
        edit.putString("PASSWORD", p);
        edit.commit();
    }
}