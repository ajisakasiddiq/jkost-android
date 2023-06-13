package com.example.jkost_android;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.jkost_android.ui.SliderAdapter;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();

    private String url = "http://192.168.1.13:8000/api/data/";

    private ViewPager2 viewPager;

    private TabLayout tabLayout;

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
        tabLayout = findViewById(R.id.tabLayout);
        textViewStatusKost = findViewById(R.id.textViewStatusKost);
        textViewStatusKamar = findViewById(R.id.textViewStatusKamar);
        textViewNamaKost = findViewById(R.id.textViewNamaKost);
        textViewAlamat = findViewById(R.id.textViewAlamat);
        textViewDeskripsiKost = findViewById(R.id.textViewDeskripsiKost);

        SliderAdapter adapter = new SliderAdapter(this, imageList);
        viewPager.setAdapter(adapter);


        // Simulasi respons JSON
        String jsonResponse = "[{ \"status_kost\": \"1\", \"status_kamar\": \"0\", \"nama_kost\": \"kos anjayy\", \"alamat\": \"mastrip\", \"deskripsi_kost\": \"enek wifi\", \"slug\": \"nama-kost\", \"longitude\": \"323113\", \"latitude\": \"323113\", \"jenis_kamar\": \"Bebas\", \"no_kamar\": 20, \"harga\": 20000, \"img_pertama\": \"1.jpg\", \"img_kedua\": \"2.jpg\", \"img_ketiga\": \"3.jpg\", \"img_keempat\": \"4.jpg\", \"deskripsi_kamar\": \"enak banget\" }]";

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
}

