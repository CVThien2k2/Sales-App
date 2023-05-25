package com.example.appbanhang.fragment;

import static android.content.ContentValues.TAG;
import static com.example.appbanhang.service.API.log;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.appbanhang.Adapter.ProductAdapter;
import com.example.appbanhang.R;
import com.example.appbanhang.model.Product;
import com.example.appbanhang.service.API;
import com.example.appbanhang.service.CheckConnection;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    ViewFlipper viewFlipper;
    ProductAdapter productAdapter1;
    ProductAdapter productAdapter2;
    RecyclerView recyclerView1;
    RecyclerView recyclerView2;

    List<Product> topSellingProducts;

    List<Product> saleProducts;
    private Disposable disposable;

    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter recommend;
    List<String> nameProducts;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    API api;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home, container, false);
        if (CheckConnection.isConnected(getActivity().getApplicationContext())) {

            //ánh xạ
            autoCompleteTextView = view.findViewById(R.id.search);
            viewFlipper = view.findViewById(R.id.viewflipper);
            recyclerView1 = view.findViewById(R.id.recyclerview1);
            recyclerView2 = view.findViewById(R.id.recyclerview2);
            //cài view flipper
            ActionViewFlipper();


            //cài recyclerview
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), recyclerView1.HORIZONTAL, false);
            LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this.getContext(), recyclerView2.HORIZONTAL, false);
            recyclerView1.setLayoutManager(linearLayoutManager);
            recyclerView2.setLayoutManager(linearLayoutManager2);
            //Khởi tạo
            nameProducts = new ArrayList<>();
            topSellingProducts = new ArrayList<>();
            saleProducts = new ArrayList<>();
            productAdapter1 = new ProductAdapter();
            productAdapter2 = new ProductAdapter();


            getTopSellingProducts();
            getSaleProducts();
            setOnClick();
        } else {
            Toast.makeText((getActivity().getApplicationContext()), "Vui lòng kiểm tra kết nối Internet", Toast.LENGTH_LONG).show();

        }
        //ánh xạ


        return view;
    }


    private void getTopSellingProducts() {
        API.api.getTopSellingProducts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Product>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<Product> products) {
                        topSellingProducts = products;
                        String name;
                        for(int i=0; i< topSellingProducts.size();i++){
                            name = topSellingProducts.get(i).getTen_san_pham();
                            nameProducts.add(name);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        productAdapter1.setData(topSellingProducts);
                        recyclerView1.setAdapter(productAdapter1);
                        recommend = new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line,nameProducts);
                        autoCompleteTextView.setAdapter(recommend);
                        autoCompleteTextView.setThreshold(1);
                    }
                });
    }
    private void getSaleProducts() {
        API.api.getSaleProducts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Product>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<Product> products) {
                        saleProducts = products;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        productAdapter2.setData(saleProducts);
                        recyclerView2.setAdapter(productAdapter2);
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

    private void ActionViewFlipper() {
        ArrayList<String> view = new ArrayList<>();
        view.add("http://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-Le-hoi-phu-kien-800-300.png");
        view.add("http://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-HC-Tra-Gop-800-300.png");
        view.add("http://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-big-ky-nguyen-800-300.jpg");
        for (int i = 0; i < view.size(); i++) {
            ImageView imageView = new ImageView(requireContext());
            Glide.with(requireContext()).load(view.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);
    }
public void setOnClick(){
    // Đăng ký sự kiện khi người dùng chọn một item từ danh sách
    autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Xử lý khi người dùng chọn một item
            String selectedItem = (String) parent.getItemAtPosition(position);
            Toast.makeText((getActivity().getApplicationContext()), selectedItem, Toast.LENGTH_LONG).show();

            // Thực hiện hành động mong muốn
        }
    });

// Đăng ký sự kiện khi người dùng ấn enter trên bàn phím
    autoCompleteTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // Xử lý khi người dùng ấn Enter
                String searchText = autoCompleteTextView.getText().toString();
                Toast.makeText((getActivity().getApplicationContext()), searchText, Toast.LENGTH_LONG).show();
                // Thực hiện hành động mong muốn
                return true;
            }
            return false; // Trả về false để yêu cầu hệ thống xử lý sự kiện
        }
    });

}


}