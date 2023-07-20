package com.example.appbanhang.model;

import java.io.Serializable;

public class item_order {
    private int id_item_don_hang;
    private int id_don_hang;
    private int so_luong_san_pham;
    private Product product;
    private Parameter parameter;

    public item_order (){}
    public item_order(int id_item_don_hang, int id_don_hang, int so_luong_san_pham, Product product, Parameter parameter) {
        this.id_item_don_hang = id_item_don_hang;
        this.id_don_hang = id_don_hang;
        this.so_luong_san_pham = so_luong_san_pham;
        this.product = product;
        this.parameter = parameter;
    }

    public int getId_item_don_hang() {
        return id_item_don_hang;
    }

    public void setId_item_don_hang(int id_item_don_hang) {
        this.id_item_don_hang = id_item_don_hang;
    }

    public int getId_don_hang() {
        return id_don_hang;
    }

    public void setId_don_hang(int id_don_hang) {
        this.id_don_hang = id_don_hang;
    }

    public int getSo_luong_san_pham() {
        return so_luong_san_pham;
    }

    public void setSo_luong_san_pham(int so_luong_san_pham) {
        this.so_luong_san_pham = so_luong_san_pham;
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
