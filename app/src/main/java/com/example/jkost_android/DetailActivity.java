package com.example.jkost_android;

import android.content.Context;
import android.content.SharedPreferences;
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
    }



    private void loaddetails() {
        String targetId = "1";
        SharedPreferences sharedPreferences = getSharedPreferences("idkamar", Context.MODE_PRIVATE);
        String roomId = sharedPreferences.getString("id", "");
        Log.d("SharedPreferences", "Room ID: " + roomId);
        String url = "http://"+ UtilApi.API_URL  + "/api/data/";
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

