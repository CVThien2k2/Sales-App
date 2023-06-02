package com.example.appbanhang.model;

public class ProductCategory {
    private int id_danh_muc;
    private String ten_danh_muc;

    private String gioi_tinh;

    public ProductCategory(int id_danh_muc, String ten_danh_muc, String gioi_tinh) {
        this.id_danh_muc = id_danh_muc;
        this.ten_danh_muc = ten_danh_muc;
        this.gioi_tinh = gioi_tinh;
    }

    public int getId_danh_muc() {
        return id_danh_muc;
    }

    public void setId_danh_muc(int id_danh_muc) {
        this.id_danh_muc = id_danh_muc;
    }

    public String getTen_danh_muc() {
        return ten_danh_muc;
    }

    public void setTen_danh_muc(String ten_danh_muc) {
        this.ten_danh_muc = ten_danh_muc;
    }

    public String getGioi_tinh() {
        return gioi_tinh;
    }

    public void setGioi_tinh(String gioi_tinh) {
        this.gioi_tinh = gioi_tinh;
    }
}
