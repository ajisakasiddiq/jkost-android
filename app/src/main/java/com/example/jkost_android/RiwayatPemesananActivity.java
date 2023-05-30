package com.example.jkost_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class RiwayatPemesananActivity extends AppCompatActivity {
    private RecyclerView riwayatRecyclerView;
    private RiwayatAdapter riwayatAdapter;
    private List<RiwayatPemesanan> riwayatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_pemesanan);

        // Inisialisasi RecyclerView
        riwayatRecyclerView = findViewById(R.id.riwayatRecyclerView);
        riwayatRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Mengisi data riwayat pemesanan
        riwayatList = getDataRiwayatPemesanan();

        // Inisialisasi adapter dan mengatur adapter ke RecyclerView
        riwayatAdapter = new RiwayatAdapter(riwayatList);
        riwayatRecyclerView.setAdapter(riwayatAdapter);
    }

    // Metode ini mengembalikan daftar data riwayat pemesanan
    private List<RiwayatPemesanan> getDataRiwayatPemesanan() {
        // Implementasikan logika untuk mengambil data riwayat pemesanan dari sumber data (misalnya, database) dan kembalikan sebagai List<RiwayatPemesanan>
        List<RiwayatPemesanan> riwayatList = new ArrayList<>();

        // Contoh data riwayat pemesanan
        riwayatList.add(new RiwayatPemesanan("Produk 2", "5 Februari 2023"));
        riwayatList.add(new RiwayatPemesanan("Produk 3", "10 Maret 2023"));
        // ... tambahkan data riwayat pemesanan lainnya

        return riwayatList;
    }
}