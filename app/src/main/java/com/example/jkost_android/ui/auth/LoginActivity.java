package com.example.jkost_android.ui.auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.jkost_android.R;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnlogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ambil nilai dari edit text username dan password
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                // Lakukan request ke server dengan menggunakan Volley
                MyServerRequest serverRequest = new MyServerRequest(LoginActivity.this);
                serverRequest.login(email, password, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Response berhasil diterima, lakukan aksi yang diperlukan
                        Toast.makeText(LoginActivity.this, "Login berhasil", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Terjadi error saat melakukan request ke server
                        Toast.makeText(LoginActivity.this, "Terjadi kesalahan: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }






                });
            }





});
    }
}