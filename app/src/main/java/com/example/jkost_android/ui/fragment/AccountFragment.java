package com.example.jkost_android.ui.fragment;

import static android.content.Context.MODE_PRIVATE;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jkost_android.EditProfileActivity;
import com.example.jkost_android.R;
import com.example.jkost_android.ui.auth.LoginActivity;

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
        ImageView btnlogout = view.findViewById(R.id.logout);

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), EditProfileActivity.class);
                startActivity(intent);
            }
        });
        // Mengakses SharedPreferences untuk mendapatkan data pengguna yang login
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("UserData", MODE_PRIVATE);
        String userId = sharedPreferences.getString("userId", ""); // Mendapatkan ID pengguna
        String username = sharedPreferences.getString("username", ""); // Mendapatkan nama pengguna
        String email = sharedPreferences.getString("email", "");
        String name = sharedPreferences.getString("name", "");

// Menampilkan data pengguna yang login
        textViewUserId.setText("User ID: " + userId);
        textViewnUserName.setText("Username: " + username);
        textViewEmail.setText("email: " + email);
        textViewName.setText("name: " +name);
        textViewname.setText("name: " +name);
        return view;
    }

    private void logout() {
        // Hapus data pengguna yang diingat
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        // Setelah logout, pindahkan pengguna ke halaman login
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }
}

