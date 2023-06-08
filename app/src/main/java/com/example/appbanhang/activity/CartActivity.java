package com.example.appbanhang.activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhang.Adapter.CartAdapter;
import com.example.appbanhang.R;
import com.example.appbanhang.model.Product;
import com.example.appbanhang.model.ShoppingCart;
import com.example.appbanhang.service.API;
import com.example.appbanhang.service.CheckLogin;
import com.example.appbanhang.service.OnItemClickListenerCart;
import com.example.appbanhang.service.OnItemClickListenerProduct;
import com.example.appbanhang.service.SumEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
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
    List<ShoppingCart> Cart;
    CartAdapter cartAdapter;
    List<Product> products;
    ImageButton subtract;
    ImageButton add;
    TextView tvtongtien;
    TextView quantity;
    CheckBox checkBox;
    TextView empty;
    DecimalFormat decimalFormat = new DecimalFormat("#,###"); // Mẫu định dạng số
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        init();
        ActionBar();
        getCart(CheckLogin.UserID);
    }

    private void getCart(int id) {
        API.api.getCart(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ShoppingCart>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<ShoppingCart> cart) {
                        Cart = cart;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        if (Cart.isEmpty()) {
                            empty.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        } else {
                            empty.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);}

                        cartAdapter.setData(Cart, new OnItemClickListenerProduct() {
                            @Override
                            public void onItemClickProduct(Product product) {
                                Intent intent = new Intent(getApplication(), ProductdetailsActivity.class);

                                Bundle bundle = new Bundle();
                                bundle.putSerializable("product", product);

                                intent.putExtras(bundle);
                                startActivity(intent);

                            }

                        });
                        recyclerView.setAdapter(cartAdapter);
                        Sum();
                    }
                });
    }

    public void init() {
        recyclerView = findViewById(R.id.recyclerviewcart);
        toolbar = findViewById(R.id.toolbargiohang);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, recyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        products = new ArrayList<>();
        cartAdapter = new CartAdapter();
        Cart = new ArrayList<>();
        subtract = findViewById(R.id.giam);
        add = findViewById(R.id.tang);
        tvtongtien = findViewById(R.id.tongtien);
        quantity = findViewById(R.id.soluong);
        checkBox = findViewById(R.id.checkBoxall);
        empty = findViewById(R.id.emptyTextView3);
    }

    private void ActionBar() {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    cartAdapter.setAllCheckBoxesChecked(b);
            }
        });
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
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void eventSum(SumEvent event) {
        if (event != null) {
            Sum();
        }
    }

    @SuppressLint("SuspiciousIndentation")
    public void Sum() {
        double sum = 0;
        for (ShoppingCart x :
                CartAdapter.Cart) {
            if(x.isChecked())
            sum += x.getSo_luong_san_pham() * x.getProduct().getGia_san_pham();
        }
        tvtongtien.setText(decimalFormat.format(sum)+" đ.");
        
    }
}