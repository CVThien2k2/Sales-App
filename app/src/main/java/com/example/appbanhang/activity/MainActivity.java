package com.example.appbanhang.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ViewFlipper;

import com.example.appbanhang.fragment.MeFragment;
import com.example.appbanhang.fragment.NotifyFragment;
import com.example.appbanhang.R;
import com.example.appbanhang.fragment.SearchFragment;
import com.example.appbanhang.fragment.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ViewFlipper viewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new HomeFragment()).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new HomeFragment()).commit();
                        return true;
                    case R.id.search:
                    {
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new SearchFragment()).commit();
                    }
                    case R.id.notify:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new NotifyFragment()).commit();
                        return true;
                    case R.id.me:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new MeFragment()).commit();
                        return true;
                }

                return false;
            }
        });
    }


}