package com.example.appbanhang.service;


import com.example.appbanhang.model.Product;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
    API api = new Retrofit.Builder().baseUrl("http://192.168.3.102/")
            .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
             .client(okBuilder.build())
            .build().create(API.class);
    @GET("server/getTopSellingProducts.php")
    Observable<List<Product>> getTopSellingProducts();
    @GET("server/getSaleProducts.php")
    Observable<List<Product>> getSaleProducts();


}
