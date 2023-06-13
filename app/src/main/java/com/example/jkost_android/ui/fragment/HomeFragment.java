package com.example.jkost_android.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jkost_android.KostAdapter;
import com.example.jkost_android.ModelClass;
import com.example.jkost_android.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private KostAdapter kamarAdapter;
    private ArrayList<ModelClass> kamarList;
    private ModelClass modelClass;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private String url = "http://10.212.213.36:8000/api/data";
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

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        kamarList = new ArrayList<ModelClass>();
        kamarAdapter = new KostAdapter(getContext(), kamarList);
        recyclerView.setAdapter(kamarAdapter);


        loadKamarData();


        return view;
    }

//private void getData(){
//        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                kamarList = new ArrayList<>();
//                 try {
//                     JSONObject jsonObject = new JSONObject(response);
//                     JSONArray jsonArray = jsonObject.getJSONArray("result");
//                     for (int i=0;i<jsonArray.length();i++){
//                         JSONObject data = jsonArray.getJSONObject(i);
//                         modelClass = new ModelClass();
//                        modelClass.setNamakost(data.getString("nama_kost"));
////                        modelClass.getHarga(data.getString("harga"));
//                        kamarList.add(modelClass);
//                     }
//
////                     RecyclerView recyclerView = findViewById(R.id.recyclerView);
//                     RecyclerView.Adapter adapter = new KostAdapter(getContext(),kamarList);
//                     recyclerView.setAdapter(adapter);
//
//
//
//                 }catch (JSONException e){
//                     e.printStackTrace();
//                 }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        requestQueue.add(stringRequest);
//}

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
