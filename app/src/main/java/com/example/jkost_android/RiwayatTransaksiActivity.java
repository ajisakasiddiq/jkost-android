package com.example.jkost_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.jkost_android.ui.profile.RiwayatTransaksiAdapter;
import com.example.jkost_android.ui.profile.Transaksi;

import java.util.ArrayList;

public class RiwayatTransaksiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_transaksi);

        ListView listView = findViewById(R.id.listView);

        //Buat daftar riwayat transaksi
        ArrayList<Transaksi> transaksiList = new ArrayList<>();
        transaksiList.add(new Transaksi("1 Januari 2023", 100000));
        transaksiList.add(new Transaksi("1 Maret 2023", 100000));
        transaksiList.add(new Transaksi("1 Mei 2023", 100000));

        //Buat adapter dan hubungkan dengan ListView
        RiwayatTransaksiAdapter adapter = new RiwayatTransaksiAdapter(this, transaksiList);
        listView.setAdapter(adapter);
    }
}