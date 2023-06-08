package com.example.appbanhang.activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhang.Adapter.ProductAdapter2;
import com.example.appbanhang.R;
import com.example.appbanhang.model.Product;
import com.example.appbanhang.service.API;
import com.example.appbanhang.service.OnItemClickListenerProduct;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity {
    private AutoCompleteTextView autoCompleteTextView;
    private ImageView imageView;
    private RecyclerView recyclerView;
    ProductAdapter2 productAdapter2;
    List<Product> products;
    private Disposable disposable;
    TextView empty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        init();
        setOnClick();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String name = bundle.getString("name");
            getSearch(name);
        }

    }

    public void init() {
        autoCompleteTextView = findViewById(R.id.search2);
        imageView = findViewById(R.id.imageback);
        recyclerView = findViewById(R.id.recyclerviewsearch);
        productAdapter2 = new ProductAdapter2();
        products = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, recyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        empty = findViewById(R.id.emptyTextView2);
    }

    private void getSearch(String name) {
        API.api.getSearch(name).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Product>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<Product> products1) {
                        products = products1;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        if (products.isEmpty()) {
                            empty.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        } else {
                            empty.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);

                        }
                        productAdapter2.setData(products, new OnItemClickListenerProduct() {
                            @Override
                            public void onItemClickProduct(Product product) {
                                Intent intent = new Intent(getApplication(), ProductdetailsActivity.class);

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

    public void setOnClick() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Xử lý khi người dùng chọn một item
                String selectedItem = (String) parent.getItemAtPosition(position);
                getSearch(selectedItem);
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
                    getSearch(searchText);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);

                    return true;
                }
                return false; // Trả về false để yêu cầu hệ thống xử lý sự kiện
            }
        });
    }
}