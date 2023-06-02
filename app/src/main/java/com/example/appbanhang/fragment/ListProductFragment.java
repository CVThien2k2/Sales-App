package com.example.appbanhang.fragment;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appbanhang.Adapter.ProductAdapter2;
import com.example.appbanhang.R;
import com.example.appbanhang.model.Product;
import com.example.appbanhang.service.API;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ListProductFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    ProductAdapter2 productAdapter2;
    List<Product> products1;
    private Disposable disposable;

    private Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list_product, container, false);
        Bundle bundle = getArguments();
        init();
        ActionBar();
        if (bundle != null) {
            String ten = bundle.getString("ten");
            String gioitinh = bundle.getString("gioitinh");
            toolbar.setTitle(ten + " "+gioitinh);
            if (gioitinh.equals("Nam")) {
                switch (ten) {
                    case "Quần":

                        getQuannam();
                        break;
                    case "Áo":
                        getAonam();
                        break;
                    case "Giày":
                        getGiaynam();
                        break;
                    case "Dép":
                        getDepnam();
                        break;

                }
            } else {
                switch (ten) {
                    case "Quần":
                        getQuannu();
                        break;
                    case "Áo":
                        getAonu();
                        break;
                    case "Giày":
                        getGiaynu();
                        break;
                    case "Dép":
                        getDepnu();
                        break;
                    case "Váy":
                        getVaynu();
                        break;

                }
            }
        }
        return view;
    }

    private void getDepnu() {
        API.api.getDepnu().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Product>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<Product> products) {
                        products1 = products;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        productAdapter2.setData(products1);
                        recyclerView.setAdapter(productAdapter2);
                    }
                });
    }

    private void getGiaynu() {
        API.api.getGiaynu().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Product>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<Product> products) {
                        products1 = products;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        productAdapter2.setData(products1);
                        recyclerView.setAdapter(productAdapter2);
                    }
                });
    }

    private void getAonu() {
        API.api.getAonu().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Product>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<Product> products) {
                        products1 = products;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        productAdapter2.setData(products1);
                        recyclerView.setAdapter(productAdapter2);
                    }
                });
    }

    private void getQuannu() {
        API.api.getQuannu().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Product>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<Product> products) {
                        products1 = products;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        productAdapter2.setData(products1);
                        recyclerView.setAdapter(productAdapter2);
                    }
                });
    }
    private void getVaynu() {
        API.api.getVaynu().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Product>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<Product> products) {
                        products1 = products;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        productAdapter2.setData(products1);
                        recyclerView.setAdapter(productAdapter2);
                    }
                });
    }
    public void init() {
        recyclerView = view.findViewById(R.id.recyclerview3);
        toolbar = view.findViewById(R.id.ToolBarProduct);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), recyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        productAdapter2 = new ProductAdapter2();
        products1 = new ArrayList<>();

    }

    private void ActionBar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); // Hiển thị nút trở về
            actionBar.setHomeAsUpIndicator(R.drawable.back); // Thay thế "R.drawable.ic_back_arrow" bằng icon của bạn
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void getQuannam() {
        API.api.getQuannam().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Product>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<Product> products) {
                        products1 = products;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        productAdapter2.setData(products1);
                        recyclerView.setAdapter(productAdapter2);
                    }
                });
    }

    private void getAonam() {
        API.api.getAonam().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Product>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<Product> products) {
                        products1 = products;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        productAdapter2.setData(products1);
                        recyclerView.setAdapter(productAdapter2);
                    }
                });
    }

    private void getGiaynam() {
        API.api.getGiaynam().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Product>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<Product> products) {
                        products1 = products;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        productAdapter2.setData(products1);
                        recyclerView.setAdapter(productAdapter2);
                    }
                });
    }

    private void getDepnam() {
        API.api.getDepnam().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Product>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<Product> products) {
                        products1 = products;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        productAdapter2.setData(products1);
                        recyclerView.setAdapter(productAdapter2);
                    }
                });
    }
}