package com.example.jkost_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailKostAdapter extends RecyclerView.Adapter<DetailKostAdapter.DetailKostViewHolder> {
    private Context context;
    private String apiUrl;

    public DetailKostAdapter(Context context, String apiUrl) {
        this.context = context;
        this.apiUrl = apiUrl;
    }

    @NonNull
    @Override
    public DetailKostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_detail_kos, parent, false);
        return new DetailKostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailKostViewHolder holder, int position) {
        // Mengambil data dari API menggunakan Volley
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, apiUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Mengambil data yang diperlukan dari response JSON
                            String title = response.getString("title");
                            String price = response.getString("price");
                            String address = response.getString("address");
                            String facilities = response.getString("facilities");

                            // Mengisi TextView dengan data yang diambil
                            holder.titleTextView.setText(title);
                            holder.priceTextView.setText(price);
                            holder.addressTextView.setText(address);
                            holder.facilitiesTextView.setText(facilities);
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
        queue.add(request);
    }

    @Override
    public int getItemCount() {
        return 1; // Jumlah item yang ingin ditampilkan, dalam hal ini hanya satu item
    }

    public static class DetailKostViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, priceTextView, addressTextView, facilitiesTextView;

        public DetailKostViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            addressTextView = itemView.findViewById(R.id.addressTextView);
            facilitiesTextView = itemView.findViewById(R.id.facilitiesTextView);
        }
    }
}

