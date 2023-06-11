package com.example.jkost_android.ui.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jkost_android.R;

import java.util.ArrayList;

public class RiwayatTransaksiAdapter extends ArrayAdapter<Transaksi> {
    public RiwayatTransaksiAdapter(Context context, ArrayList<Transaksi> transaksilist){
        super(context,0, transaksilist);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Transaksi transaksi = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_riwayat_transaksi, parent, false);
        }

        TextView textViewTanggal = convertView.findViewById(R.id.textViewTanggal);
        TextView textViewTotal = convertView.findViewById(R.id.textViewTotal);
//
//        textViewTanggal.setText(transaksi.getTanggal());
//        textViewTotal.setText(String.valueOf(transaksi.getTotal()));

        return convertView;
    }
}
