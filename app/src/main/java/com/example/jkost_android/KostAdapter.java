package com.example.jkost_android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.security.AccessController;
import java.util.ArrayList;
import java.util.List;

public class KostAdapter extends RecyclerView.Adapter<KostAdapter.MyHolder> {

//    private final OnItemClickListener listener;
    Context context;
    List<ModelClass> arrayList;
    LayoutInflater layoutInflater;

//    , OnItemClickListener listener
    public KostAdapter(Context context, ArrayList<ModelClass> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        layoutInflater=LayoutInflater.from(context);
    }



    public interface OnItemClickListener {
        void onItemClick(ModelClass item);
    }



    @NonNull
    @Override
    public KostAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.item_file,parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KostAdapter.MyHolder holder, int position) {
        ModelClass item = arrayList.get(position);

            holder.kostName.setText(arrayList.get(position).getNamakost());
            holder.nokamar.setText(arrayList.get(position).getNo_kamar());
            holder.kostNum.setText(arrayList.get(position).getHarga());
//            holder.kostNum.setText(arrayList.get(position).getHarga());
            holder.kostStatus.setText(arrayList.get(position).getStatus());
//if (item.getStatus() == "0"){
//    holder.kostStatus.setText("Disewakan");
//} else if (item.getStatus() == "1") {
//    holder.kostStatus.setText("Tersedia");
//}else {
//    holder.kostStatus.setText("Data tidak valid");
//}

        holder.btnpesan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Intent i = new Intent(context,DetailActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("nokamar");


                    // Mendapatkan ID kamar
                    String roomId = item.getId();


                    // Menyimpan ID kamar ke SharedPreferences
//                    AccessController view;
                    SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("idkamar", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("id_kamar", roomId);
                    editor.apply();

                    // Buka halaman detail
                    Intent intent = new Intent(view.getContext(), DetailActivity.class);
                    view.getContext().startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public ModelClass getItem(int position) {
        return arrayList.get(position);
    }


    public class MyHolder extends RecyclerView.ViewHolder{
        TextView kostName, kostNum,kostStatus,nokamar,id;
        ImageView img;

        Button btnpesan;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            kostName=itemView.findViewById(R.id.name);
            kostNum=itemView.findViewById(R.id.harga);
            kostStatus=itemView.findViewById(R.id.status);
            img=itemView.findViewById(R.id.img);
            nokamar=itemView.findViewById(R.id.no_kamar);
            btnpesan =itemView.findViewById(R.id.btnPesan);
        }
    }
}
