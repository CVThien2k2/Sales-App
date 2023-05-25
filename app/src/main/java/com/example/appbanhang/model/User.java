package com.example.appbanhang.model;

public class User {
    private int idNguoiDung;
    private String tenDangNhap;
    private String matKhau;
    private String hoTen;
    private String email;
    private String diaChi;
    private String soDienThoai;
    private UserType loaiNguoiDung;

    public User(int idNguoiDung, String tenDangNhap, String matKhau, String hoTen, String email, String diaChi, String soDienThoai, UserType loaiNguoiDung) {
        this.idNguoiDung = idNguoiDung;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.email = email;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.loaiNguoiDung = loaiNguoiDung;
    }

    // Các phương thức getter và setter cho các thuộc tính

    public int getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(int idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public UserType getLoaiNguoiDung() {
        return loaiNguoiDung;
    }

    public void setLoaiNguoiDung(UserType loaiNguoiDung) {
        this.loaiNguoiDung = loaiNguoiDung;
    }

    // Enum để định nghĩa các giá trị cho loại người dùng

    public enum UserType {
        CHU_CUA_HANG,
        KHACH_HANG
    }


}
