package com.example.appbanhang.Adapter;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhang.R;
import com.example.appbanhang.model.Product;
import com.example.appbanhang.model.ProductOrder;
import com.example.appbanhang.model.item_cart;
import com.example.appbanhang.model.item_order;
import com.example.appbanhang.service.API;
import com.example.appbanhang.service.OnItemClickListenerProduct;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ListOrderAdapter extends  RecyclerView.Adapter<ListOrderAdapter.ListOrderHolder>{
    private Context mcontext;
    private List<ProductOrder> productOrders;
    private OnItemClickListenerProduct itemClickListener;



    public ListOrderAdapter(Context mcontext) {
        this.mcontext = mcontext;
    }
    public void setData(List<ProductOrder> productOrders1, OnItemClickListenerProduct itemClickListener){
        this.productOrders = productOrders1;
        this.itemClickListener = itemClickListener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListOrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order,parent,false);
        return new ListOrderHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListOrderHolder holder, int position) {
        ProductOrder productOrder = productOrders.get(position);
        if (productOrder == null) return;
        holder.idCart.setText("Đơn hàng mã: "+ productOrder.getId_don_hang());
        holder.tonggia.setText(productOrder.getGia()+"");
        if(productOrder.getTrang_thai_don_hang().equals("Chờ xác nhận")){
            holder.btmualai.setText("Hủy đơn hàng");
        }
        else if(productOrder.getTrang_thai_don_hang().equals("Đã giao hàng")){
            holder.btmualai.setText("Mua lại");
        }
        else {
            holder.btmualai.setVisibility(View.GONE);
        }

        API.api.getItemOrder(productOrder.getId_don_hang()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<item_order>>() {
                    private List<item_order> list;

                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<item_order> item_order) {
                        list = item_order;
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        item_order_Adapter item_order_adapter = new item_order_Adapter();
                        item_order_adapter.setData(list, new OnItemClickListenerProduct() {
                            @Override
                            public void onItemClickProduct(Product product) {
                                itemClickListener.onItemClickProduct(product);
                            }
                        });
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mcontext,RecyclerView.VERTICAL,false);
                        holder.recyclerView.setLayoutManager(linearLayoutManager);
                        holder.recyclerView.setAdapter(item_order_adapter);
                    }
                });


    }

    @Override
    public int getItemCount() {
        if(productOrders != null) return productOrders.size();
        return 0;
    }

    public class ListOrderHolder extends RecyclerView.ViewHolder{
        private TextView idCart;
        private TextView tonggia;
        private RecyclerView recyclerView;
        private Button btmualai;
        public ListOrderHolder(@NonNull View itemView) {
            super(itemView);
            idCart = itemView.findViewById(R.id.idcart);
            recyclerView = itemView.findViewById(R.id.recyclerviewdonhang);
            tonggia = itemView.findViewById(R.id.tongdongia);
            btmualai = itemView.findViewById(R.id.btmualai);

        }
    }

}
