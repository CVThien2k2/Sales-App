package com.example.appbanhang.activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.appbanhang.R;
import com.example.appbanhang.model.ResponseData;
import com.example.appbanhang.model.User;
import com.example.appbanhang.service.API;
import com.example.appbanhang.service.CheckLogin;

import java.util.ArrayList;
import java.util.List;

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
    private EditText editText7;

    Toolbar toolbar;
    Button button;

    TextView textView;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;


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
        editText7 = findViewById(R.id.diachi);

        textView1 = findViewById(R.id.thongbao1);
        textView2 = findViewById(R.id.thongbao2);
        textView3 = findViewById(R.id.thongbao3);
        textView4 = findViewById(R.id.thongbao4);
        textView5 = findViewById(R.id.thongbao5);
        textView6 = findViewById(R.id.thongbao6);
        textView7 = findViewById(R.id.thongbao7);


        button = findViewById(R.id.buttonregister);
        textView = findViewById(R.id.login6);

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

    public static boolean validateEmail(String email) {
        // Sử dụng hàm Patterns.EMAIL_ADDRESS.matcher để kiểm tra định dạng email
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        // Sử dụng biểu thức chính quy để kiểm tra định dạng số điện thoại
        // Trong ví dụ này, số điện thoại phải có ít nhất 10 chữ số và không chứa ký tự đặc biệt
        String regex = "^[0-9]{10,}$";
        return phoneNumber.matches(regex);
    }

    public static boolean validateFullName(String fullName) {
        // Sử dụng biểu thức chính quy để kiểm tra định dạng họ tên
        // Trong ví dụ này, họ tên chỉ chứa chữ cái và khoảng trắng
        String regex = "^[a-zA-Z\\s]+$";
        return fullName.matches(regex);
    }

    public static boolean validateAddress(String address) {
        // Sử dụng biểu thức chính quy để kiểm tra định dạng địa chỉ
        // Trong ví dụ này, địa chỉ có thể chứa chữ cái, chữ số, khoảng trắng và các ký tự đặc biệt như dấu phẩy, chấm, gạch chéo
        String regex = "^[a-zA-Z0-9\\s\\-,.\\/]+$";
        return address.matches(regex);
    }

    public void setOnClick() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isValidCredentials(editText1.getText().toString().trim())) {
                    textView1.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            textView1.setVisibility(View.INVISIBLE);
                        }
                    }, 5000);

                } else if (!isValidCredentials(editText2.getText().toString().trim())) {
                    textView2.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            textView2.setVisibility(View.INVISIBLE);
                        }
                    }, 5000);

                } else if (!isValidCredentials(editText3.getText().toString().trim())) {
                    textView3.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            textView3.setVisibility(View.INVISIBLE);
                        }
                    }, 5000);

                } else if (!editText2.getText().toString().trim().equals(editText3.getText().toString().trim())) {
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
                } else if (!validateFullName(editText4.getText().toString().trim())) {
                    textView4.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            textView4.setVisibility(View.INVISIBLE);
                        }
                    }, 5000);

                } else if (!validateEmail(editText5.getText().toString().trim())) {
                    textView5.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            textView5.setVisibility(View.INVISIBLE);
                        }
                    }, 5000);

                } else if (!validatePhoneNumber(editText6.getText().toString().trim())) {
                    textView6.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            textView6.setVisibility(View.INVISIBLE);
                        }
                    }, 5000);

                }
                else if (!validateAddress(editText7.getText().toString().trim())) {
                    textView7.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            textView7.setVisibility(View.INVISIBLE);
                        }
                    }, 5000);

                } else {
                    API.api.Register(editText1.getText().toString().trim(),
                                    editText2.getText().toString().trim(),
                                    editText4.getText().toString().trim(),
                                    editText5.getText().toString().trim(),
                                    editText7.getText().toString().trim(),
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
                                        getUser(editText1.getText().toString().trim(), editText2.getText().toString().trim());
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
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
            }


        });
    }

    public void getUser(String username, String password) {
        API.api.getUser(username, password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<User>>() {
                    List<User> user = new ArrayList<>();

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        @NonNull Disposable disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<User> user1) {
                        user = user1;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        if (!user.isEmpty()) {
                            CheckLogin.user = user.get(0);
                            CheckLogin.Login = true;
                            CheckLogin.UserID = user.get(0).getId_nguoi_dung();
                        }
                    }
                });
    }
}
