package com.arturofilio.hemp_reserve_java.model;

import java.util.Date;

public class ProductModel {

    public String name;
    public String description;
    public int price;
    public Category category;
    public String photo;

    public ProductModel(String name) {
        this.name = name;
    }

}

class Category {
    String name;
}
