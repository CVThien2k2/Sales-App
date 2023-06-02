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
import com.example.appbanhang.service.OnItemClickListenerProduct;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<Product> products;

    private OnItemClickListenerProduct itemClickListener;

    public void setData(List<Product> products, OnItemClickListenerProduct itemClickListener){
        this.products = products;
        this.itemClickListener =itemClickListener;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = products.get(position);
        if(product == null){
            return;
        }
        Picasso.get().load(product.getHinh_anh()).into(holder.imageView);
        holder.textViewname.setText(product.getTen_san_pham());
        holder.textviewGia.setText("Giá: "+product.getGia_san_pham()+ "đ - Lượt bán: "+product.getDa_ban()+" lượt.");
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClickProduct(product);
            }
        });
    }


    @Override
    public int getItemCount() {
        if(products != null) return products.size();
        return 0;
    }

    public class CartViewHolder extends  RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textViewname;
        private TextView textviewGia;
        private TextView textviewLuotban;

        private CardView cardView;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagecart);
            textViewname = itemView.findViewById(R.id.namecart);
            textviewGia = itemView.findViewById(R.id.giacart);
            cardView = itemView.findViewById(R.id.cart);
        }
    }


}
