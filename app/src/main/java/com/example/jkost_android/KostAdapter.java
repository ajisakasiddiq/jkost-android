package com.example.jkost_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class KostAdapter extends RecyclerView.Adapter<KostAdapter.MyHolder> {

    Context context;
    List<ModelClass> arrayList;
    LayoutInflater layoutInflater;

    public KostAdapter(Context context, ArrayList<ModelClass> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public KostAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.item_file,parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KostAdapter.MyHolder holder, int position) {
            holder.kostName.setText(arrayList.get(position).getNamakost());
            holder.nokamar.setText(arrayList.get(position).getNo_kamar());
//            holder.kostNum.setText(arrayList.get(position).getHarga());
            holder.kostStatus.setText(arrayList.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        TextView kostName, kostNum,kostStatus,nokamar;
        ImageView img;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            kostName=itemView.findViewById(R.id.name);
            kostNum=itemView.findViewById(R.id.harga);
            kostStatus=itemView.findViewById(R.id.status);
            img=itemView.findViewById(R.id.img);
            nokamar=itemView.findViewById(R.id.no_kamar);
        }
    }
}
