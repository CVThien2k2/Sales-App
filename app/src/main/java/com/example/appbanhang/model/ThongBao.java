package com.example.appbanhang.model;

import java.util.Date;

public class ThongBao {
    private int idThongBao;
    private int idNguoiDung;
    private String tieuDe;
    private String noiDung;
    private Date ngayGui;

    public ThongBao(int idThongBao, int idNguoiDung, String tieuDe, String noiDung, Date ngayGui) {
        this.idThongBao = idThongBao;
        this.idNguoiDung = idNguoiDung;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.ngayGui = ngayGui;
    }

    // Các phương thức getter và setter cho các thuộc tính

    public int getIdThongBao() {
        return idThongBao;
    }

    public void setIdThongBao(int idThongBao) {
        this.idThongBao = idThongBao;
    }

    public int getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(int idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Date getNgayGui() {
        return ngayGui;
    }

    public void setNgayGui(Date ngayGui) {
        this.ngayGui = ngayGui;
    }


}
