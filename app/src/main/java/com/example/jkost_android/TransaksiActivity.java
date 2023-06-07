package com.example.jkost_android;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.jkost_android.ui.auth.LoginActivity;
import com.example.jkost_android.ui.auth.RegisterActivity;
import com.example.jkost_android.util.UtilApi;
import android.widget.AutoCompleteTextView;

import org.json.JSONException;
import org.json.JSONObject;

public class TransaksiActivity extends Activity {

    private EditText editTextKamarId,editTextUserId ,editTextNamePesan, editTextDurasi, editTextTgl, editTextTotal;
    private Button buttonSubmit;
    private AutoCompleteTextView autoCompleteTextView, autoCompleteTextView2;
    ArrayAdapter<String> adapter,adapter2;
    String[] suggestions = {"P", "L"};
    String[] suggestions2 = {"1 bulan", "3 bulan", "6 bulan","9 bulan","12 bulan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);


        editTextUserId = findViewById(R.id.user_id);
        editTextKamarId = findViewById(R.id.kamar_id);
        editTextNamePesan = findViewById(R.id.nama_pemesan);
        editTextDurasi = findViewById(R.id.durasi_sewa);
        editTextTgl = findViewById(R.id.tgl_sewa);
        editTextTotal = findViewById(R.id.total_price);

        buttonSubmit = findViewById(R.id.buttonSubmit);

        autoCompleteTextView = findViewById(R.id.tgl_sewa);
        adapter = new ArrayAdapter<String>(this,R.layout.list_dropdown, suggestions);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView2 = findViewById(R.id.durasi_sewa);
        adapter2 = new ArrayAdapter<String>(this,R.layout.list_dropdown, suggestions2);
        autoCompleteTextView2.setAdapter(adapter2);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {sendTransaction();}
        });




        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                String adapter = adapterView.getItemAtPosition(i).toString();
            }
        });

        autoCompleteTextView2.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                String adapter2 = adapterView.getItemAtPosition(i).toString();
            }
        });


    }


    public void showDatePickerDialog(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Tangani tanggal yang dipilih oleh pengguna di sini
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                editTextTgl.setText(selectedDate);
            }
        }, 2023, 5, 7);  // Tanggal default yang ditampilkan saat form dibuka

        datePickerDialog.show();
    }



    private void sendTransaction() {
        String url = "http://"+ UtilApi.API_URL  + "/api/order" ;  // Ganti dengan URL API yang sesuai

        // Buat objek JSON untuk data transaksi
        JSONObject transactionData = new JSONObject();
        try {
            transactionData.put("user_id", editTextUserId);
            transactionData.put("kamar_id", editTextKamarId);
            transactionData.put("nama_pemesan", editTextNamePesan);
            transactionData.put("durasi_sewa", editTextDurasi);
            transactionData.put("total_price", editTextTotal);
            transactionData.put("tgl_sewa", editTextTgl);
            // Contoh data transaksi
            // Tambahkan lebih banyak data transaksi sesuai kebutuhan
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Buat permintaan JSON dengan metode POST
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, transactionData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Intent intent = new Intent(TransaksiActivity.this, RiwayatTransaksiActivity.class);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Tangani kesalahan dari permintaan API
                    }
                });

        // Tambahkan permintaan ke antrian Volley
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

}
