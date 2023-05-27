package com.example.jkost_android.getData;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class KostDataFetcher {
    private static final String DATA_URL = "http://example.com/api/kost";

    public void fetchData(final KostDataListener listener) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                DATA_URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Panggil metode pada listener dengan parameter response
                        listener.onDataReceived(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Panggil metode pada listener dengan parameter error
                        listener.onDataError(error);
                    }
                }
        );

        // Buat RequestQueue dan tambahkan request ke dalam antrian
        RequestQueue requestQueue = Volley.newRequestQueue(listener.getContext());
        requestQueue.add(jsonArrayRequest);
    }

    public interface KostDataListener {
        void onDataReceived(JSONArray response);
        void onDataError(VolleyError error);
        Context getContext();
    }
}
