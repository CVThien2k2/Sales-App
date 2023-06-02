package com.example.appbanhang.fragment;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.appbanhang.Adapter.StringAdapter;
import com.example.appbanhang.R;
import com.example.appbanhang.model.Product;
import com.example.appbanhang.model.ProductCategory;
import com.example.appbanhang.service.API;
import com.example.appbanhang.service.OnItemClickListenerString;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ManFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    List<ProductCategory> str;
    StringAdapter stringAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_man, container, false);
        init();
        return view;
    }

    public void init() {

        recyclerView = view.findViewById(R.id.ListMan);
        stringAdapter = new StringAdapter();
        str = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), recyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        getDanhmuc();



    }
    private void getDanhmuc() {
        API.api.getDanhmucnam().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ProductCategory>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull List<ProductCategory> products) {
                        str = products;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        stringAdapter.setData(str, new OnItemClickListenerString() {
                            @Override
                            public void OnItemClickListenerString(ProductCategory productCategory) {
                                ListProductFragment fragment = new ListProductFragment();
                                Bundle bundle = new Bundle();
                                bundle.putString("ten", productCategory.getTen_danh_muc());
                                bundle.putString("gioitinh", productCategory.getGioi_tinh());; // Gán dữ liệu vào bundle
                                fragment.setArguments(bundle); // Truyền bundle cho fragment
                                getActivity().getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.framelayout, fragment)
                                        .addToBackStack(null)
                                        .commit();

                            }
                        });
                        recyclerView.setAdapter(stringAdapter);
                    }
                });
    }

   
}