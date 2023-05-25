package com.example.appbanhang.model;

import java.math.BigDecimal;
import java.util.Date;

public class ProductOrder {
    private int idDonHang;
    private int idNguoiDung;
    private Date ngayDatHang;
    private int idSanPham;
    private int soLuongSanPham;
    private BigDecimal gia;
    private Date ngayGiaoHang;
    private String trangThaiDonHang;

    public ProductOrder(int idDonHang, int idNguoiDung, Date ngayDatHang, int idSanPham,
                        int soLuongSanPham, BigDecimal gia, Date ngayGiaoHang, String trangThaiDonHang) {
        this.idDonHang = idDonHang;
        this.idNguoiDung = idNguoiDung;
        this.ngayDatHang = ngayDatHang;
        this.idSanPham = idSanPham;
        this.soLuongSanPham = soLuongSanPham;
        this.gia = gia;
        this.ngayGiaoHang = ngayGiaoHang;
        this.trangThaiDonHang = trangThaiDonHang;
    }

    // Các phương thức getter và setter cho các thuộc tính

    public int getIdDonHang() {
        return idDonHang;
    }

    public void setIdDonHang(int idDonHang) {
        this.idDonHang = idDonHang;
    }

    public int getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(int idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public Date getNgayDatHang() {
        return ngayDatHang;
    }

    public void setNgayDatHang(Date ngayDatHang) {
        this.ngayDatHang = ngayDatHang;
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

    public BigDecimal getGia() {
        return gia;
    }

    public void setGia(BigDecimal gia) {
        this.gia = gia;
    }

    public Date getNgayGiaoHang() {
        return ngayGiaoHang;
    }

    public void setNgayGiaoHang(Date ngayGiaoHang) {
        this.ngayGiaoHang = ngayGiaoHang;
    }

    public String getTrangThaiDonHang() {
        return trangThaiDonHang;
    }

    public void setTrangThaiDonHang(String trangThaiDonHang) {
        this.trangThaiDonHang = trangThaiDonHang;
    }


}

