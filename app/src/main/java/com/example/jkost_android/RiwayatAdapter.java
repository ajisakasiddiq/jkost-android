package com.example.jkost_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.RiwayatViewHolder> {
    private List<RiwayatPemesanan> riwayatList;

    public RiwayatAdapter(List<RiwayatPemesanan> riwayatList) {
        this.riwayatList = riwayatList;
    }

    @NonNull
    @Override
    public RiwayatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_riwayat_adapter, parent, false);
        return new RiwayatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatViewHolder holder, int position) {
        RiwayatPemesanan riwayat = riwayatList.get(position);

        holder.namaProdukTextView.setText(riwayat.getNamaProduk());
        holder.tanggalPemesananTextView.setText(riwayat.getTanggalPemesanan());

        // Setel informasi lainnya untuk item riwayat pemesanan
    }

    @Override
    public int getItemCount() {
        return riwayatList.size();
    }

    public static class RiwayatViewHolder extends RecyclerView.ViewHolder {
        TextView namaProdukTextView;
        TextView tanggalPemesananTextView;

        public RiwayatViewHolder(@NonNull View itemView) {
            super(itemView);
            namaProdukTextView = itemView.findViewById(R.id.namaProdukTextView);
            tanggalPemesananTextView = itemView.findViewById(R.id.tanggalPemesananTextView);
        }
    }
}