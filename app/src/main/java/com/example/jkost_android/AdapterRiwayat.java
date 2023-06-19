package com.example.jkost_android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterRiwayat extends RecyclerView.Adapter<AdapterRiwayat.MyHolder> {

//    private final OnItemClickListener listener;
    Context context;
    List<ClassModel> arrayList;
    LayoutInflater layoutInflater;

//    , OnItemClickListener listener
    public AdapterRiwayat(Context context, ArrayList<ClassModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        layoutInflater=LayoutInflater.from(context);
    }

    public interface OnItemClickListener {
        void onItemClick(ClassModel item);
    }



    @NonNull
    @Override
    public AdapterRiwayat.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.item_file,parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRiwayat.MyHolder holder, int position) {
        ClassModel item = arrayList.get(position);

            holder.kostName.setText(arrayList.get(position).getNama_kost());
            holder.nokamar.setText(arrayList.get(position).getNo_kamar());
//            holder.kostNum.setText(arrayList.get(position).getHarga());
            holder.kostStatus.setText(arrayList.get(position).getStatus());


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

    public ClassModel getItem(int position) {
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
