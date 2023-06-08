package com.example.appbanhang.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhang.R;
import com.example.appbanhang.model.Product;
import com.squareup.picasso.Picasso;

public class ProductdetailsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView txttensp;
    private TextView txtdaban;
    private TextView txtdanhgia;
    private TextView txtgiatien;
    private TextView txtmota;

    private ImageView imagesp;
    private int id;
    Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productdetails);

        init();
        ActionBar();
        setView();
    }

    private void setView() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            product = (Product) bundle.getSerializable("product");
            txttensp.setText(""+product.getTen_san_pham());
            Picasso.get().load(product.getHinh_anh()).into(imagesp);
            txtdaban.setText(product.getDa_ban()+" lần.");
            txtdanhgia.setText(product.getDanh_gia()+"/5.");
            txtgiatien.setText("Giá tiền: "+ product.getGia_san_pham()+" đ.");
            txtmota.setText("Mô tả sản phẩm: \n\t"+product.getMo_ta());
        }
    }

    public void init() {
        toolbar = findViewById(R.id.toolbarchitiet);
        txtdaban = findViewById(R.id.dabanchitiet);
        txttensp = findViewById(R.id.tenchitiet);
        txtdanhgia = findViewById(R.id.danhgiachitiet);
        txtgiatien = findViewById(R.id.giachitiet);
        txtmota = findViewById(R.id.motachitiet);
        imagesp = findViewById(R.id.imagechitiet);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {

        }actionBar.setDisplayHomeAsUpEnabled(true); // Hiển thị nút trở về
        actionBar.setHomeAsUpIndicator(R.drawable.back3); // Thay thế "R.drawable.ic_back_arrow" bằng icon của bạn
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
    }
}