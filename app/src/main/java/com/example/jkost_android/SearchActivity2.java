package com.example.jkost_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class SearchActivity2 extends AppCompatActivity {

    SearchView searchView;
    RecyclerView recyclerView;
    ArrayList<ModelClass> arrayList=new ArrayList<>();
    ArrayList<ModelClass> searchList;
    String[] kostList=new String[]{"Kost Cantik, Kost Kafka, Kost Putra Oyi, Kost Orchid, Kost Putri Cinderella"};

    String[] kostNum=new String[]{"Kost 1", "Kost 2", "Kost 3", "Kost 4", "Kost 5"};

    int[] imgList=new int[]{R.drawable.kost1,R.drawable.kost2,R.drawable.kost3,R.drawable.kost4,R.drawable.kost5,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);
        recyclerView=findViewById(R.id.recycleView);
        searchView=findViewById(R.id.searchView);

        for (int i = 0; i<kostList.length ; i++) {
            ModelClass modelClass = new ModelClass(
                    "Namakost",
                    "status",
                    "no",
                    "hargaKamar",
                    "deskripsi",
                    "alamat",
                    "imgpertama",
                    "imgkedua",
                    "imgketiga",
                    "imgkeempat"
            );
            modelClass.setKostName(kostList[i]);
            modelClass.setKostHarga(kostNum[i]);
            modelClass.setImg(imgList[i]);
            arrayList.add(modelClass);
        }

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(SearchActivity2.this);
        recyclerView.setLayoutManager(layoutManager);

        KostAdapter kostAdapter=new KostAdapter(SearchActivity2.this,arrayList);
        recyclerView.setAdapter(kostAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchList=new ArrayList<>();

                if (query.length()>0){
                    for (int i = 0; i <arrayList.size() ; i++) {
                        if (arrayList.get(i).getKostName().toUpperCase().contains(query.toUpperCase())){

                            ModelClass modelClass = new ModelClass(
                                    "Namakost",
                                    "status",
                                    "no",
                                    "hargaKamar",
                                    "deskripsi",
                                    "alamat",
                                    "imgpertama",
                                    "imgkedua",
                                    "imgketiga",
                                    "imgkeempat"
                            );
                            modelClass.setKostName(arrayList.get(i).getKostName());
                            modelClass.setKostHarga(arrayList.get(i).getKostHarga());
                            modelClass.setImg(Integer.parseInt(arrayList.get(i).getImg()));
                            searchList.add(modelClass);
                        }
                    }
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(SearchActivity2.this);
                    recyclerView.setLayoutManager(layoutManager);

                    KostAdapter kostAdapter=new KostAdapter(SearchActivity2.this,arrayList);
                    recyclerView.setAdapter(kostAdapter);

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}