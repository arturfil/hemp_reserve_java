package com.arturofilio.hemp_reserve_java.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.arturofilio.hemp_reserve_java.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends ViewModel {

    public MutableLiveData<List<ProductModel>> products = new MutableLiveData<>();
    public MutableLiveData<Boolean> loadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    public void refresh() {
        ProductModel product1 = new ProductModel("gummies");
        ProductModel product2 = new ProductModel("cbd_drops");
        ProductModel product3 = new ProductModel("cbd_beer");

        List<ProductModel> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);

        products.setValue(productList);
        loadError.setValue(false);
        loading.setValue(false);
    }

}
