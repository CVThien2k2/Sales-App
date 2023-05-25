package com.example.appbanhang.model;

public class Product {
    private int id_san_pham;
    private String ten_san_pham;
    private String mo_ta;
    private double gia_san_pham;
    private String thuong_hieu;
    private int id_danh_muc;
    private int so_luong;
    private double danh_gia;
    private int da_ban;
    private int con_lai;
    private String kich_thuoc;

    private String hinh_anh;

    public Product() {
    }

    public Product(int id_san_pham, String ten_san_pham, String mo_ta) {
        this.id_san_pham = id_san_pham;
        this.ten_san_pham = ten_san_pham;
        this.mo_ta = mo_ta;
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

    public int getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }

    public double getDanh_gia() {
        return danh_gia;
    }

    public void setDanh_gia(double danh_gia) {
        this.danh_gia = danh_gia;
    }

    public int getDa_ban() {
        return da_ban;
    }

    public void setDa_ban(int da_ban) {
        this.da_ban = da_ban;
    }

    public int getCon_lai() {
        return con_lai;
    }

    public void setCon_lai(int con_lai) {
        this.con_lai = con_lai;
    }

    public String getKich_thuoc() {
        return kich_thuoc;
    }

    public void setKich_thuoc(String kich_thuoc) {
        this.kich_thuoc = kich_thuoc;
    }

    public String getHinh_anh() {
        return hinh_anh;
    }

    public void setHinh_anh(String hinh_anh) {
        this.hinh_anh = hinh_anh;
    }
}
