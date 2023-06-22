package com.example.appbanhang.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ViewFlipper;

import com.example.appbanhang.fragment.CheckLogin;
import com.example.appbanhang.fragment.ProfileFragment;
import com.example.appbanhang.fragment.NotifyFragment;
import com.example.appbanhang.R;
import com.example.appbanhang.fragment.SearchFragment;
import com.example.appbanhang.fragment.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new HomeFragment()).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new HomeFragment()).commit();
                        return true;
                    case R.id.search: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new SearchFragment()).commit();
                        return true;
                    }
                    case R.id.notify:
                        if (com.example.appbanhang.service.CheckLogin.Login == false) {
                            CheckLogin fragment = new CheckLogin();
                            Bundle bundle = new Bundle();
                            bundle.putString("myString", "Thông Báo");
                            fragment.setArguments(bundle);
                            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, fragment).commit();
                        } else {
                            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new NotifyFragment()).commit();
                        }
                        return true;

                    case R.id.me:
                        if (com.example.appbanhang.service.CheckLogin.Login == false) {
                            CheckLogin fragment = new CheckLogin();
                            Bundle bundle = new Bundle();
                            bundle.putString("myString", "Profile");
                            fragment.setArguments(bundle);
                            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, fragment).commit();
                        } else {
                            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new ProfileFragment()).commit();
                        }
                        return true;
                }

                return false;
            }
        });
    }


}