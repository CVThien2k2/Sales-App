package com.example.appbanhang.Adapter;


import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhang.R;
import com.example.appbanhang.activity.ProductdetailsActivity;
import com.example.appbanhang.model.Parameter;
import com.example.appbanhang.model.Product;
import com.example.appbanhang.model.ResponseData;
import com.example.appbanhang.model.item_cart;
import com.example.appbanhang.service.API;
import com.example.appbanhang.service.CallBackClass;
import com.example.appbanhang.service.DeleteEvent;
import com.example.appbanhang.service.ImageClickListener;
import com.example.appbanhang.service.OnItemClickListenerCart;
import com.example.appbanhang.service.OnItemClickListenerProduct;
import com.example.appbanhang.service.SumEvent;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    public static List<item_cart> item_carts;
    DecimalFormat decimalFormat = new DecimalFormat("#,###"); // Mẫu định dạng số
    private OnItemClickListenerProduct itemClickListener;
    private Activity mActivity;
    public static int delete = -1;
    private CallBackClass callback;

    public CartAdapter() {
    }

    public void setData(List<item_cart> Cart, OnItemClickListenerProduct itemClickListener, Activity activity,CallBackClass callback) {
        this.callback = callback;
        this.item_carts = Cart;
        this.mActivity = activity;
        this.itemClickListener = itemClickListener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        item_cart itemCart = item_carts.get(position);
        Product product = itemCart.getProduct();
        Parameter parameter = itemCart.getParameter();

        int j = position;

        Picasso.get().load(product.getHinh_anh()).into(holder.imageView);
        holder.textViewname.setText(product.getTen_san_pham());
        holder.textviewGia.setText("Tổng giá: " + decimalFormat.format(product.getGia_san_pham() * itemCart.getSo_luong_san_pham()) + "đ ");
        holder.textviewLuotban.setText("Size : " + parameter.getKich_thuoc() );
        holder.soluong.setText(itemCart.getSo_luong_san_pham() + "");
        if (itemCart.getTrang_thai() == 1) {
            holder.checkBox.setChecked(true);
        } else {
            holder.checkBox.setChecked(false);
        }

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    setDBclick(itemCart.getId_item_gio_hang(), itemCart.getSo_luong_san_pham() , 1);
                    itemCart.setTrang_thai(1);
                    EventBus.getDefault().postSticky(new SumEvent());
                } else {
                    setDBclick(itemCart.getId_item_gio_hang(), itemCart.getSo_luong_san_pham() , 0);
                    itemCart.setTrang_thai(0);
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
                if (giatri == 1) {
                    if (itemCart.getSo_luong_san_pham() == 1) {
                        delete = j;
                        EventBus.getDefault().postSticky(new DeleteEvent());
                    } else {
                        setDBclick(itemCart.getId_item_gio_hang(), itemCart.getSo_luong_san_pham() - 1, itemCart.getTrang_thai());
                        itemCart.setSo_luong_san_pham(itemCart.getSo_luong_san_pham() - 1);
                    }
                } else if (giatri == 2) {
                    if (itemCart.getSo_luong_san_pham() < parameter.getCon_lai()) {
                        setDBclick(itemCart.getId_item_gio_hang(),itemCart.getSo_luong_san_pham() +1 ,itemCart.getTrang_thai());
                        itemCart.setSo_luong_san_pham(itemCart.getSo_luong_san_pham() + 1);
                    }
                    else{
                        Toast.makeText(mActivity.getApplicationContext(), "Sản phẩm này chỉ còn "+ parameter.getCon_lai()+" sản phẩm", Toast.LENGTH_SHORT).show();
                    }
                }
                holder.textviewGia.setText("Tổng giá: " + decimalFormat.format(product.getGia_san_pham() * itemCart.getSo_luong_san_pham()) + "đ ");
                holder.soluong.setText(itemCart.getSo_luong_san_pham() + "");
                EventBus.getDefault().postSticky(new SumEvent());
            }
        });
    }

    public void setDBclick(int id, int count, int st) {
        API.api.setItemCart(id, count, st)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData>() {
                    String res;
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                        // Xử lý khi đăng ký
                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull ResponseData responseData1) {
                        // Xử lý khi nhận được dữ liệu phản hồi
                    res = responseData1.getMessage();
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        // Xử lý khi xảy ra lỗi
                    }

                    @Override
                    public void onComplete() {
                        // Xử lý khi hoàn thành
                        if(res.equals("Error")){
                            callback.onCallback();
                            Toast.makeText(mActivity.getApplicationContext(), "Số lượng sản phẩm đã thay đổi, đã cập nhật lại", Toast.LENGTH_SHORT).show();
                        }
                        
                    }
                });

    }


    @Override
    public int getItemCount() {
        if (item_carts != null) return item_carts.size();
        return 0;
    }

    public void setAllCheckBoxesChecked(boolean b) {
        if (b == true) {
            for (item_cart cartItem : item_carts) {
                setDBclick(cartItem.getId_item_gio_hang(), cartItem.getSo_luong_san_pham() , 1);
                cartItem.setTrang_thai(1);
            }
        } else if (b == false) {
            for (item_cart cartItem : item_carts) {
                setDBclick(cartItem.getId_item_gio_hang(), cartItem.getSo_luong_san_pham() , 0);
                cartItem.setTrang_thai(0);
            }

        }
        notifyDataSetChanged();
        EventBus.getDefault().postSticky(new SumEvent());
    }

    public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
            if (view == subtract) {
                imageClickListener.onImageClick(view, getAbsoluteAdapterPosition(), 1);

            } else if (view == add) {
                imageClickListener.onImageClick(view, getAbsoluteAdapterPosition(), 2);
            }
        }


    }


}
