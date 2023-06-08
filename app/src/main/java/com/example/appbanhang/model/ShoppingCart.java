package com.example.appbanhang.model;

public class ShoppingCart {
    private int id_gio_hang;
    private int id_nguoi_dung;
    private int id_san_pham;
    private int so_luong_san_pham;
    private Product product;
    private boolean isChecked = false;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public ShoppingCart(int id_gio_hang, int id_nguoi_dung, int id_san_pham, int so_luong_san_pham, Product product) {
        this.id_gio_hang = id_gio_hang;
        this.id_nguoi_dung = id_nguoi_dung;
        this.id_san_pham = id_san_pham;
        this.so_luong_san_pham = so_luong_san_pham;
        this.product = product;
    }

    public int getId_gio_hang() {
        return id_gio_hang;
    }

    public void setId_gio_hang(int id_gio_hang) {
        this.id_gio_hang = id_gio_hang;
    }

    public int getId_nguoi_dung() {
        return id_nguoi_dung;
    }

    public void setId_nguoi_dung(int id_nguoi_dung) {
        this.id_nguoi_dung = id_nguoi_dung;
    }

    public int getId_san_pham() {
        return id_san_pham;
    }

    public void setId_san_pham(int id_san_pham) {
        this.id_san_pham = id_san_pham;
    }

    public int getSo_luong_san_pham() {
        return so_luong_san_pham;
    }

    public void setSo_luong_san_pham(int so_luong_san_pham) {
        this.so_luong_san_pham = so_luong_san_pham;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}