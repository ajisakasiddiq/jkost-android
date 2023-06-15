package com.example.jkost_android;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jkost_android.ui.SliderAdapter;
import com.example.jkost_android.ui.fragment.HomeFragment;
import com.example.jkost_android.util.UtilApi;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();


    private ViewPager2 viewPager;
    private HashMap<String, JSONObject> dataMap = new HashMap<>();


    private TabLayout tabLayout;
    private ArrayList<ModelClass> kamarList;
    private ModelClass modelClass;
//    private deta

    private int[] imageList = {R.drawable.rumah, R.drawable.rumah, R.drawable.rumah};
    private TextView textViewStatusKost;
    private TextView textViewStatusKamar;
    private TextView textViewNamaKost;
    private TextView textViewAlamat;
    private TextView textViewDeskripsiKost;

    public DetailActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kos);

        viewPager = findViewById(R.id.viewPager);
//        tabLayout = findViewById(R.id.tabLayout);
        textViewStatusKost = findViewById(R.id.textViewStatusKost);
        textViewStatusKamar = findViewById(R.id.textViewStatusKamar);
        textViewNamaKost = findViewById(R.id.textViewNamaKost);
        textViewAlamat = findViewById(R.id.textViewAlamat);
        textViewDeskripsiKost = findViewById(R.id.textViewDeskripsiKost);

        SliderAdapter adapter = new SliderAdapter(this, imageList);
        viewPager.setAdapter(adapter);

        loaddetails();
//        load();
    }



private void load(){

//    Simulasi respons JSON
    String jsonResponse = "[{ \"nama_kost\": \"kos anjayy\", \"alamat\": \"mastrip\", \"deskripsi_kost\": \"enek wifi\", \"slug\": \"nama-kost\", \"longitude\": \"323113\", \"latitude\": \"323113\", \"jenis_kamar\": \"Bebas\", \"no_kamar\": 20, \"harga\": 20000, \"img_pertama\": \"1.jpg\", \"img_kedua\": \"2.jpg\", \"img_ketiga\": \"3.jpg\", \"img_keempat\": \"4.jpg\", \"deskripsi_kamar\": \"enak banget\" }]";
//String jsonResponse ="[]";
    try {
        JSONArray jsonArray = new JSONArray(jsonResponse);

        if (jsonArray.length() > 0) {
            JSONObject data = jsonArray.getJSONObject(0);

            String statusKost = data.getString("status_kost");
            String statusKamar = data.getString("status_kamar");
            String namaKost = data.getString("nama_kost");
            String alamat = data.getString("alamat");
            String deskripsiKost = data.getString("deskripsi_kost");

            // Set nilai ke TextView
            textViewStatusKost.setText(statusKost);
            textViewStatusKamar.setText(statusKamar);
            textViewNamaKost.setText(namaKost);
            textViewAlamat.setText(alamat);
            textViewDeskripsiKost.setText(deskripsiKost);

            // Tampilkan data ke dalam UI atau lakukan operasi lainnya
            Log.d(TAG, "Status Kost: " + statusKost);
            Log.d(TAG, "Status Kamar: " + statusKamar);
            Log.d(TAG, "Nama Kost: " + namaKost);
            Log.d(TAG, "Alamat: " + alamat);
            Log.d(TAG, "Deskripsi Kost: " + deskripsiKost);
        } else {
            Log.d(TAG, "Data kosong");
        }
    } catch (JSONException e) {
        e.printStackTrace();
    }
}

    private void loaddetails() {
        String targetId = "1";
        String url = "http://"+ UtilApi.API_URL  + "/api/data/" +targetId;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray dataArray = response.getJSONArray("data");

                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject jsonObject = dataArray.getJSONObject(i);
                                String id = jsonObject.getString("id_kamar");

                                // Simpan data ke dalam HashMap berdasarkan ID
                                dataMap.put(id, jsonObject);
                            }

                            // Tampilkan data berdasarkan ID yang diinginkan
                            String desiredId = "1";
                            JSONObject desiredData = dataMap.get(desiredId);
                            if (desiredData != null) {
                                String namaKost = desiredData.getString("nama_kost");
                                String alamat = desiredData.getString("alamat");
                                String deskripsiKost = desiredData.getString("deskripsi_kost");

//                                textViewStatusKost.setText(statusKost);
//                                textViewStatusKamar.setText(statusKamar);
                                textViewNamaKost.setText(namaKost);
                                textViewAlamat.setText(alamat);
                                textViewDeskripsiKost.setText(deskripsiKost);

                            } else {
                                // ID yang diinginkan tidak ditemukan
                                Log.d(TAG,"Data tidak ditemukan");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        // Menambahkan permintaan ke antrian Volley
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }

}

