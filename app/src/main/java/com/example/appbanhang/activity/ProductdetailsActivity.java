package com.example.appbanhang.activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhang.Adapter.SizeAdapter;
import com.example.appbanhang.R;
import com.example.appbanhang.fragment.ListProductFragment;
import com.example.appbanhang.model.Parameter;
import com.example.appbanhang.model.Product;
import com.example.appbanhang.model.ProductCategory;
import com.example.appbanhang.model.ResponseData;
import com.example.appbanhang.service.API;
import com.example.appbanhang.service.CheckLogin;
import com.example.appbanhang.service.OnItemClickListenerString;
import com.example.appbanhang.service.OnItemClickParameter;
import com.example.appbanhang.service.SelectionEvent;
import com.example.appbanhang.service.SumEvent;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProductdetailsActivity extends AppCompatActivity {
     Dialog dialog;
    private Toolbar toolbar;
    private TextView txttensp;
    private TextView txtdaban;
    private TextView txtdanhgia;
    private TextView txtgiatien;
    private TextView txtmota;

    private ImageView imagesp;
    private int id;
    Product product;
    Button themgiohang;

    List<Parameter> parameterList;
    SizeAdapter sizeAdapter;
    EditText editTextQuantity;
    Button buttonMinus;
    Button buttonPlus;
    Button buttonBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productdetails);

        init();
        ActionBar();
        setView();
        setonClick();
    }

    private void setView() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            product = (Product) bundle.getSerializable("product");
            txttensp.setText("" + product.getTen_san_pham());
            Picasso.get().load(product.getHinh_anh()).into(imagesp);
            txtdaban.setText(product.getDa_ban() + " lần.");
            txtdanhgia.setText(product.getDanh_gia() + "/5.");
            txtgiatien.setText("Giá tiền: " + product.getGia_san_pham() + " đ.");
            txtmota.setText("Mô tả sản phẩm: \n\t" + product.getMo_ta());
        }
    }

    public void init() {
        toolbar = findViewById(R.id.toolbarchitiet);
        txtdaban = findViewById(R.id.dabanchitiet);
        txttensp = findViewById(R.id.tenchitiet);
        txtdanhgia = findViewById(R.id.danhgiachitiet);
        txtgiatien = findViewById(R.id.giachitiet);
        txtmota = findViewById(R.id.motachitiet);
        imagesp = findViewById(R.id.imagechitiet);
        themgiohang = findViewById(R.id.themgiohang);
        parameterList = new ArrayList<>();
        sizeAdapter = new SizeAdapter();
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {

        }
        actionBar.setDisplayHomeAsUpEnabled(true); // Hiển thị nút trở về
        actionBar.setHomeAsUpIndicator(R.drawable.back3); // Thay thế "R.drawable.ic_back_arrow" bằng icon của bạn
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
    }

    private void showBuyDialog() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.productselection);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttribute = window.getAttributes();
        windowAttribute.gravity = Gravity.CENTER;
        window.setAttributes(windowAttribute);

        editTextQuantity = dialog.findViewById(R.id.editTextQuantity);
        buttonMinus = dialog.findViewById(R.id.buttonMinus);
        buttonPlus = dialog.findViewById(R.id.buttonPlus);
        buttonBuy = dialog.findViewById(R.id.buttonBuy);
        RecyclerView recyclerView = dialog.findViewById(R.id.recyclerview4);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);



        // TODO: Lấy danh sách size từ database cho sản phẩm hiện tại
        API.api.getParameter(product.getId_san_pham()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Parameter>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull List<Parameter> parameters) {
                        parameterList = parameters;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        sizeAdapter.setData(parameterList, new OnItemClickParameter() {
                            @Override
                            public void OnItemClickListenerParameter(Parameter parameter) {

                            }
                        });
                        recyclerView.setAdapter(sizeAdapter);

                    }
                });


        editTextQuantity.setText("1");
        buttonBuy.setEnabled(false);
        editTextQuantity.setEnabled(false);
        buttonMinus.setEnabled(false);
        buttonPlus.setEnabled(false);



        dialog.show();
    }


    public void setonClick() {
        themgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBuyDialog();
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
    public void SelectEvent(SelectionEvent event) {
        if (event != null) {
            select();
        }
    }

    private void select() {
        if (SizeAdapter.selection != -1) {
            buttonBuy.setEnabled(true);
            editTextQuantity.setEnabled(true);
            buttonMinus.setEnabled(true);
            buttonPlus.setEnabled(true);
            int max1 = parameterList.get(SizeAdapter.selection).getCon_lai();
            if(Integer.parseInt(editTextQuantity.getText().toString()) > max1){
                editTextQuantity.setText(max1+"");
            }
            editTextQuantity.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    String inputText = editable.toString();
                    if (!inputText.isEmpty()) {
                        int inputValue = Integer.parseInt(inputText);
                        int max = parameterList.get(SizeAdapter.selection).getCon_lai();

                        if (inputValue > max) {
                            // Nếu giá trị nhập vào vượt quá giới hạn tối đa
                            // Đặt lại giá trị của EditText thành giá trị tối đa
                            editable.replace(0, editable.length(),  String.valueOf(max));
                        }
                    }
                }
            });
            buttonMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Integer.parseInt(editTextQuantity.getText().toString()) > 1) {
                        editTextQuantity.setText((Integer.parseInt(editTextQuantity.getText().toString())-1)+"");
                    }
                }
            });

            buttonPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int max = parameterList.get(SizeAdapter.selection).getCon_lai();
                    if (Integer.parseInt(editTextQuantity.getText().toString()) < max) {
                        editTextQuantity.setText((Integer.parseInt(editTextQuantity.getText().toString())+1)+"");
                    }
                }
            });
            buttonBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Parameter selectedSize =  parameterList.get(SizeAdapter.selection);
                int quantity = Integer.parseInt(editTextQuantity.getText().toString());
                    // TODO: Xử lý size và số lượng được chọn
                    API.api.updateItemCart(CheckLogin.UserID,product.getId_san_pham(),selectedSize.getId_thong_so(),quantity)
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
                                        Toast.makeText(getApplicationContext(), "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
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
                                    dialog.dismiss();
                                }
                            });

                }
            });
        } else {
            buttonBuy.setEnabled(false);
            editTextQuantity.setEnabled(false);
            buttonMinus.setEnabled(false);
            buttonPlus.setEnabled(false);
        }
    }

}