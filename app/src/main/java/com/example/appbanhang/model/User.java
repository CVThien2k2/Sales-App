package com.example.appbanhang.model;

import java.io.Serializable;

public class User implements Serializable {

    private int id_nguoi_dung;
    private String ten_dang_nhap;
    private String mat_khau;
    private String ho_ten;
    private String email;
    private String dia_chi;
    private String so_dien_thoai;
    private UserType loai_nguoi_dung;
    public User(){
    }
    public User(int id_nguoi_dung, String ten_dang_nhap, String mat_khau, String ho_ten, String email, String dia_chi, String so_dien_thoai, UserType loai_nguoi_dung) {
        this.id_nguoi_dung = id_nguoi_dung;
        this.ten_dang_nhap = ten_dang_nhap;
        this.mat_khau = mat_khau;
        this.ho_ten = ho_ten;
        this.email = email;
        this.dia_chi = dia_chi;
        this.so_dien_thoai = so_dien_thoai;
        this.loai_nguoi_dung = loai_nguoi_dung;
    }

    public int getId_nguoi_dung() {
        return id_nguoi_dung;
    }

    public void setId_nguoi_dung(int id_nguoi_dung) {
        this.id_nguoi_dung = id_nguoi_dung;
    }

    public String getTen_dang_nhap() {
        return ten_dang_nhap;
    }

    public void setTen_dang_nhap(String ten_dang_nhap) {
        this.ten_dang_nhap = ten_dang_nhap;
    }

    public String getMat_khau() {
        return mat_khau;
    }

    public void setMat_khau(String mat_khau) {
        this.mat_khau = mat_khau;
    }

    public String getHo_ten() {
        return ho_ten;
    }

    public void setHo_ten(String ho_ten) {
        this.ho_ten = ho_ten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    public String getSo_dien_thoai() {
        return so_dien_thoai;
    }

    public void setSo_dien_thoai(String so_dien_thoai) {
        this.so_dien_thoai = so_dien_thoai;
    }

    public UserType getLoai_nguoi_dung() {
        return loai_nguoi_dung;
    }

    public void setLoai_nguoi_dung(UserType loai_nguoi_dung) {
        this.loai_nguoi_dung = loai_nguoi_dung;
    }

    // Enum để định nghĩa các giá trị cho loại người dùng

    public enum UserType {
        CHU_CUA_HANG,
        KHACH_HANG
    }


}
