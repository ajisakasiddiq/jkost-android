package com.example.jkost_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class EditProfileActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;
    private static final int REQUEST_IMAGE_PICK = 3;

    private ImageView profileImageView;
    private EditText nameEditText;
    private EditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofil2);

        // Buat objek RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

// URL endpoint untuk mengambil data pengguna
        String url = "http://10.10.5.58:8000/api/user";

// Buat permintaan GET
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Tangani respons dari permintaan

                        // Misalkan respons berupa JSON
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            // Ambil data pengguna dari respons JSON
                            String name = jsonObject.getString("name");
                            String alamat = jsonObject.getString("alamat");
                            String no_hp = jsonObject.getString("no_hp");
                            String jenis_kelamin = jsonObject.getString("kelamin");
                            String foto = jsonObject.getString("foto");
                            String email = jsonObject.getString("email");
                            String username = jsonObject.getString("username");

                            // Tampilkan data pengguna di tampilan EditText atau TextView
                            editTextUsername.setText(username);
                            editTextEmail.setText(email);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Tangani kesalahan saat permintaan
                    }
                });

// Tambahkan permintaan ke RequestQueue
        requestQueue.add(stringRequest);


//
//        profileImageView = findViewById(R.id.profileImageView);
//        nameEditText = findViewById(R.id.nameEditText);
//        emailEditText = findViewById(R.id.emailEditText);
//        Button changeImageButton = findViewById(R.id.changeImageButton);
//        Button saveButton = findViewById(R.id.saveButton);
//
//        changeImageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                selectImage();
//            }
//        });
//
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                saveProfile();
//            }
//        });
    }
//    private void selectImage() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
//            != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}
////                    REQUEST
//            );
//        }
//    }
}