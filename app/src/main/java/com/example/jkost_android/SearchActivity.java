package com.example.jkost_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.search.SearchView;

public class SearchActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
////        getMenuInflater().inflate(R.menu.menu,menu);
////
////        MenuItem menuItem = menu.findItem(R.id.action_search);
////        SearchView searchView = (SearchView) menuItem.getActionView();
////        searchView.setQueryHint("Nama kost");
////
////        searchView.setOnQueryTextListener(new SearchView.onQueryTextListener(){
////
////        });
////        return super.onCreateOptionsMenu(menu);
////    }
//}