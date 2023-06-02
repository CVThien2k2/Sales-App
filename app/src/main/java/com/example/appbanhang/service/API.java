package com.example.appbanhang.service;


import com.example.appbanhang.model.Product;
import com.example.appbanhang.model.ProductCategory;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
public interface API {

    HttpLoggingInterceptor log = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient.Builder okBuilder = new OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30,TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(log);
//    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH-mm-ss").create();
    API api = new Retrofit.Builder().baseUrl("http://192.168.3.105/")
            .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
             .client(okBuilder.build())
            .build().create(API.class);

    //Trang chu
    @GET("server/getTopSellingProducts.php")
    Observable<List<Product>> getTopSellingProducts();
    @GET("server/getSaleProducts.php")
    Observable<List<Product>> getSaleProducts();

    //Nam
    @GET("server/getDanhmucnam.php")
    Observable<List<ProductCategory>> getDanhmucnam();
    @GET("server/getQuannam.php")
    Observable<List<Product>> getQuannam();
    @GET("server/getAonam.php")
    Observable<List<Product>> getAonam();
    @GET("server/getGiaynam.php")
    Observable<List<Product>> getGiaynam();
    @GET("server/getDepnam.php")
    Observable<List<Product>> getDepnam();
    //Nữ
    @GET("server/getDanhmucnu.php")
    Observable<List<ProductCategory>> getDanhmucnu();
    @GET("server/getQuannu.php")
    Observable<List<Product>> getQuannu();
    @GET("server/getAonu.php")
    Observable<List<Product>> getAonu();
    @GET("server/getGiaynu.php")
    Observable<List<Product>> getGiaynu();
    @GET("server/getDepnu.php")
    Observable<List<Product>> getDepnu();

    @GET("server/getVaynu.php")
    Observable<List<Product>> getVaynu();
}
