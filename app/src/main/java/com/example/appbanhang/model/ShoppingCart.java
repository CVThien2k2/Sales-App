package com.example.appbanhang.model;

public class ShoppingCart {
    private int id_gio_hang;
    private int id_nguoi_dung;

    public ShoppingCart(int id_gio_hang, int id_nguoi_dung) {
        this.id_gio_hang = id_gio_hang;
        this.id_nguoi_dung = id_nguoi_dung;
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
}