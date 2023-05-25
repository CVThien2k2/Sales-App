package com.example.appbanhang.model;

import java.util.Date;

public class ProductReview {
    private int idDanhGia;
    private String noiDung;
    private double diemDanhGia;
    private Date ngayDanhGia;
    private int idNguoiDung;
    private int idSanPham;

    public ProductReview(int idDanhGia, String noiDung, double diemDanhGia,
                         Date ngayDanhGia, int idNguoiDung, int idSanPham) {
        this.idDanhGia = idDanhGia;
        this.noiDung = noiDung;
        this.diemDanhGia = diemDanhGia;
        this.ngayDanhGia = ngayDanhGia;
        this.idNguoiDung = idNguoiDung;
        this.idSanPham = idSanPham;
    }



    public int getIdDanhGia() {
        return idDanhGia;
    }

    public void setIdDanhGia(int idDanhGia) {
        this.idDanhGia = idDanhGia;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public double getDiemDanhGia() {
        return diemDanhGia;
    }

    public void setDiemDanhGia(double diemDanhGia) {
        this.diemDanhGia = diemDanhGia;
    }

    public Date getNgayDanhGia() {
        return ngayDanhGia;
    }

    public void setNgayDanhGia(Date ngayDanhGia) {
        this.ngayDanhGia = ngayDanhGia;
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


}

