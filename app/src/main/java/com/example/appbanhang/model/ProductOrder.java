package com.example.appbanhang.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ProductOrder {
    private int id_don_hang;
    private int id_nguoi_dung;
    private Date ngay_dat_hang;
    private int gia;
    private Date ngay_giao_hang;
    private String trang_thai_don_hang;
//    List<item_order> item_orders;


    public ProductOrder(int id_don_hang, int id_nguoi_dung, Date ngay_dat_hang, int gia, Date ngay_giao_hang, String trang_thai_don_hang) {
        this.id_don_hang = id_don_hang;
        this.id_nguoi_dung = id_nguoi_dung;
        this.ngay_dat_hang = ngay_dat_hang;
        this.gia = gia;
        this.ngay_giao_hang = ngay_giao_hang;
        this.trang_thai_don_hang = trang_thai_don_hang;
    }

    public int getId_don_hang() {
        return id_don_hang;
    }

    public void setId_don_hang(int id_don_hang) {
        this.id_don_hang = id_don_hang;
    }

    public int getId_nguoi_dung() {
        return id_nguoi_dung;
    }

    public void setId_nguoi_dung(int id_nguoi_dung) {
        this.id_nguoi_dung = id_nguoi_dung;
    }

    public Date getNgay_dat_hang() {
        return ngay_dat_hang;
    }

    public void setNgay_dat_hang(Date ngay_dat_hang) {
        this.ngay_dat_hang = ngay_dat_hang;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public Date getNgay_giao_hang() {
        return ngay_giao_hang;
    }

    public void setNgay_giao_hang(Date ngay_giao_hang) {
        this.ngay_giao_hang = ngay_giao_hang;
    }

    public String getTrang_thai_don_hang() {
        return trang_thai_don_hang;
    }

    public void setTrang_thai_don_hang(String trang_thai_don_hang) {
        this.trang_thai_don_hang = trang_thai_don_hang;
    }
}

