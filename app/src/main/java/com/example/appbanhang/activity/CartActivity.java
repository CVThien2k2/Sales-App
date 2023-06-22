package com.example.appbanhang.activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhang.Adapter.CartAdapter;
import com.example.appbanhang.R;
import com.example.appbanhang.fragment.NotifyFragment;
import com.example.appbanhang.model.Product;
import com.example.appbanhang.model.ResponseData;
import com.example.appbanhang.model.item_cart;
import com.example.appbanhang.service.API;
import com.example.appbanhang.service.CheckLogin;
import com.example.appbanhang.service.DeleteEvent;
import com.example.appbanhang.service.OnItemClickListenerCart;
import com.example.appbanhang.service.OnItemClickListenerProduct;
import com.example.appbanhang.service.SumEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
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
    List<item_cart> item_carts;
    CartAdapter cartAdapter;
    List<Product> products;
    ImageButton subtract;
    ImageButton add;
    TextView tvtongtien;
    TextView quantity;
    CheckBox checkBox;
    TextView empty;
    Button btmuahang;
    double sum = 0;
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
                        if (item_carts.isEmpty()) {
                            empty.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        } else {
                            empty.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }

                        cartAdapter.setData(item_carts, new OnItemClickListenerProduct() {
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
        item_carts = new ArrayList<>();
        subtract = findViewById(R.id.giam);
        add = findViewById(R.id.tang);
        tvtongtien = findViewById(R.id.tongtien);
        quantity = findViewById(R.id.soluong);
        checkBox = findViewById(R.id.checkBoxall);
        empty = findViewById(R.id.emptyTextView3);
        btmuahang = findViewById(R.id.muahang);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        // Thực hiện các hành động reset ở đây
        getCart(CheckLogin.UserID);
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
        btmuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                intent.putExtra("tien", String.valueOf(sum));
                startActivity(intent);
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
        sum = 0;
        for (item_cart x :
                CartAdapter.item_carts) {
            if (x.getTrang_thai() == 1)
                sum += x.getSo_luong_san_pham() * x.getProduct().getGia_san_pham();
        }
        tvtongtien.setText(decimalFormat.format(sum) + " đ.");

    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void eventdelete(DeleteEvent event) {
        if (event != null && CartAdapter.delete != -1 ) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Xác nhận xóa");
            builder.setMessage("Bạn có muốn xóa sản phẩm khỏi giỏ hàng?");

            // Thiết lập nút xác nhận
            builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Thực hiện xóa sản phẩm
                    API.api.deleteItemCart(CartAdapter.item_carts.get(CartAdapter.delete).getId_item_gio_hang())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<ResponseData>() {
                                @Override
                                public void onSubscribe(@NonNull Disposable d) {
                                    // Xử lý khi đăng ký
                                }

                                @Override
                                public void onNext(@NonNull ResponseData responseData1) {
                                    // Xử lý khi nhận được dữ liệu phản hồi
                                    if (responseData1.getMessage().equals("Success")) {
                                        Toast.makeText(getApplicationContext(), "Đã xóa", Toast.LENGTH_SHORT).show();
                                    } else if (responseData1.getMessage().equals("Error")) {
                                        Toast.makeText(getApplicationContext(), "Lỗi", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onError(@NonNull Throwable e) {
                                    // Xử lý khi xảy ra lỗi
                                }

                                @Override
                                public void onComplete() {
                                    // Xử lý khi hoàn thành
                                    CartAdapter.delete = -1;
                                    getCart(CheckLogin.UserID);
                                }
                            });
                    dialog.dismiss();

                }
            });

            // Thiết lập nút hủy
            builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Đóng hộp thoại và không thực hiện xóa sản phẩm
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Đã hủy xóa sản phẩm", Toast.LENGTH_SHORT).show();
                }
            });

            // Hiển thị hộp thoại
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }


}
