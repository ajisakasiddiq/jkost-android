package com.example.jkost_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.jkost_android.util.UtilApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity2 extends AppCompatActivity {
    private EditText searchEditText;
    private Button searchButton;
    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);

//        searchEditText = findViewById(R.id.searchEditText);
//        searchButton = findViewById(R.id.searchButton);
//        recyclerView = findViewById(R.id.recyclerView);

        // Inisialisasi adapter dan RecyclerView
        searchAdapter = new SearchAdapter(SearchActivity2.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(searchAdapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchEditText.getText().toString().trim();
                performSearch(query);
            }
        });
    }

    private void performSearch(String query) {
        String url = "http://"+ UtilApi.API_URL  + "/api/search/"  + query;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Tangani respons pencarian yang diterima
                        List<ModelClass> kamarList = parseSearchResults(response);
//                        searchAdapter.setData(kamarList);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Tangani kesalahan yang terjadi saat melakukan pencarian
                        Toast.makeText(SearchActivity2.this, "Terjadi kesalahan: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // Tambahkan permintaan ke antrian permintaan Volley
        Volley.newRequestQueue(this).add(request);
    }

    private List<ModelClass> parseSearchResults(JSONObject response) {
        // Lakukan parsing dan konversi respons JSON ke objek List<Kamar>
        // Anda dapat menyesuaikan kode ini sesuai dengan struktur respons JSON dari server

        List<ModelClass> kamarList = new ArrayList<>();

        try {
            JSONArray resultsArray = response.getJSONArray("results");

            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject kamarObject = resultsArray.getJSONObject(i);

                int id = kamarObject.getInt("id");
                String nama = kamarObject.getString("nama");
                String lokasi = kamarObject.getString("lokasi");
                // ... lanjutkan dengan mendapatkan data lainnya sesuai dengan struktur JSON

//                Kamar kamar = new Kamar(id, nama, lokasi);
//                kamarList.add(kamar);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return kamarList;
    }
}

