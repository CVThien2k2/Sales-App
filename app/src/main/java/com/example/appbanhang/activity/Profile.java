package com.example.appbanhang.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhang.R;
import com.example.appbanhang.model.Product;
import com.example.appbanhang.model.ResponseData;
import com.example.appbanhang.model.User;
import com.example.appbanhang.service.API;
import com.example.appbanhang.service.CheckLogin;
import com.squareup.picasso.Picasso;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Profile extends AppCompatActivity {
    private EditText name;
    private EditText email;
    private EditText phone;
    private EditText address;
    private Button save;
    User user;
    Toolbar toolbar;
    String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
        setView();
        ActionBar();
        setClick();
    }
    public void init(){
        name = findViewById(R.id.name1);
        email = findViewById(R.id.email1);
        phone = findViewById(R.id.phone1);
        address = findViewById(R.id.address1);
        save = findViewById(R.id.save);
        toolbar = findViewById(R.id.toolbarinfo);
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
    public void checkChange(){
        if(name.getText().toString().equals(user.getHo_ten())&&email.getText().toString().equals(user.getEmail())&&address.getText().toString().equals(user.getDia_chi())&&phone.getText().toString().equals(user.getSo_dien_thoai())){
            save.setEnabled(false);
        }
        else save.setEnabled(true);
    }
    private void setView() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            user = (User) bundle.getSerializable("user");
            name.setText(user.getHo_ten());
            email.setText(user.getEmail());
            phone.setText(user.getSo_dien_thoai());
            address.setText(user.getDia_chi());

        }
    }
    public void setClick(){
        save.setEnabled(false);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this);
                builder.setMessage("Bạn có muốn lưu thay đổi?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                showInputDialog();
                            }
                        })
                        .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // Hủy bỏ lưu thay đổi ở đây
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkChange();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkChange();
            }
        });
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkChange();
            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkChange();
            }
        });
        address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkChange();
            }
        });

    }
    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this);
        builder.setTitle("Nhập vào mật khẩu của bạn");

        // Tạo trường nhập liệu
        final EditText input = new EditText(Profile.this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

        // Đặt nút "Lưu" cho hộp thoại nhập liệu
        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String inputValue = input.getText().toString();
                if(inputValue.equals(user.getMat_khau())){
//                    Toast.makeText(Profile.this, "lưu thành công", Toast.LENGTH_SHORT).show();
                    setUser(user.getId_nguoi_dung(),name.getText().toString().trim(),email.getText().toString().trim(),phone.getText().toString().trim(),address.getText().toString());
                }
                else {
                    Toast.makeText(Profile.this, "Mật khẩu sai", Toast.LENGTH_SHORT).show();
                }
                // Xử lý giá trị nhập vào
            }
        });

        // Đặt nút "Hủy" cho hộp thoại nhập liệu
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Hiển thị hộp thoại nhập liệu
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public String setUser(int id,String name, String email, String phone, String address){

        API.api.setInfo(id,name,email,phone,address)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                        // Xử lý khi đăng ký
                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull ResponseData ResponseData) {
                        // Xử lý khi nhận được dữ liệu phản hồi
                        res = ResponseData.getMessage().toString();
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        // Xử lý khi xảy ra lỗi
                    }

                    @Override
                    public void onComplete() {
                        // Xử lý khi hoàn thành
                        Toast.makeText(Profile.this, "Đã lưu thay đổi", Toast.LENGTH_SHORT).show();
                    }
                });
        return res;
    }

}