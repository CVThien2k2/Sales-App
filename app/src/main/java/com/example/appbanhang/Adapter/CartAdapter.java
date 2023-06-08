package com.example.appbanhang.Adapter;


import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhang.R;
import com.example.appbanhang.model.Product;
import com.example.appbanhang.model.ShoppingCart;
import com.example.appbanhang.service.ImageClickListener;
import com.example.appbanhang.service.OnItemClickListenerCart;
import com.example.appbanhang.service.OnItemClickListenerProduct;
import com.example.appbanhang.service.SumEvent;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    public static List<ShoppingCart> Cart;
    DecimalFormat decimalFormat = new DecimalFormat("#,###"); // Mẫu định dạng số
    private OnItemClickListenerProduct itemClickListener;

    public void setData(List<ShoppingCart> Cart, OnItemClickListenerProduct itemClickListener){
        this.Cart = Cart;
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
        Product product = Cart.get(position).getProduct();
        int j = position;
        if(product == null){
            return;
        }

        Picasso.get().load(product.getHinh_anh()).into(holder.imageView);
        holder.textViewname.setText(product.getTen_san_pham());
        holder.textviewGia.setText("Thành tiền "+decimalFormat.format(product.getGia_san_pham() * Cart.get(j).getSo_luong_san_pham())+ "đ ");
        holder.textviewLuotban.setText("Lượt bán: "+product.getDa_ban()+" lượt.");
        holder.soluong.setText(Cart.get(j).getSo_luong_san_pham()+"");
        holder.checkBox.setChecked(Cart.get(j).isChecked());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Cart.get(j).setChecked(true);
                    EventBus.getDefault().postSticky(new SumEvent());
                } else {
                    Cart.get(j).setChecked(false);
                    EventBus.getDefault().postSticky(new SumEvent());
                }
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClickProduct(product);
            }
        });
        holder.setImageClickListener(new ImageClickListener() {
            @Override
            public void onImageClick(View view, int pos, int giatri) {
                if(giatri ==1 ){
                    if(Cart.get(j).getSo_luong_san_pham() ==1){
                    }
                    else{
                        Cart.get(j).setSo_luong_san_pham(Cart.get(j).getSo_luong_san_pham()-1);
                    }
                } else if(giatri ==2){
                    Cart.get(j).setSo_luong_san_pham(Cart.get(j).getSo_luong_san_pham()+1);
                }
                holder.textviewGia.setText("Thành tiền "+decimalFormat.format(product.getGia_san_pham() * Cart.get(j).getSo_luong_san_pham())+ "đ ");
                holder.soluong.setText(Cart.get(j).getSo_luong_san_pham()+"");
                EventBus.getDefault().postSticky(new SumEvent());
            }
        });
    }


    @Override
    public int getItemCount() {
        if(Cart != null) return Cart.size();
        return 0;
    }

    public void setAllCheckBoxesChecked(boolean b) {
        for (ShoppingCart cartItem : Cart) {
            cartItem.setChecked(b);
        }
        notifyDataSetChanged();
        EventBus.getDefault().postSticky(new SumEvent());
    }

    public class CartViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView textViewname;
        private TextView textviewGia;
        private TextView textviewLuotban;
        private TextView soluong;
        ImageButton subtract;
        ImageButton add;
        TextView quantity;
        ImageClickListener imageClickListener;
        CheckBox checkBox;
        public void setImageClickListener(ImageClickListener imageClickListener) {
            this.imageClickListener = imageClickListener;
        }

        private CardView cardView;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagecart);
            textViewname = itemView.findViewById(R.id.namecart);
            textviewGia = itemView.findViewById(R.id.giacart);
            cardView = itemView.findViewById(R.id.cart);
            textviewLuotban = itemView.findViewById(R.id.luotban);
            soluong = itemView.findViewById(R.id.soluong);
            subtract = itemView.findViewById(R.id.giam);
            add = itemView.findViewById(R.id.tang);
            quantity = itemView.findViewById(R.id.soluong);
            subtract.setOnClickListener(this);
            add.setOnClickListener(this);
            checkBox = itemView.findViewById(R.id.checkBox);
        }

        @Override
        public void onClick(View view) {
            if(view == subtract){
                imageClickListener.onImageClick(view,getAbsoluteAdapterPosition(),1);
                
            } else if (view == add) {
                imageClickListener.onImageClick(view,getAbsoluteAdapterPosition(),2);
            }
        }

    }


}
