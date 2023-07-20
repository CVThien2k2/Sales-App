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
import android.widget.Toast;

import com.example.appbanhang.Adapter.CartAdapter;
import com.example.appbanhang.Adapter.PaymetAdapter;
import com.example.appbanhang.R;
import com.example.appbanhang.model.Parameter;
import com.example.appbanhang.model.Product;
import com.example.appbanhang.model.ResponseData;
import com.example.appbanhang.model.item_cart;
import com.example.appbanhang.service.API;
import com.example.appbanhang.service.CheckLogin;
import com.example.appbanhang.service.OnItemClickListenerProduct;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
    TextView tongthanhtoan1;
    TextView giatridonhang;
    TextView phivanchuyen;
    Button dathang;
    PaymetAdapter paymetAdapter = new PaymetAdapter();
    List<item_cart> item_carts = new ArrayList<>();
    double phivc = 18000;
    int count;
    DecimalFormat decimalFormat = new DecimalFormat("#,###");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        init();
        setClick();
        ActionBar();
    }

    public void init() {
        toolbar = findViewById(R.id.toolbarthanhtoan);
        address = findViewById(R.id.addressthanhtoan);
        recyclerView = findViewById(R.id.recyclerviewthanhtoan);
        tongthanhtoan = findViewById(R.id.tongthanhtoan);
        dathang = findViewById(R.id.dathang);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, recyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        giatridonhang = findViewById(R.id.giatridonhang);
        phivanchuyen = findViewById(R.id.phivanchuyen);
        tongthanhtoan1 = findViewById(R.id.tonggiatri);
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
        API.api.getItemPayment(id).subscribeOn(Schedulers.io())
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

    public void setClick() {
        address.setText("Địa chỉ giao hàng: " + CheckLogin.user.getDia_chi());
        String tien = getIntent().getStringExtra("tien");
        item_cart item = (item_cart) getIntent().getSerializableExtra("item");
        giatridonhang.setText(decimalFormat.format(Double.parseDouble(tien)));
        double tongtien = Double.parseDouble(tien) + phivc;
        phivanchuyen.setText(decimalFormat.format(phivc));
        tongthanhtoan1.setText(decimalFormat.format(tongtien) + "");
        tongthanhtoan.setText(decimalFormat.format(tongtien) + "đ");
        dathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date currentDate = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.setTimeZone(TimeZone.getTimeZone("GMT")); // Thiết lập vùng thời gian
                String formattedDate = dateFormat.format(currentDate);

                count = 0;
                for(int i=0; i< item_carts.size();i++){
                    int finalI = i;
                    API.api.checkItemPayment(item_carts.get(i).getParameter().getId_thong_so(),item_carts.get(i).getSo_luong_san_pham())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<ResponseData>() {
                                String res;

                                @Override
                                public void onSubscribe(@NonNull Disposable d) {
                                    // Xử lý khi đăng ký
                                }

                                @Override
                                public void onNext(@NonNull ResponseData responseData1) {
                                    // Xử lý khi nhận được dữ liệu phản hồi
                                    res = responseData1.getMessage();
                                }

                                @Override
                                public void onError(@NonNull Throwable e) {
                                    // Xử lý khi xảy ra lỗi
                                }

                                @Override
                                public void onComplete() {
                                   if(res.equals("Error")){
                                       count++;
                                   }
                                    if(finalI ==item_carts.size()-1){
                                        if(count==0)
                                            API.api.createOrder(CheckLogin.UserID, formattedDate, tongtien, formattedDate, "Chờ xác nhận")
                                                    .subscribeOn(Schedulers.io())
                                                    .observeOn(AndroidSchedulers.mainThread())
                                                    .subscribe(new Observer<ResponseData>() {
                                                        String idcart;

                                                        @Override
                                                        public void onSubscribe(@NonNull Disposable d) {
                                                            // Xử lý khi đăng ký
                                                        }

                                                        @Override
                                                        public void onNext(@NonNull ResponseData responseData1) {
                                                            // Xử lý khi nhận được dữ liệu phản hồi
                                                            idcart = responseData1.getMessage();
                                                        }

                                                        @Override
                                                        public void onError(@NonNull Throwable e) {
                                                            // Xử lý khi xảy ra lỗi
                                                        }

                                                        @Override
                                                        public void onComplete() {
                                                            // Xử lý khi hoàn thành
                                                            createItem(idcart);
                                                        }
                                                    });
                                        else Toast.makeText(PaymentActivity.this, "Có sản phẩm đã hết hàng", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }


            }
        });
        if (item != null) {
            item_carts.add(item);
            paymetAdapter.setData(item_carts);
            recyclerView.setAdapter(paymetAdapter);
        }
        else{
            getCart(CheckLogin.UserID);
        }
    }

    public void createItem(String id) {
        for (item_cart item : item_carts) {
            Product product = item.getProduct();
            Parameter parameter = item.getParameter();
            API.api.createItemOrder(Integer.parseInt(id), product.getId_san_pham(), parameter.getId_thong_so(), item.getSo_luong_san_pham())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ResponseData>() {
                        String idcart;

                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            // Xử lý khi đăng ký
                        }

                        @Override
                        public void onNext(@NonNull ResponseData responseData1) {
                            // Xử lý khi nhận được dữ liệu phản hồi
                            idcart = responseData1.getMessage();
                            Toast.makeText(PaymentActivity.this, idcart + ", Quay lại trang chủ để mua sắm tiếp", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            // Xử lý khi xảy ra lỗi
                        }

                        @Override
                        public void onComplete() {
                            // Xử lý khi hoàn thành
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                    });
            API.api.deleteItemCart(item.getId_item_gio_hang())
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
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            // Xử lý khi xảy ra lỗi
                        }

                        @Override
                        public void onComplete() {
                            // Xử lý khi hoàn thành
                        }
                    });
            API.api.setquantity(parameter.getId_thong_so(),parameter.getCon_lai()-item.getSo_luong_san_pham())
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
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            // Xử lý khi xảy ra lỗi
                        }

                        @Override
                        public void onComplete() {
                            // Xử lý khi hoàn thành
                        }
                    });
        }
    }
}