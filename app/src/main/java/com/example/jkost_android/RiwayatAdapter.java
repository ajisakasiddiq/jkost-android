package com.example.jkost_android;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jkost_android.ui.profile.Transaksi;

import java.util.ArrayList;
import java.util.List;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.RiwayatViewHolder> {
    private List<Transaksi> riwayatList;
    private Context context;
    private LayoutInflater layoutInflater;
    public RiwayatAdapter(Context context, List<Transaksi> riwayatList) {
        this.riwayatList = riwayatList;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RiwayatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_riwayat_adapter, parent, false);
        return new RiwayatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatViewHolder holder, int position) {
        Transaksi item = riwayatList.get(position);

        holder.name.setText(item.getNama_kost());
        holder.harga.setText(item.getTotal_price());
        holder.no_kamar.setText(item.getNo_kamar());
        holder.status.setText(item.getStatus());
        // Setel informasi lainnya untuk item riwayat pemesanan
    }

    @Override
    public int getItemCount() {
        return riwayatList.size();
    }

    public static class RiwayatViewHolder extends RecyclerView.ViewHolder {
        TextView name, harga, no_kamar, status;

        public RiwayatViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            no_kamar = itemView.findViewById(R.id.no_kamar);
            harga = itemView.findViewById(R.id.harga);
            status = itemView.findViewById(R.id.status);
        }
    }
}
