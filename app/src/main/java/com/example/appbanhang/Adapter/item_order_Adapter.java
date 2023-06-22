package com.example.appbanhang.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhang.R;
import com.example.appbanhang.model.Parameter;
import com.example.appbanhang.model.Product;
import com.example.appbanhang.model.item_cart;
import com.example.appbanhang.model.item_order;
import com.example.appbanhang.service.OnItemClickListenerProduct;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class item_order_Adapter extends RecyclerView.Adapter<com.example.appbanhang.Adapter.item_order_Adapter.itemOrderViewHolder> {
    public List<item_order> item_orders;
    private OnItemClickListenerProduct itemClickListener;
    DecimalFormat decimalFormat = new DecimalFormat("#,###"); // Mẫu định dạng số

    public void setData(List<item_order> Cart, OnItemClickListenerProduct itemClickListener) {
        this.item_orders = Cart;
        this.itemClickListener = itemClickListener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public com.example.appbanhang.Adapter.item_order_Adapter.itemOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment, parent, false);
        return new com.example.appbanhang.Adapter.item_order_Adapter.itemOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.appbanhang.Adapter.item_order_Adapter.itemOrderViewHolder holder, int position) {
        item_order itemCart = item_orders.get(position);
        Product product = itemCart.getProduct();
        Parameter parameter = itemCart.getParameter();

        int j = position;

        Picasso.get().load(product.getHinh_anh()).into(holder.imageView);
        holder.textViewname.setText(product.getTen_san_pham());
        holder.textviewGia.setText("Thành tiền " + decimalFormat.format(product.getGia_san_pham() * itemCart.getSo_luong_san_pham()) + "đ ");
        holder.soluong.setText(itemCart.getSo_luong_san_pham() + "");
        holder.size.setText(parameter.getKich_thuoc());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClickProduct(product);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (item_orders != null) return item_orders.size();
        return 0;
    }

    public class itemOrderViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textViewname;
        private TextView textviewGia;
        private TextView size;
        private TextView soluong;



        public itemOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagecart2);
            textViewname = itemView.findViewById(R.id.namecart2);
            textviewGia = itemView.findViewById(R.id.giacart2);
            soluong = itemView.findViewById(R.id.soluong2);
            size = itemView.findViewById(R.id.sizeitem);

        }

    }
}

