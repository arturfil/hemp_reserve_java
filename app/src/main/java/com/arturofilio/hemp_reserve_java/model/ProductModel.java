package com.arturofilio.hemp_reserve_java.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ProductModel {

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    @SerializedName("price")
    public int price;

    @SerializedName("category")
    public Category category;

    public ProductModel(String name) {
        this.name = name;
    }

}

class Category {
    public String name;
}
