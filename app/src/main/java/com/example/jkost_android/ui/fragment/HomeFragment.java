package com.example.jkost_android.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jkost_android.DetailActivity;
import com.example.jkost_android.EditProfileActivity;
import com.example.jkost_android.KostAdapter;
import com.example.jkost_android.ModelClass;
import com.example.jkost_android.R;
import com.example.jkost_android.TransaksiActivity;
import com.example.jkost_android.util.UtilApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private RelativeLayout item;
    private KostAdapter kamarAdapter;
    private ArrayList<ModelClass> kamarList;
    private ModelClass modelClass;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private String url = "http://"+ UtilApi.API_URL  + "/api/data";
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);


        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        kamarList = new ArrayList<ModelClass>();
        kamarAdapter = new KostAdapter(getContext(), kamarList);
        recyclerView.setAdapter(kamarAdapter);


        loadKamarData();
//        recyclerView.setOnClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // Dapatkan item yang diklik dari adapter
//
//                modelClass = new ModelClass();
//                ModelClass item = kamarAdapter.getItem(position);
//
//                // Simpan data item yang diklik ke SharedPreferences
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("clickedItem", item.getId());
//                editor.apply();
//
//                // Beralih ke halaman DetailActivity
//                Intent intent = new Intent(getActivity(), DetailActivity.class);
//                startActivity(intent);
//            }
//        });

        return view;
    }


    private void loadKamarData() {
         // Ganti dengan URL endpoint API Anda

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                modelClass = new ModelClass();
                                modelClass.setNamakost(jsonObject.getString("nama_kost"));
                                modelClass.setNo_kamar(jsonObject.getString("no_kamar"));
//                                modelClass.setHarga(jsonObject.getString("harga"));
//                        modelClass.getHarga(data.getString("harga"));
                                HomeFragment.this.kamarList.add(modelClass);
                            }

                            kamarAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}
