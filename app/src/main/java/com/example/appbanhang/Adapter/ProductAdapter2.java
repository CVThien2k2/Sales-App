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


public class ProductAdapter2 extends RecyclerView.Adapter<ProductAdapter2.ProductViewHolder> {
    private List<Product> products;
    private OnItemClickListenerProduct itemClickListener;
    public void setData(List<Product> products, OnItemClickListenerProduct itemClickListener){
        this.products = products;
        this.itemClickListener = itemClickListener;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product2,parent,false);
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
        holder.textviewGia.setText("Giá: "+product.getGia_san_pham()+ " đ - Lượt bán: "+product.getDa_ban()+" lượt");
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

    public class ProductViewHolder extends  RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView;
        private TextView textviewGia;
        private CardView cardView;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.anhsanpham2);
            textView = itemView.findViewById(R.id.tensanpham2);
            textviewGia = itemView.findViewById(R.id.giasanpham2);
            cardView = itemView.findViewById(R.id.cardview2);
        }
    }


}
