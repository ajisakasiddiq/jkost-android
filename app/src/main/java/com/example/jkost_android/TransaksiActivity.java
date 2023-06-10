package com.example.jkost_android;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.jkost_android.util.UtilApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class TransaksiActivity extends Activity {

    private EditText editTextKamarId,editTextUserId ,editTextNamePesan, editTextTgl, editTextTotal;
    private Spinner  editTextDurasi;
    private ImageButton btnimg;
    private Button buttonSubmit;
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
        btnimg = findViewById(R.id.imageButton);
//        editTextTotal = findViewById(R.id.total_price);

        buttonSubmit = findViewById(R.id.buttonSubmit);

        btnimg.setOnClickListener(new View.OnClickListener() {
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
                // Ambil input pengguna
                String userid = editTextUserId.getText().toString().trim();
                String kamarid = editTextKamarId.getText().toString().trim();
                String namapemesan = editTextNamePesan.getText().toString().trim();
                String durasi = editTextDurasi.getSelectedItem().toString().trim();
                String tglpesan = editTextTgl.getText().toString().trim();
                String total = editTextTotal.getText().toString().trim();
                // Buat objek JSON untuk dikirim ke API
                JSONObject requestObject = new JSONObject();
                try {
                    requestObject.put("user_id", editTextUserId);
                    requestObject.put("kamar_id", editTextKamarId);
                    requestObject.put("nama_pemesan", editTextNamePesan);
                    requestObject.put("durasi_sewa", editTextDurasi);
                    requestObject.put("total_price", editTextTotal);
                    requestObject.put("tgl_sewa", editTextTgl);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Buat permintaan HTTP POST menggunakan Volley
                String url = "http://"+ UtilApi.API_URL  + "/api/order";
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, requestObject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Tanggapan sukses dari API
                                try {
                                    String message = response.getString("message");
                                    Toast.makeText(TransaksiActivity.this, message, Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Tanggapan error dari API
                                Toast.makeText(TransaksiActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                // Tambahkan permintaan ke antrian permintaan Volley
                Volley.newRequestQueue(TransaksiActivity.this).add(jsonObjectRequest);
            }
        });
    }

}
