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


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> products;

    public void setData(List<Product> products){
        this.products = products;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_sp_ban_chay,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        if(product == null){
            return;
        }
        Picasso.get().load(product.getHinh_anh()).into(holder.imageView);
        holder.textView.setText(product.getTen_san_pham());
        holder.textviewGia.setText("Giá: "+product.getGia_san_pham());
    }

    @Override
    public int getItemCount() {
        if(products != null) return products.size();
        return 0;
    }

    public class ProductViewHolder extends  RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView;
        private TextView textviewGia;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.anhsanpham);
            textView = itemView.findViewById(R.id.tensanpham);
            textviewGia = itemView.findViewById(R.id.giasanpham);
        }
    }


}
