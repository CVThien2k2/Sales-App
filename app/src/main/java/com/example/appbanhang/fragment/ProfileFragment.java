package com.example.appbanhang.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhang.Adapter.CartAdapter;
import com.example.appbanhang.R;
import com.example.appbanhang.activity.HistoryActivity;
import com.example.appbanhang.activity.MainActivity;
import com.example.appbanhang.activity.Profile;
import com.example.appbanhang.model.ResponseData;
import com.example.appbanhang.model.User;
import com.example.appbanhang.service.API;
import com.example.appbanhang.service.CheckConnection;
import com.example.appbanhang.service.CheckLogin;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ProfileFragment extends Fragment {
    View view;
    
    User user;
    private LinearLayout profile;
    private LinearLayout history;
    private ImageView edit;
    private TextView name;
    private Button logout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_me, container, false);
        if (CheckConnection.isConnected(getActivity().getApplicationContext())) {
            init();
            getUser();
            setClick();
        } else {
            Toast.makeText((getActivity().getApplicationContext()), "Vui lòng kiểm tra kết nối Internet", Toast.LENGTH_LONG).show();
        }
        
        return view;
    }


    private void init() {
        logout = view.findViewById(R.id.btprofile_logout);
        name = view.findViewById(R.id.tvprofile_name);
        profile = view.findViewById(R.id.thongtin);
        edit = view.findViewById(R.id.imgv_editname);
        history = view.findViewById(R.id.lichsu);
        user = new User();
    }


    public void getUser(){
        API.api.getUser(CheckLogin.UserID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                        // Xử lý khi đăng ký
                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull User user2) {
                        // Xử lý khi nhận được dữ liệu phản hồi
                            user = user2;
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        // Xử lý khi xảy ra lỗi
                    }

                    @Override
                    public void onComplete() {
                        // Xử lý khi hoàn thành
                        name.setText(user.getHo_ten());
                    }
                });
    }
    void setClick(){
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Profile.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("user", user);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Xác nhận đăng xuất");
                builder.setMessage("Bạn có muốn đăng xuất?");

                // Thiết lập nút xác nhận
                builder.setPositiveButton("Đăng xuất", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CheckLogin.Login = false;
                        CheckLogin.UserID = 0;
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        dialog.dismiss();

                    }
                });

                // Thiết lập nút hủy
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Đóng hộp thoại và không thực hiện xóa sản phẩm
                        dialog.dismiss();
                    }
                });

                // Hiển thị hộp thoại
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HistoryActivity.class);

//                Bundle bundle = new Bundle();
//                bundle.putSerializable("user", user);
//
//                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }


}