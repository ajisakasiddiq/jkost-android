package com.example.jkost_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.example.jkost_android.ui.SliderAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {
    private String url = "http://192.168.1.4:8000//api/data";
    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    private int[] imageList = {R.drawable.rumah, R.drawable.rumah, R.drawable.rumah};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kos);

        getDetailKost();

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        SliderAdapter adapter = new SliderAdapter(this, imageList);
        viewPager.setAdapter(adapter);

        // Menambahkan indikator TabLayout
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> viewPager.setCurrentItem(position, true)
        ).attach();
    }

    private void getDetailKost() {
        // Buat objek RequestQueue menggunakan Volley
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Buat permintaan GET menggunakan JsonObjectRequest
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Tangani respon JSON di sini
                        // Misalnya, parse respon dan tampilkan data di UI
                        Log.d("DetailActivity", "Response: " + response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Tangani kesalahan jaringan di sini
                    }
                });

        // Tambahkan permintaan ke RequestQueue
        requestQueue.add(jsonObjectRequest);
    }
}