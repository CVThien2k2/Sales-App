package com.example.appbanhang.fragment;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.appbanhang.R;
import com.example.appbanhang.service.CheckConnection;

public class NotifyFragment extends Fragment {


    View view;
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_notify, container, false);
        if (CheckConnection.isConnected(getActivity().getApplicationContext())) {
            init();
            ActionBar();
        } else {
            Toast.makeText((getActivity().getApplicationContext()), "Vui lòng kiểm tra kết nối Internet", Toast.LENGTH_LONG).show();
        }
        return view;
    }

    private void init() {
        toolbar = view.findViewById(R.id.notifytoolbar);
    }
    private void ActionBar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); // Hiển thị nút trở về
            actionBar.setHomeAsUpIndicator(R.drawable.back3); // Thay thế "R.drawable.ic_back_arrow" bằng icon của bạn
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }
}