package com.example.appbanhang.activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.appbanhang.Adapter.PaymetAdapter;
import com.example.appbanhang.R;
import com.example.appbanhang.model.Product;
import com.example.appbanhang.model.item_cart;
import com.example.appbanhang.service.API;
import com.example.appbanhang.service.OnItemClickListenerProduct;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PaymentActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView address;
    RecyclerView recyclerView;
    TextView tongthanhtoan;
    Button dathang;
    PaymetAdapter paymetAdapter = new PaymetAdapter();
    List<item_cart> item_carts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        init();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            item_carts = (List<item_cart>) bundle.getSerializable("itemCarts");
            paymetAdapter.setData(item_carts);
            recyclerView.setAdapter(paymetAdapter);
        }
        getCart(1);
//        ActionBar();
    }
    public void init(){
        toolbar = findViewById(R.id.toolbarinfo);
        address = findViewById(R.id.addressthanhtoan);
        recyclerView = findViewById(R.id.recyclerviewthanhtoan);
        tongthanhtoan = findViewById(R.id.tongthanhtoan);
        dathang = findViewById(R.id.dathang);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, recyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
    private void ActionBar() {
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); // Hiển thị nút trở về
            actionBar.setHomeAsUpIndicator(R.drawable.back3); // Thay thế "R.drawable.ic_back_arrow" bằng icon của bạn
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    private void getCart(int id) {
        API.api.getitemCart(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<item_cart>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<item_cart> cart) {
                        item_carts = cart;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        paymetAdapter.setData(item_carts);
                        recyclerView.setAdapter(paymetAdapter);
                    }
                });
    }
}