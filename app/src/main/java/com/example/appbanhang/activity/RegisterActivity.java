package com.example.appbanhang.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.appbanhang.R;
import com.example.appbanhang.model.ResponseData;
import com.example.appbanhang.service.API;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RegisterActivity extends AppCompatActivity {


    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText5;
    private EditText editText6;

    Toolbar toolbar;
    Button button;

    TextView textView;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        ActionBar();
        setOnClick();
    }

    public void init() {
        editText1 = findViewById(R.id.usernameregister);
        editText2 = findViewById(R.id.passregister);
        editText3 = findViewById(R.id.passregister2);
        editText4 = findViewById(R.id.hoten);
        editText5 = findViewById(R.id.emailregister);
        editText6 = findViewById(R.id.sodienthoai);

        textView1 = findViewById(R.id.thongbao1);
        textView2 = findViewById(R.id.thongbao2);
        textView3 = findViewById(R.id.thongbao3);
        textView4 = findViewById(R.id.thongbao4);
        textView5 = findViewById(R.id.thongbao5);
        textView6 = findViewById(R.id.thongbao6);

        button = findViewById(R.id.buttonregister);
        textView = findViewById(R.id.register3);

        toolbar = findViewById(R.id.toolbarregister);
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

    public boolean isValidCredentials(String str) {
        // Kiểm tra xem tên người dùng và mật khẩu có rỗng hay không
        if (str.isEmpty()) {
            return false;
        }

        // Kiểm tra xem tên người dùng và mật khẩu có chứa các ký tự không hợp lệ hay không
        String regex = "^[a-zA-Z0-9]+$"; // Chỉ cho phép ký tự chữ và số
        if (!str.matches(regex)) {
            return false;
        }
        return true;
    }

    public void setOnClick() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isValidCredentials(editText1.getText().toString().trim())) {
                    textView1.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            textView1.setVisibility(View.GONE);
                        }
                    }, 5000);

                }
                if (!isValidCredentials(editText2.getText().toString().trim())) {
                    textView2.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            textView2.setVisibility(View.GONE);
                        }
                    }, 5000);

                }
                if (!isValidCredentials(editText3.getText().toString().trim())) {
                    textView3.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            textView3.setVisibility(View.GONE);
                        }
                    }, 5000);

                }
                if (!isValidCredentials(editText4.getText().toString().trim())) {
                    textView4.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            textView4.setVisibility(View.GONE);
                        }
                    }, 5000);

                }
                if (!isValidCredentials(editText5.getText().toString().trim())) {
                    textView5.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            textView5.setVisibility(View.GONE);
                        }
                    }, 5000);

                }
                if (!isValidCredentials(editText6.getText().toString().trim())) {
                    textView6.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            textView6.setVisibility(View.GONE);
                        }
                    }, 5000);

                }
                if (isValidCredentials(editText2.getText().toString().trim()) && isValidCredentials(editText3.getText().toString().trim())) {
                    if (!editText2.getText().toString().trim().equals(editText3.getText().toString().trim())) {
                        textView3.setText("Nhập lại mật khẩu sai");
                        textView3.setVisibility(View.VISIBLE);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                textView3.setVisibility(View.GONE);
                                textView3.setText("Không hợp lệ, vui lòng nhập lại");
                            }
                        }, 5000);
                    }
                }  if (isValidCredentials(editText1.getText().toString().trim()) && isValidCredentials(editText2.getText().toString().trim()) && isValidCredentials(editText3.getText().toString().trim()) && isValidCredentials(editText4.getText().toString().trim()) && isValidCredentials(editText5.getText().toString().trim()) && isValidCredentials(editText6.getText().toString().trim()))
                    API.api.Register(editText1.getText().toString().trim(),
                                    editText2.getText().toString().trim(),
                                    editText3.getText().toString().trim(),
                                    editText4.getText().toString().trim(),
                                    editText5.getText().toString().trim(),
                                    editText6.getText().toString().trim())
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
                                    if (responseData1.getMessage().equals("Done")) {
                                        Toast.makeText(RegisterActivity.this, "Thành công!", Toast.LENGTH_SHORT).show();
                                    } else if (responseData1.getMessage().equals("phone")) {
                                        Toast.makeText(RegisterActivity.this, "Số điện thoại đã được dùng", Toast.LENGTH_SHORT).show();
                                    } else if (responseData1.getMessage().equals("email")) {
                                        Toast.makeText(RegisterActivity.this, "Email đã được dùng", Toast.LENGTH_SHORT).show();
                                    } else if (responseData1.getMessage().equals("username")) {
                                        Toast.makeText(RegisterActivity.this, "Tên tài khoản đã được dùng", Toast.LENGTH_SHORT).show();
                                    } else if (responseData1.getMessage().equals("error")) {
                                        Toast.makeText(RegisterActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                                    }
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


        });
    }
}