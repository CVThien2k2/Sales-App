package com.example.appbanhang.model;

public class item_cart {
    private int id_item_gio_hang;
    private int id_nguoi_dung;
    private int so_luong_san_pham;
    private int trang_thai;
    private Product product;
    private Parameter parameter;

    public item_cart() {
    }

    public int getId_nguoi_dung() {
        return id_nguoi_dung;
    }

    public item_cart(int id_item_gio_hang, int id_nguoi_dung, int so_luong_san_pham, int trang_thai, Product product, Parameter parameter) {
        this.id_item_gio_hang = id_item_gio_hang;
        this.id_nguoi_dung = id_nguoi_dung;
        this.so_luong_san_pham = so_luong_san_pham;
        this.trang_thai = trang_thai;
        this.product = product;
        this.parameter = parameter;
    }

    public void setId_nguoi_dung(int id_nguoi_dung) {
        this.id_nguoi_dung = id_nguoi_dung;
    }

    public int getId_item_gio_hang() {
        return id_item_gio_hang;
    }

    public void setId_item_gio_hang(int id_item_gio_hang) {
        this.id_item_gio_hang = id_item_gio_hang;
    }



    public int getSo_luong_san_pham() {
        return so_luong_san_pham;
    }

    public void setSo_luong_san_pham(int so_luong_san_pham) {
        this.so_luong_san_pham = so_luong_san_pham;
    }

    public int getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(int trang_thai) {
        this.trang_thai = trang_thai;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Parameter getParameter() {
        return parameter;
    }


    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }
}
