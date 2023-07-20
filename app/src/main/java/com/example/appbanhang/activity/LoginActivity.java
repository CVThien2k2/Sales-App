package com.example.appbanhang.activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhang.R;
import com.example.appbanhang.model.Product;
import com.example.appbanhang.model.User;
import com.example.appbanhang.service.API;
import com.example.appbanhang.service.CheckLogin;
import com.example.appbanhang.service.OnItemClickListenerProduct;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText username;
    private EditText password;
    private Button button;
    TextView usernameerror;
    TextView error;
    TextView register;
    private Disposable disposable;
    List<User> user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        ActionBar();
        SetOnClick();
    }
    public void init(){
        toolbar = findViewById(R.id.toolbarlogin);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        button = findViewById(R.id.buttonlogin);
        usernameerror =findViewById(R.id.errorusername);
        error = findViewById(R.id.error);
        register = findViewById(R.id.register2);
        user = new ArrayList<>();
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
    public void SetOnClick(){
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    // Xử lý sự kiện khi người dùng ấn nút Enter
                    button.performClick(); // Giả lập sự kiện nhấn nút submitButton
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                if (isValidCredentials(name, pass)) {
                    // Thực hiện đăng nhập
                    API.api.getUser(name,pass).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<List<User>>() {
                                @Override
                                public void onSubscribe(@NonNull Disposable d) {
                                    disposable = d;
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
                                    if (!user.isEmpty()){
                                        CheckLogin.user = user.get(0);
                                        CheckLogin.Login = true;
                                        CheckLogin.UserID = user.get(0).getId_nguoi_dung();
                                        error.setText("Đăng nhập thành công");
                                        error.setTextColor(Color.GREEN);
                                        error.setVisibility(View.VISIBLE);
                                        usernameerror.setVisibility(View.GONE);
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                error.setVisibility(View.GONE);
                                            }
                                        }, 5000);
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else{
                                        error.setVisibility(View.VISIBLE);
                                        usernameerror.setVisibility(View.GONE);
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                error.setVisibility(View.GONE);
                                            }
                                        }, 5000);
                                    }
                                }
                            });

                } else {
                    error.setVisibility(View.VISIBLE);
                    usernameerror.setVisibility(View.GONE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            error.setVisibility(View.INVISIBLE);
                        }
                    }, 5000);
                }
            }
        });
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String regex = "^[a-zA-Z0-9]+$";
                if(username.getText().toString().trim().isEmpty()|| !username.getText().toString().trim().matches(regex)){
                    usernameerror.setVisibility(View.VISIBLE);
                    error.setVisibility(View.GONE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            usernameerror.setVisibility(View.GONE);
                        }
                    }, 5000);
                }
            }
        });
    }
    public boolean isValidCredentials(String username, String password) {
        // Kiểm tra xem tên người dùng và mật khẩu có rỗng hay không
        if (username.isEmpty() || password.isEmpty()) {
            return false;
        }

        // Kiểm tra xem tên người dùng và mật khẩu có chứa các ký tự không hợp lệ hay không
        String regex = "^[a-zA-Z0-9]+$"; // Chỉ cho phép ký tự chữ và số
        if (!username.matches(regex) || !password.matches(regex)) {
            return false;
        }

        return true;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
        }
    }


}