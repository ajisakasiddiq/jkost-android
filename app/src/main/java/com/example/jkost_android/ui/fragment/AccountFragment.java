package com.example.jkost_android.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jkost_android.EditProfileActivity;
import com.example.jkost_android.R;
import com.example.jkost_android.util.UtilApi;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
       Button edit_btn = view.findViewById(R.id.editBtn);
        TextView textViewUserId = view.findViewById(R.id.titleUsername);
        TextView textViewname = view.findViewById(R.id.titleName);
        TextView textViewEmail = view.findViewById(R.id.profileEmail);
        TextView textViewName = view.findViewById(R.id.profileName);
        TextView textViewnUserName = view.findViewById(R.id.profileUsername);

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), EditProfileActivity.class);
                startActivity(intent);
            }
        });
        // Mengakses SharedPreferences untuk mendapatkan data pengguna yang login
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("Id", ""); // Mendapatkan ID pengguna
        String url = "http://"+ UtilApi.API_URL  + "/api/userdata/"  + userId;
// Buat permintaan GET
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Tangani respons dari permintaan
                        System.out.println("response = " + response);
                        // Misalkan respons berupa JSON
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            // Ambil data pengguna dari respons JSON
                            String id = jsonObject.getString("id");
                            String name = jsonObject.getString("name");
                            String alamat = jsonObject.getString("alamat");
                            String no_hp = jsonObject.getString("no_hp");
                            String jenis_kelamin = jsonObject.getString("kelamin");
                            String foto = jsonObject.getString("foto");
                            String email = jsonObject.getString("email");
                            String username = jsonObject.getString("username");
                            String nik = jsonObject.getString("nik");
                            textViewUserId.setText("" + userId);
                            textViewnUserName.setText("" + username);
                            textViewEmail.setText("" + email);
                            textViewName.setText("" +name);
                            // Tampilkan data pengguna di tampilan EditText atau TextView
//                            name.setText(username);
//                            editTextEmail.setText(email);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Tangani kesalahan saat permintaan
                    }
                });

// Tambahkan permintaan ke RequestQueue
        requestQueue.add(stringRequest);

// Menampilkan data pengguna yang login

        return view;
    }
}

