package com.example.jkost_android.ui.auth;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jkost_android.MainActivity;
import com.example.jkost_android.util.UtilApi;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MyServerRequest {
    private static final String TAG = "MyServerRequest";
    private final Context context;
    private final RequestQueue requestQueue;

    public MyServerRequest(Context context) {
        this.context = context;
        this.requestQueue = Volley.newRequestQueue(context);
    }

    public void login(String email, String password, Response.Listener<String> successListener, Response.ErrorListener errorListener) {
        // URL endpoint untuk login
        String url = "http://"+ UtilApi.API_URL  + "/api/login";

        // membuat objek RequestQueue untuk mengirim request ke server
        RequestQueue queue = Volley.newRequestQueue(context);

        // membuat objek StringRequest untuk melakukan request POST
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean error = jsonResponse.getBoolean("success");
                            boolean success = jsonResponse.getBoolean("success");
                            String message = jsonResponse.getString("message");
                            JSONObject data = jsonResponse.getJSONObject("data");
                            String token = data.getString("token");
                            String name = data.getString("name");
                            String email = data.getString("email");
                            String username = data.getString("username");
                            String id =  data.getString("id");
                            SharedPreferences sharedPreferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("Id", id); // Simpan ID pengguna
                            editor.putString("username", username);
                            editor.putString("email", email);
                            editor.putString("name", name);// Simpan nama pengguna
                            editor.apply();
                            if (error) {
                                // response dari server jika login berhasil
                                Intent intent = new Intent(context, MainActivity.class);
                                context.startActivity(intent);
                            } else {
                                // response dari server jika login gagal
                                Toast.makeText(context, "Login gagal", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // response dari server jika terjadi kesalahan pada request atau response dari server
                        Toast.makeText(context, "Terjadi kesalahan pada server "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        // menambahkan request ke dalam queue
        queue.add(stringRequest);
    }
    public void updateProfile(String userId, String name, String username, String alamat, String noHp, String kelamin, String email, String password, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener) {
        // URL endpoint untuk update profil
        String url = "http://172.17.202.139:8000/api/user-update/";

        // Buat objek JSON untuk mengirim data yang akan diperbarui
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("id",userId);
            requestBody.put("name", name);
            requestBody.put("username", username);
            requestBody.put("alamat", alamat);
            requestBody.put("no_hp", noHp);
            requestBody.put("kelamin", kelamin);
            requestBody.put("email", email);
            requestBody.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Buat objek permintaan Volley POST
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, requestBody,
                successListener,
                errorListener);

        // Menambahkan permintaan ke antrian permintaan Volley
        Volley.newRequestQueue(context.getApplicationContext()).add(request);
    }


}