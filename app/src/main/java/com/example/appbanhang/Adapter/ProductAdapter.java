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

import java.text.DecimalFormat;
import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> products;
    private OnItemClickListenerProduct itemClickListener;
    DecimalFormat decimalFormat = new DecimalFormat("#,###"); // Mẫu định dạng số
    public void setData(List<Product> products, OnItemClickListenerProduct itemClickListener){
        this.products = products;
        this.itemClickListener =itemClickListener;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product,parent,false);
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
        holder.textviewGia.setText("Giá: "+decimalFormat.format(product.getGia_san_pham())+ " đ");
        holder.textviewLuotban.setText("Lượt bán: "+product.getDa_ban()+" lượt");

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
        private TextView textviewLuotban;

        private CardView cardView;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.anhsanpham);
            textView = itemView.findViewById(R.id.tensanpham);
            textviewGia = itemView.findViewById(R.id.giasanpham);
            textviewLuotban = itemView.findViewById(R.id.luotban);
            cardView = itemView.findViewById(R.id.product1);
        }
    }


}
