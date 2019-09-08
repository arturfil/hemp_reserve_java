package com.arturofilio.hemp_reserve_java.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ProductModel {

    public String name;
    public String description;
    public int price;
    public String category;

    public ProductModel(String name) {
        this.name = name;
    }

}
