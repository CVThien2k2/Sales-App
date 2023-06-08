package com.example.appbanhang.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.appbanhang.R;
import com.example.appbanhang.activity.LoginActivity;
import com.example.appbanhang.activity.RegisterActivity;

public class CheckLogin extends Fragment {

    private View view;

    private Button login;
    private Button register;
    Toolbar toolbar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_check_login, container, false);

        init();
        setOnClick();

        return view;

    }
    private void ActionBar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); // Hiển thị nút trở về
            actionBar.setHomeAsUpIndicator(R.drawable.back); // Thay thế "R.drawable.ic_back_arrow" bằng icon của bạn
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void setOnClick() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);

//                Bundle bundle = new Bundle();
//                bundle.putSerializable("product", product);
//
//                intent.putExtras(bundle);
                startActivity(intent);
                Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    


    private void init() {
        login = view.findViewById(R.id.login);
        register = view.findViewById(R.id.register);
        toolbar = view.findViewById(R.id.toolbarcheck);
    }
}