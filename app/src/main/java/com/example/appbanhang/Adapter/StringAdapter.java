package com.example.appbanhang.Adapter;


import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhang.R;
import com.example.appbanhang.model.Product;
import com.example.appbanhang.model.ProductCategory;
import com.example.appbanhang.service.OnItemClickListenerString;
import com.squareup.picasso.Picasso;

import java.util.List;


public class StringAdapter extends RecyclerView.Adapter<StringAdapter.StringViewHolder> {
    private List<ProductCategory> str;
    private OnItemClickListenerString onItemClickListenerString;

    public void setData(List<ProductCategory> str,OnItemClickListenerString onItemClickListenerString){
        this.str = str;
        this.onItemClickListenerString = onItemClickListenerString;
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
        ProductCategory s = str.get(position);
        if(s == null){
            return;
        }
        holder.textview.setText(s.getTen_danh_muc());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListenerString.OnItemClickListenerString(s);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(str != null) return str.size();
        return 0;
    }

    public class StringViewHolder extends  RecyclerView.ViewHolder{
        private TextView textview;
        private CardView cardView;
        public StringViewHolder(@NonNull View itemView) {
            super(itemView);
            textview = itemView.findViewById(R.id.namelist);
            cardView = itemView.findViewById(R.id.ListMan);
        }
    }


}
