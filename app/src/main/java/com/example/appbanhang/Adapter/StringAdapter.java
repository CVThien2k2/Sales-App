package com.example.appbanhang.Adapter;


import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhang.R;
import com.example.appbanhang.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;


public class StringAdapter extends RecyclerView.Adapter<StringAdapter.StringViewHolder> {
    private List<String> str;

    public void setData(List<String> str){
        this.str = str;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public StringViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.liststring,parent,false);
        return new StringViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StringViewHolder holder, int position) {
        String s = str.get(position);
        if(s == null){
            return;
        }
        holder.textview.setText(s);
    }

    @Override
    public int getItemCount() {
        if(str != null) return str.size();
        return 0;
    }

    public class StringViewHolder extends  RecyclerView.ViewHolder{
        private TextView textview;
        public StringViewHolder(@NonNull View itemView) {
            super(itemView);
            textview = itemView.findViewById(R.id.namelist);
        }
    }


}
