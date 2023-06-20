package com.example.jkost_android;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jkost_android.ui.auth.LoginActivity;
import com.example.jkost_android.ui.auth.RegisterActivity;
import com.example.jkost_android.util.UtilApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TransaksiActivity extends Activity {

    private EditText editTextKamarId,editTextUserId ,editTextNamePesan, editTextTgl, editTextTotal;
    private Spinner  editTextDurasi;
    private ImageButton btnimg;
    private Button buttonSubmit;
    private String url = "http://"+ UtilApi.API_URL  + "/api/order";
    private int durasiSewa;
    private int tahun,bulan,hari;
    private AutoCompleteTextView autoCompleteTextView, autoCompleteTextView2;
    ArrayAdapter<String> adapter,adapter2;
    String[] suggestions = {"P", "L"};
    String[] suggestions2 = {"1 bulan", "3 bulan", "6 bulan","9 bulan","12 bulan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);


        SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("Id", "");
        editTextUserId = findViewById(R.id.user_id);
        editTextUserId.setText(userId);
        editTextUserId.setVisibility(View.GONE);
        editTextKamarId = findViewById(R.id.kamar_id);
        editTextNamePesan = findViewById(R.id.nama_pemesan);
        editTextTotal = findViewById(R.id.total_price);
        editTextDurasi = findViewById(R.id.durasi_sewa);
        String[] durasi = {"1 Bulan", "3 Bulan", "6 Bulan", "9 Bulan", "12 Bulan"};

// Buat adapter untuk opsi jenis kelamin
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, durasi);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editTextDurasi.setAdapter(adapter);
        editTextDurasi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedJenisKelamin = durasi[position];
                if(position == 0){

                    durasiSewa = 1;
                } else if(position == 1){

                    durasiSewa = 3;
                }else if(position == 2){

                    durasiSewa = 6;
                }else if(position == 3){

                    durasiSewa = 9;
                }else if(position == 4){

                    durasiSewa = 12;
                }
                // Lakukan tindakan sesuai dengan jenis kelamin yang dipilih
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Tindakan ketika tidak ada pilihan yang dipilih
            }
        });



        editTextTgl = findViewById(R.id.tgl_sewa);
        editTextTotal = findViewById(R.id.total_price);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        editTextTgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                tahun = calendar.get(Calendar.YEAR);
                bulan = calendar.get(Calendar.MONTH);
                hari = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog;
                dialog = new DatePickerDialog(TransaksiActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tahun = year;
                        bulan = month;
                        hari = dayOfMonth;

                        editTextTgl.setText(tahun + "-" + bulan + "-"+hari);
                    }
                },tahun,bulan,hari);
                dialog.show();
            }
        });


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaksi();
            }
        });

    }



    private void transaksi() {
        final String userid = editTextUserId.getText().toString().trim();
        final String kamarid = editTextKamarId.getText().toString().trim();
        final String namapemesan = editTextNamePesan.getText().toString().trim();
        final String durasi = editTextDurasi.getSelectedItem().toString().trim();
        final String tglpesan = editTextTgl.getText().toString().trim();
        final String total = editTextTotal.getText().toString().trim();

//         Buat request POST ke URL REGISTER_URL
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Tanggapan dari server jika pendaftaran berhasil
//                        Intent intent = new Intent(TransaksiActivity.this, RiwayatTransaksiActivity.class);
//                        startActivity(intent);
//                        Toast.makeText(TransaksiActivity.this, response, Toast.LENGTH_SHORT).show();
                        Log.d("Transaction Success"+response, "Transaction was successful!");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Tanggapan dari server jika terjadi kesalahan
                        Toast.makeText(TransaksiActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
//                params.put("user_id", userid);
//                params.put("kamar_id", kamarid);
//                params.put("nama_pemesan", namapemesan);
//                params.put("durasi_sewa", durasi);
//                params.put("total_price", total);
//                params.put("tgl_sewa", tglpesan);

                params.put("user_id", userid);
                params.put("kamar_id", kamarid);
                params.put("nama_pemesan", namapemesan);
                params.put("durasi_sewa", "1");
                params.put("total_price", total);
                params.put("tgl_sewa", tglpesan);
                return params;
            }
        };

        // Buat antrian permintaan Volley dan tambahkan permintaan ke antrian
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


//
//        // Membuat RequestQueue dengan menggunakan Volley
//        RequestQueue queue = Volley.newRequestQueue(this);
//// Membuat objek JSON untuk dikirimkan sebagai permintaan
//        JSONObject requestBody = new JSONObject();
//        try {
//                requestBody.put("user_id", userid);
//                requestBody.put("kamar_id", kamarid);
//                requestBody.put("nama_pemesan", namapemesan);
//                requestBody.put("durasi_sewa", durasi);
//                requestBody.put("total_price", total);
//                requestBody.put("tgl_sewa", tglpesan);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//// Membuat permintaan POST dengan Volley
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, requestBody,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Intent intent = new Intent(TransaksiActivity.this, RiwayatTransaksiActivity.class);
//                        startActivity(intent);
////                        Toast.makeText(TransaksiActivity.this, response, Toast.LENGTH_SHORT).show();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // Tangani kesalahan dari permintaan
//                        // ...
//                        error.printStackTrace();
//                    }
//                });
//
//// Menambahkan permintaan ke RequestQueue
//        queue.add(request);
    }






}
