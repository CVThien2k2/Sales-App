package com.example.appbanhang.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.appbanhang.R;
import com.example.appbanhang.activity.ProductdetailsActivity;
import com.example.appbanhang.activity.Profile;
import com.example.appbanhang.model.Product;
import com.example.appbanhang.model.ResponseData;
import com.example.appbanhang.model.User;
import com.example.appbanhang.service.API;
import com.example.appbanhang.service.CheckLogin;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MeFragment extends Fragment {
    View view;
    
    User user;
    private LinearLayout profile;
    private ImageView edit;
    private TextView name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_me, container, false);
        init();
        getUser();
        setClick();
        
        return view;
    }


    private void init() {
        name = view.findViewById(R.id.tvprofile_name);
        profile = view.findViewById(R.id.thongtin);
        edit = view.findViewById(R.id.imgv_editname);
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

    }


}