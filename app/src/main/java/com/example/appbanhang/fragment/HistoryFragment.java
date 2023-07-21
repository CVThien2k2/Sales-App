package com.example.appbanhang.fragment;

import static android.content.ContentValues.TAG;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhang.Adapter.ListOrderAdapter;
import com.example.appbanhang.Adapter.item_order_Adapter;
import com.example.appbanhang.R;
import com.example.appbanhang.activity.MainActivity;
import com.example.appbanhang.activity.PaymentActivity;
import com.example.appbanhang.activity.ProductdetailsActivity;
import com.example.appbanhang.model.Parameter;
import com.example.appbanhang.model.Product;
import com.example.appbanhang.model.ProductOrder;
import com.example.appbanhang.model.ResponseData;
import com.example.appbanhang.model.item_cart;
import com.example.appbanhang.model.item_order;
import com.example.appbanhang.service.API;
import com.example.appbanhang.service.CheckLogin;
import com.example.appbanhang.service.OnItemClickListenerOrder;
import com.example.appbanhang.service.OnItemClickListenerProduct;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class HistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private ListOrderAdapter listOrderAdapter;
    private TextView tvhistory;

    View view;
    String data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_history, container, false);
        init();
        Bundle args = getArguments();
        if (args != null) {
            data = args.getString("data");
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
                            }, new OnItemClickListenerOrder() {
                                @Override
                                public void onItemClickProductOrder(ProductOrder product, List<item_order> list) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                    builder.setTitle("Xác nhận hủy đơn hàng");
                                    builder.setMessage("Bạn có muốn hủy đơn?");

                                    // Thiết lập nút xác nhận
                                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        deleteItem(product.getId_don_hang(),list);
                                        listOrderAdapter.notifyDataSetChanged();
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
                            recyclerView.setAdapter(listOrderAdapter);
                        } else {
                        recyclerView.setVisibility(View.GONE);
                        tvhistory.setVisibility(View.VISIBLE);
                    }
                    }
                });
    }
    public void deleteItem(int id,List<item_order> list) {
        for (item_order item : list) {
            Product product = item.getProduct();
            Parameter parameter = item.getParameter();
            API.api.setquantity(parameter.getId_thong_so(),parameter.getCon_lai()+item.getSo_luong_san_pham())
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
        API.api.setChangeOrder(id,"Đã hủy hàng")
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
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        // Xử lý khi xảy ra lỗi
                    }

                    @Override
                    public void onComplete() {
                        // Xử lý khi hoàn thành
                        getOrder(data);
                        listOrderAdapter.notifyDataSetChanged();
                    }
                });


    }
}