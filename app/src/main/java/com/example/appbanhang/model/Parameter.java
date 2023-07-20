package com.example.appbanhang.model;

import java.io.Serializable;

public class Parameter implements Serializable {
    private int id_thong_so;
    private int id_san_pham;
    private int so_luong;
    private int con_lai;
    private String kich_thuoc;


    public int getId_thong_so() {
        return id_thong_so;
    }

    public void setId_thong_so(int id_thong_so) {
        this.id_thong_so = id_thong_so;
    }
    public Parameter(){

    };

    public Parameter(int id_thong_so, int id_san_pham, int so_luong, int con_lai, String kich_thuoc) {
        this.id_thong_so = id_thong_so;
        this.id_san_pham = id_san_pham;
        this.so_luong = so_luong;
        this.con_lai = con_lai;
        this.kich_thuoc = kich_thuoc;
    }

    public int getId_san_pham() {
        return id_san_pham;
    }

    public void setId_san_pham(int id_san_pham) {
        this.id_san_pham = id_san_pham;
    }

    public int getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }

    public int getCon_lai() {
        return con_lai;
    }

    public void setCon_lai(int con_lai) {
        this.con_lai = con_lai;
    }

    public String getKich_thuoc() {
        return kich_thuoc;
    }

    public void setKich_thuoc(String kich_thuoc) {
        this.kich_thuoc = kich_thuoc;
    }
}
