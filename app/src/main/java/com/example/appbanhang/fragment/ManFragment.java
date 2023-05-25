package com.example.appbanhang.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.appbanhang.Adapter.StringAdapter;
import com.example.appbanhang.R;

import java.util.ArrayList;
import java.util.List;

public class ManFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    List<String> str;
    StringAdapter stringAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_man, container, false);
        init();
        return view;
    }

    public void init() {

        recyclerView = view.findViewById(R.id.ListMan);
        stringAdapter = new StringAdapter();
        str = new ArrayList<>();

        str.add("Áo");
        str.add("Quần");
        str.add("Giày");
        str.add("Dép");
        Toast.makeText(this.getContext(), str.size()+"", Toast.LENGTH_SHORT).show();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), recyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        stringAdapter.setData(str);
        recyclerView.setAdapter(stringAdapter);

    }

   
}