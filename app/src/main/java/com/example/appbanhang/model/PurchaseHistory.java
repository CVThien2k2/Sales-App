package com.example.appbanhang.model;

import java.util.Date;

public class PurchaseHistory {
    private int idLichSuMuaHang;
    private int idNguoiDung;
    private int idDonHang;
    private Date ngayMuaHang;

    public PurchaseHistory(int idLichSuMuaHang, int idNguoiDung, int idDonHang, Date ngayMuaHang) {
        this.idLichSuMuaHang = idLichSuMuaHang;
        this.idNguoiDung = idNguoiDung;
        this.idDonHang = idDonHang;
        this.ngayMuaHang = ngayMuaHang;
    }

    // Các phương thức getter và setter cho các thuộc tính

    public int getIdLichSuMuaHang() {
        return idLichSuMuaHang;
    }

    public void setIdLichSuMuaHang(int idLichSuMuaHang) {
        this.idLichSuMuaHang = idLichSuMuaHang;
    }

    public int getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(int idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public int getIdDonHang() {
        return idDonHang;
    }

    public void setIdDonHang(int idDonHang) {
        this.idDonHang = idDonHang;
    }

    public Date getNgayMuaHang() {
        return ngayMuaHang;
    }

    public void setNgayMuaHang(Date ngayMuaHang) {
        this.ngayMuaHang = ngayMuaHang;
    }

}
