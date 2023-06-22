package com.example.appbanhang.fragment;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appbanhang.Adapter.ListOrderAdapter;
import com.example.appbanhang.Adapter.item_order_Adapter;
import com.example.appbanhang.R;
import com.example.appbanhang.activity.ProductdetailsActivity;
import com.example.appbanhang.model.Product;
import com.example.appbanhang.model.ProductOrder;
import com.example.appbanhang.model.item_order;
import com.example.appbanhang.service.API;
import com.example.appbanhang.service.CheckLogin;
import com.example.appbanhang.service.OnItemClickListenerProduct;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class HistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private ListOrderAdapter listOrderAdapter;
    private TextView tvhistory;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_history, container, false);
        init();
        Bundle args = getArguments();
        if (args != null) {
            String data = args.getString("data");
            if (data != null) {
                getOrder(data);
            }
        }
        return view;
    }

    private void init() {
        recyclerView = view.findViewById(R.id.recyclerviewhistory);
        listOrderAdapter = new ListOrderAdapter(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        tvhistory = view.findViewById(R.id.tvhistory);
    }

    public void getOrder(String trangthaidonhang) {
        API.api.getOrder(CheckLogin.UserID, trangthaidonhang)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ProductOrder>>() {
                    private List<ProductOrder> list;

                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<ProductOrder> item_order) {
                        list = item_order;
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        if (list.size() != 0) {
                            listOrderAdapter.setData(list, new OnItemClickListenerProduct() {
                                @Override
                                public void onItemClickProduct(Product product) {
                                    Intent intent = new Intent(getActivity(), ProductdetailsActivity.class);

                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("product", product);

                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });
                            recyclerView.setAdapter(listOrderAdapter);
                        } else {
                        recyclerView.setVisibility(View.GONE);
                        tvhistory.setVisibility(View.VISIBLE);
                    }
                    }
                });
    }
}