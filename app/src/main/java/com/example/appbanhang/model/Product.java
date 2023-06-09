package com.example.appbanhang.model;

import java.io.Serializable;

public class Product implements Serializable {
    private int id_san_pham;
    private String ten_san_pham;
    private String mo_ta;
    private double gia_san_pham;
    private String thuong_hieu;
    private int id_danh_muc;
    private int da_ban;
    private double danh_gia;
    private String hinh_anh;

    public Product(int id_san_pham, String ten_san_pham, String mo_ta, double gia_san_pham, String thuong_hieu, int id_danh_muc, int da_ban, double danh_gia, String hinh_anh) {
        this.id_san_pham = id_san_pham;
        this.ten_san_pham = ten_san_pham;
        this.mo_ta = mo_ta;
        this.gia_san_pham = gia_san_pham;
        this.thuong_hieu = thuong_hieu;
        this.id_danh_muc = id_danh_muc;
        this.da_ban = da_ban;
        this.danh_gia = danh_gia;
        this.hinh_anh = hinh_anh;
    }

    public Product() {
    }

    public int getDa_ban() {
        return da_ban;
    }

    public void setDa_ban(int da_ban) {
        this.da_ban = da_ban;
    }

    public double getDanh_gia() {
        return danh_gia;
    }

    public void setDanh_gia(double danh_gia) {
        this.danh_gia = danh_gia;
    }

    public int getId_san_pham() {
        return id_san_pham;
    }

    public void setId_san_pham(int id_san_pham) {
        this.id_san_pham = id_san_pham;
    }

    public String getTen_san_pham() {
        return ten_san_pham;
    }

    public void setTen_san_pham(String ten_san_pham) {
        this.ten_san_pham = ten_san_pham;
    }

    public String getMo_ta() {
        return mo_ta;
    }

    public void setMo_ta(String mo_ta) {
        this.mo_ta = mo_ta;
    }

    public double getGia_san_pham() {
        return gia_san_pham;
    }

    public void setGia_san_pham(double gia_san_pham) {
        this.gia_san_pham = gia_san_pham;
    }

    public String getThuong_hieu() {
        return thuong_hieu;
    }

    public void setThuong_hieu(String thuong_hieu) {
        this.thuong_hieu = thuong_hieu;
    }

    public int getId_danh_muc() {
        return id_danh_muc;
    }

    public void setId_danh_muc(int id_danh_muc) {
        this.id_danh_muc = id_danh_muc;
    }


    public String getHinh_anh() {
        return hinh_anh;
    }

    public void setHinh_anh(String hinh_anh) {
        this.hinh_anh = hinh_anh;
    }
}
