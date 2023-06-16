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
import com.example.appbanhang.model.Parameter;
import com.example.appbanhang.model.Product;
import com.example.appbanhang.model.ResponseData;
import com.example.appbanhang.model.item_cart;
import com.example.appbanhang.service.API;
import com.example.appbanhang.service.ImageClickListener;
import com.example.appbanhang.service.OnItemClickListenerProduct;
import com.example.appbanhang.service.SumEvent;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PaymetAdapter extends RecyclerView.Adapter<PaymetAdapter.PaymentViewHolder> {
    public  List<item_cart> item_carts;
    DecimalFormat decimalFormat = new DecimalFormat("#,###"); // Mẫu định dạng số

    public  void setData(List<item_cart> Cart) {
        this.item_carts = Cart;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PaymetAdapter.PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment, parent, false);
        return new PaymetAdapter.PaymentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymetAdapter.PaymentViewHolder holder, int position) {
        item_cart itemCart = item_carts.get(position);
        Product product = itemCart.getProduct();
        Parameter parameter = itemCart.getParameter();

        int j = position;

        Picasso.get().load(product.getHinh_anh()).into(holder.imageView);
        holder.textViewname.setText(product.getTen_san_pham());
        holder.textviewGia.setText("Thành tiền " + decimalFormat.format(product.getGia_san_pham() * itemCart.getSo_luong_san_pham()) + "đ ");
        holder.soluong.setText(itemCart.getSo_luong_san_pham() + "");
    }


    @Override
    public int getItemCount() {
        if (item_carts != null) return item_carts.size();
        return 0;
    }

    public class PaymentViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textViewname;
        private TextView textviewGia;
        private TextView textviewLuotban;
        private TextView soluong;



        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagecart2);
            textViewname = itemView.findViewById(R.id.namecart2);
            textviewGia = itemView.findViewById(R.id.giacart2);
            soluong = itemView.findViewById(R.id.soluong2);
        }

    }
}
