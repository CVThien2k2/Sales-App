package com.example.appbanhang.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appbanhang.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class SearchFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search, container, false);
        init();
        return view;
    }
    public void init(){
        tabLayout = view.findViewById(R.id.tablelayout);
        viewPager2 = view.findViewById(R.id.viewpager2);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this );
        viewPager2.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Man");
                    break;
                case 1:
                    tab.setText("Women");
                    break;
                case 2:
                    tab.setText("Kid");
                    break;
                case 3:
                    tab.setText("Kid2");
                    break;
            }
        }).attach();
    }

}
