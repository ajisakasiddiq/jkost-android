package com.example.jkost_android;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RiwayatPemesanan {
    private String namaProduk;
    private String tanggalPemesanan;

    public RiwayatPemesanan(String namaProduk, String tanggalPemesanan) {
        this.namaProduk = namaProduk;
        this.tanggalPemesanan = tanggalPemesanan;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public String getTanggalPemesanan() {
        return tanggalPemesanan;
    }
}
