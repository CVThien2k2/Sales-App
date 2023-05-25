package com.example.appbanhang.fragment;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
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
    EditText editText;
    ProductAdapter2 productAdapter2;
    List<Product> products1;
    private Disposable disposable;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_list_product, container, false);
        init();
        return view;
    }

    public void init(){
        recyclerView = view.findViewById(R.id.recyclerview3);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), recyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        editText = view.findViewById(R.id.namesearch);
        productAdapter2 = new ProductAdapter2();
        products1 = new ArrayList<>();
        getSearchProduct();

    }
    private void getSearchProduct() {
        API.api.getTopSellingProducts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Product>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<Product> products) {
                        products1 = products;
                        Toast.makeText(getContext(), products1.get(1).getTen_san_pham(), Toast.LENGTH_SHORT).show();
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