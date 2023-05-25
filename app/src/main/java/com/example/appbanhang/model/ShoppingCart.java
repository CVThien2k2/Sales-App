package com.example.appbanhang.model;

public class ShoppingCart {
    private int idGioHang;
    private int idNguoiDung;
    private int idSanPham;
    private int soLuongSanPham;

    public ShoppingCart(int idGioHang, int idNguoiDung, int idSanPham, int soLuongSanPham) {
        this.idGioHang = idGioHang;
        this.idNguoiDung = idNguoiDung;
        this.idSanPham = idSanPham;
        this.soLuongSanPham = soLuongSanPham;
    }

    // Các phương thức getter và setter cho các thuộc tính

    public int getIdGioHang() {
        return idGioHang;
    }

    public void setIdGioHang(int idGioHang) {
        this.idGioHang = idGioHang;
    }

    public int getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(int idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public int getSoLuongSanPham() {
        return soLuongSanPham;
    }

    public void setSoLuongSanPham(int soLuongSanPham) {
        this.soLuongSanPham = soLuongSanPham;
    }


}