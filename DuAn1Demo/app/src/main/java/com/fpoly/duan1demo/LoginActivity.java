package com.fpoly.duan1demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    AppCompatButton btn_login;
    private CheckBox rememberMe;
    private TextInputEditText username, password;
    String strUser, strPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = findViewById(R.id.btn_login);
        username = findViewById(R.id.mail_user_login);
        password = findViewById(R.id.pass_user_login);
        rememberMe = findViewById(R.id.rememberME);

        //doc user, pass trong SharedPreferences
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        username.setText(pref.getString("USERNAME", ""));
        password.setText(pref.getString("PASSWORK", ""));
        rememberMe.setChecked(pref.getBoolean("REMEMBER", false));

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });

    }

    public void checkLogin() {
        strUser = username.getText().toString();
        strPass = password.getText().toString();
        if (strUser.isEmpty() || strPass.isEmpty()) {
            Toast.makeText(this, "Tên đăng nhập và mật khẩu không được bỏ trống", Toast.LENGTH_SHORT).show();
        } else {
            if (strUser.equalsIgnoreCase("admin") && strPass.equalsIgnoreCase("admin")) {
                Toast.makeText(this, "Login thành công", Toast.LENGTH_SHORT).show();

                rememberUser(strUser, strPass, rememberMe.isChecked());
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("user", strUser);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(this, "Tên đăng nhập và mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void rememberUser(String u, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status) {
            //xoa tinh trang luu truoc do
            edit.clear();
        } else {
            edit.putString("USERNAME", u);
            edit.putString("PASSWORK", p);
            edit.putBoolean("REMEMBER", status);
        }
        edit.commit();
    }
}