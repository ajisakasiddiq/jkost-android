package com.example.jkost_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jkost_android.ui.auth.MyServerRequest;
import com.example.jkost_android.util.UtilApi;

import org.json.JSONException;
import org.json.JSONObject;

public class EditProfileActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;
    private static final int REQUEST_IMAGE_PICK = 3;

    private ImageView profileImageView;
    private EditText name;
    private EditText email;
    private EditText nameEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText tglLahirEditText;
    private Spinner jenisKelaminEditText;
    private EditText nikEditText;
    private EditText emailEditText;
    private EditText noHpEditText;
    private EditText alamatEditText;
    private Button simpanButton;
    String jenisKelamin = "L";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofil2);

        // Buat objek RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        MyServerRequest myServerRequest = new MyServerRequest(this);
        nameEditText = findViewById(R.id.name);
        usernameEditText = findViewById(R.id.Txtusername);
        nikEditText = findViewById(R.id.nik);
        emailEditText = findViewById(R.id.email);

        noHpEditText = findViewById(R.id.no_hp);
        simpanButton = findViewById(R.id.id_detail_edit);
        jenisKelaminEditText = findViewById(R.id.spinnerJenisKelamin);
        // Daftar opsi jenis kelamin
        String[] jenisKelaminOptions = {"Laki-laki", "Perempuan"};

// Buat adapter untuk opsi jenis kelamin
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, jenisKelaminOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Terapkan adapter pada Spinner
        jenisKelaminEditText.setAdapter(adapter);
        jenisKelaminEditText.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedJenisKelamin = jenisKelaminOptions[position];
                if(position == 0){

                    jenisKelamin = "L";
                } else if(position == 1){

                    jenisKelamin = "P";
                }
                // Lakukan tindakan sesuai dengan jenis kelamin yang dipilih
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Tindakan ketika tidak ada pilihan yang dipilih
            }
        });

        passwordEditText = findViewById(R.id.password_user);
        alamatEditText = findViewById(R.id.alamat);
// URL endpoint untuk mengambil data pengguna

//        String url = "http://172.17.202.139:8000/api/user";
        SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("Id", ""); // Mendapatkan ID pengguna
        String username = sharedPreferences.getString("username", ""); // Mendapatkan nama pengguna
        String email = sharedPreferences.getString("email", "");
        String name = sharedPreferences.getString("name", "");
        String url = "http://"+ UtilApi.API_URL  + "/api/userdata/"  + userId;
// Buat permintaan GET
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Tangani respons dari permintaan
                        System.out.println("response = " + response);
                        // Misalkan respons berupa JSON
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            // Ambil data pengguna dari respons JSON
                            String id = jsonObject.getString("id");
                            String name = jsonObject.getString("name");
                            String alamat = jsonObject.getString("alamat");
                            String no_hp = jsonObject.getString("no_hp");
                            String jenis_kelamin = jsonObject.getString("kelamin");
                            String foto = jsonObject.getString("foto");
                            String email = jsonObject.getString("email");
                            String username = jsonObject.getString("username");
                            String nik = jsonObject.getString("nik");
                            nameEditText.setText(name);
                            usernameEditText.setText(username);
                            nikEditText.setText(nik);

                            emailEditText.setText(email);
                            noHpEditText.setText(no_hp);
                            alamatEditText.setText(alamat);
                            // Tampilkan data pengguna di tampilan EditText atau TextView
//                            name.setText(username);
//                            editTextEmail.setText(email);
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

        simpanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ID pengguna yang ingin diperbarui
                String name = nameEditText.getText().toString(); // Nama baru
                String username = usernameEditText.getText().toString(); // Username baru
                String alamat = alamatEditText.getText().toString(); // Alamat baru
                String noHp = noHpEditText.getText().toString(); // Nomor HP baru
                String kelamin = jenisKelamin;// Jenis kelamin baru
                String email = emailEditText.getText().toString(); // Email baru
                String password = passwordEditText.getText().toString(); // Password baru

                Response.Listener<JSONObject> successListener = new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Tanggapan berhasil diterima dari server
                        try {
                            String message = response.getString("message");
                            // Lakukan tindakan sesuai kebutuhan Anda dengan respons dari server
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };



                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error response jika terjadi kesalahan pada request atau response dari server
                        Toast.makeText(getApplicationContext(), "Terjadi kesalahan pada server " + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                };
                myServerRequest.updateProfile(userId, name, username, alamat, noHp, kelamin, email, password, successListener, errorListener);
// Panggil fungsi updateProfile untuk memperbarui profil pengguna


            }
        });
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