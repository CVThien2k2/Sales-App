package com.example.appbanhang.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.example.appbanhang.Adapter.ViewPagerAdapterHistory;
import com.example.appbanhang.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HistoryActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        init();
        ActionBar();
    }

    public void init() {
        tabLayout = findViewById(R.id.history_tablelayout);
        viewPager2 = findViewById(R.id.history_viewpager2);

        ViewPagerAdapterHistory viewPagerAdapter = new ViewPagerAdapterHistory(this);
        viewPager2.setAdapter(viewPagerAdapter);
        toolbar = findViewById(R.id.toolbarhistory);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Chờ xác nhận");
                    break;
                case 1:
                    tab.setText("Đang vận chuyển");
                    break;
                case 2:
                    tab.setText("Đã hủy hàng");
                    break;
                case 3:
                    tab.setText("Đã Giao hàng");
                    break;
            }
        }).attach();
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); // Hiển thị nút trở về
            actionBar.setHomeAsUpIndicator(R.drawable.back3); // Thay thế "R.drawable.ic_back_arrow" bằng icon của bạn
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
    }
}