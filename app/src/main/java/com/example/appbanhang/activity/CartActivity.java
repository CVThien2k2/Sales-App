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
import android.widget.Toast;

import com.example.appbanhang.Adapter.CartAdapter;
import com.example.appbanhang.R;
import com.example.appbanhang.model.Product;
import com.example.appbanhang.service.API;
import com.example.appbanhang.service.OnItemClickListenerProduct;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    Toolbar toolbar;
    List<Product> products;
    CartAdapter cartAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        init();
        ActionBar();
        getCart();
    }

    private void getCart() {
        API.api.getAonam().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Product>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Product> product) {
                        products = product;
                        Toast.makeText(CartActivity.this, products.size()+"", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        cartAdapter.setData(products, new OnItemClickListenerProduct() {
                            @Override
                            public void onItemClickProduct(Product product) {

//                                Intent intent = new Intent(getApplication(), ProductdetailsActivity.class);
//
//                                Bundle bundle = new Bundle();
//                                bundle.putSerializable("product",product);
//
//                                intent.putExtras(bundle);
//                                startActivity(intent);


                            }
                        });
                        recyclerView.setAdapter(cartAdapter);
                        Toast.makeText(CartActivity.this, products.size()+"", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void init(){
        recyclerView = findViewById(R.id.recyclerviewcart);
        toolbar = findViewById(R.id.toolbargiohang);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, recyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        products = new ArrayList<>();
        cartAdapter = new CartAdapter();
    }
    private void ActionBar() {
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); // Hiển thị nút trở về
            actionBar.setHomeAsUpIndicator(R.drawable.back); // Thay thế "R.drawable.ic_back_arrow" bằng icon của bạn
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
    }
}