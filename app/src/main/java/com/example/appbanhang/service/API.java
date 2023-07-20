package com.example.appbanhang.service;


import com.example.appbanhang.model.Parameter;
import com.example.appbanhang.model.Product;
import com.example.appbanhang.model.ProductCategory;
import com.example.appbanhang.model.ProductOrder;
import com.example.appbanhang.model.ResponseData;
import com.example.appbanhang.model.User;
import com.example.appbanhang.model.item_cart;
import com.example.appbanhang.model.item_order;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {


    HttpLoggingInterceptor log = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient.Builder okBuilder = new OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30,TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(log);
//    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH-mm-ss").create();
    API api = new Retrofit.Builder().baseUrl("https://caovanthien09102002.000webhostapp.com/")
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
    @GET("server/getAonam.php")
    Observable<List<Product>> getAonam();
    @GET("server/getDanhmucnu.php")
    Observable<List<ProductCategory>> getDanhmucnu();

    // POST
    @POST ("server/getProduct.php")
    @FormUrlEncoded
    Observable<List<Product>> getProduct(
            @Field("danhmuc") String search,
            @Field("gioitinh") String gioitinh
    );
    @POST ("server/getSearch.php")
    @FormUrlEncoded
    Observable<List<Product>> getSearch(
            @Field("name") String search
    );

    @POST ("server/getUser.php")
    @FormUrlEncoded
    Observable<List<User>> getUser(
            @Field("username") String username,
            @Field("password") String password
    );


    @POST ("server/check.php")
    @FormUrlEncoded
    Observable<ResponseData> check(
            @Field("username") String username,
            @Field("email") String email,
            @Field("phone") String phone
    );
    @FormUrlEncoded
    @POST("server/Register.php")
    Observable<ResponseData> Register(
            @Field("ten_dang_nhap") String tenDangNhap,
            @Field("mat_khau") String matKhau,
            @Field("ho_ten") String hoTen,
            @Field("email") String email,
            @Field("dia_chi") String diaChi,
            @Field("so_dien_thoai") String soDienThoai
    );

    //Gio hang
    @POST ("server/getItemCart.php")
    @FormUrlEncoded
    Observable<List<item_cart>> getitemCart(
            @Field("id") int id
    );

    @POST ("server/setItemCart.php")
    @FormUrlEncoded
    Observable<ResponseData> setItemCart(
            @Field("id") int id,
            @Field("count") int count,
            @Field("st") int st

    );

    //
    @POST ("server/getUser1.php")
    @FormUrlEncoded
    Observable<User> getUser(
            @Field("id") int id
    );

    //Thay doi thong tin
    @POST ("server/setInfo.php")
    @FormUrlEncoded
    Observable<ResponseData> setInfo(
            @Field("id") int id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("address") String address

    );
    //Them gio hang

    @POST ("server/getParameter.php")
    @FormUrlEncoded
    Observable<List<Parameter>> getParameter(
            @Field("id") int id
    );
    @POST ("server/updateItemCart.php")
    @FormUrlEncoded
    Observable<ResponseData> updateItemCart(
            @Field("id") int id,
            @Field("id_product") int id_product,
            @Field("id_parameter") int id_parameter,
            @Field("quantity") int quantity

    );
    @POST ("server/deleteItemCart.php")
    @FormUrlEncoded
    Observable<ResponseData> deleteItemCart(
            @Field("id_item_gio_hang") int id_item_gio_hang

    );
    //Lich su

    @POST ("server/getItemOrder.php")
    @FormUrlEncoded
    Observable<List<item_order>> getItemOrder(
            @Field("id") int id

    );
    @POST ("server/getOrder.php")
    @FormUrlEncoded
    Observable<List<ProductOrder>> getOrder(
            @Field("id") int id,
            @Field("trang_thai_don_hang") String trang_thai_don_hang

    );
    //Thanh toan
    @POST ("server/getItemPayment.php")
    @FormUrlEncoded
    Observable<List<item_cart>> getItemPayment(
            @Field("id") int id
    );
    //Dat hang
    @POST ("server/createOrder.php")
    @FormUrlEncoded
    Observable<ResponseData> createOrder(
            @Field("id_nguoi_dung") int id_nguoi_dung,
            @Field("ngay_dat_hang") String ngay_dat_hang,
            @Field("gia") double gia,
            @Field("ngay_giao_hang") String ngay_giao_hang,
            @Field("trang_thai_don_hang") String trang_thai_don_hang
    );
    @POST ("server/createItemOrder.php")
    @FormUrlEncoded
    Observable<ResponseData> createItemOrder(
            @Field("id_don_hang") int id_don_hang,
            @Field("id_san_pham") int id_san_pham,
            @Field("id_thong_so") int id_thong_so,
            @Field("so_luong_san_pham") int so_luong_san_pham
    );
    @POST ("server/setquantity.php")
    @FormUrlEncoded
    Observable<ResponseData> setquantity(
            @Field("id_thong_so") int id_thong_so,
            @Field("con_lai") int con_lai
    );
    //Huy Hang
    @POST ("server/setChangeOrder.php")
    @FormUrlEncoded
    Observable<ResponseData> setChangeOrder(
            @Field("id_don_hang") int id_don_hang,
            @Field("trang_thai_don_hang") String trang_thai_don_hang
    );
    //Check Item
    @POST ("server/checkItemPayment.php")
    @FormUrlEncoded
    Observable<ResponseData> checkItemPayment(
            @Field("id_thong_so") int id_thong_so,
            @Field("so_luong") int so_luong
    );

}
