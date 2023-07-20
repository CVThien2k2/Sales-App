package com.example.appbanhang.service;

import com.example.appbanhang.model.Product;
import com.example.appbanhang.model.ProductOrder;
import com.example.appbanhang.model.item_order;

import java.util.List;

public interface OnItemClickListenerOrder {
    void onItemClickProductOrder(ProductOrder product, List<item_order> list);
}
