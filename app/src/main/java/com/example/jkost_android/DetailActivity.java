package com.example.jkost_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.jkost_android.ui.SliderAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class DetailActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    private int[] imageList = {R.drawable.rumah, R.drawable.rumah, R.drawable.rumah};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kos);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        SliderAdapter adapter = new SliderAdapter(this, imageList);
        viewPager.setAdapter(adapter);

        // Menambahkan indikator TabLayout
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> viewPager.setCurrentItem(position, true)
        ).attach();
    }
}