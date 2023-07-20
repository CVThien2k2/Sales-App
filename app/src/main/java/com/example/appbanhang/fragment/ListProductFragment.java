package com.example.appbanhang.fragment;

import static android.content.ContentValues.TAG;

import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhang.Adapter.ProductAdapter;
import com.example.appbanhang.Adapter.ProductAdapter2;
import com.example.appbanhang.R;
import com.example.appbanhang.activity.ProductdetailsActivity;
import com.example.appbanhang.model.Product;
import com.example.appbanhang.service.API;
import com.example.appbanhang.service.CheckConnection;
import com.example.appbanhang.service.OnItemClickListenerProduct;

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
    TextView empty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list_product, container, false);
        if (CheckConnection.isConnected(getActivity().getApplicationContext())) {
            Bundle bundle = getArguments();
            init();
            ActionBar();
            if (bundle != null) {
                String ten = bundle.getString("danhmuc");
                String gioitinh = bundle.getString("gioitinh");
                toolbar.setTitle(ten + " " + gioitinh);
                getProduct(ten, gioitinh);
            }
        } else {
            Toast.makeText((getActivity().getApplicationContext()), "Vui lòng kiểm tra kết nối Internet", Toast.LENGTH_LONG).show();
        }

        return view;
    }


    public void init() {
        recyclerView = view.findViewById(R.id.recyclerview3);
        toolbar = view.findViewById(R.id.ToolBarProduct);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), recyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        productAdapter2 = new ProductAdapter2();
        products1 = new ArrayList<>();
        empty = view.findViewById(R.id.emptyTextView);

    }

    private void ActionBar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); // Hiển thị nút trở về
            actionBar.setHomeAsUpIndicator(R.drawable.back3); // Thay thế "R.drawable.ic_back_arrow" bằng icon của bạn
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void getProduct(String loai, String gioitinh) {
        API.api.getProduct(loai, gioitinh).subscribeOn(Schedulers.io())
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
                        if (products1.isEmpty()) {
                            recyclerView.setVisibility(View.GONE);
                            empty.setVisibility(View.VISIBLE);
                        }
                        else{
                            recyclerView.setVisibility(View.VISIBLE);
                            empty.setVisibility(View.GONE);
                        }
                        productAdapter2.setData(products1, new OnItemClickListenerProduct() {
                            @Override
                            public void onItemClickProduct(Product product) {
                                Intent intent = new Intent(getActivity(), ProductdetailsActivity.class);

                                Bundle bundle = new Bundle();
                                bundle.putSerializable("product", product);

                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                        recyclerView.setAdapter(productAdapter2);
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
        }
    }

}