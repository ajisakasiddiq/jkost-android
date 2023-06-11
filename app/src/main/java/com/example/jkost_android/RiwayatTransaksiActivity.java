package com.example.jkost_android;

import static android.content.ContentValues.TAG;

import static com.example.jkost_android.util.UtilApi.API_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;
import com.example.jkost_android.ui.fragment.HomeFragment;
import com.example.jkost_android.ui.profile.RiwayatTransaksiAdapter;
import com.example.jkost_android.ui.profile.Transaksi;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.jkost_android.util.UtilApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class RiwayatTransaksiActivity extends AppCompatActivity {
    private String url = "http://"+ UtilApi.API_URL  + "/api/riwayat";

    private RiwayatAdapter riwayatAdapter;
    private ArrayList<Transaksi>transaksiList;
    private Transaksi transaksi;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_transaksi);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                transaksi =new Transaksi();
                                transaksi.setName(jsonObject.getString("nama_kost"));
                                //  transaksi.setNo_kamar(jsonObject.getString("no_kamar"));
//                                modelClass.setHarga(jsonObject.getString("harga"));
//                        modelClass.getHarga(data.getString("harga"));
                                RiwayatTransaksiActivity.this.transaksiList.add(transaksi);
                            }

                            riwayatAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Eror:" + error.getMessage());
                    }
                });

           Volley.newRequestQueue(RiwayatTransaksiActivity.this).add(stringRequest);
    }
}
