package com.arturofilio.hemp_reserve_java.model;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductApiService {

    private static final String BASE_URL = "http://thehempreserve.com";
    ProductApi api = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ProductApi.class);

    public Single<List<ProductModel>> getProdcuts() {
        return api.getProdcuts();
    }
}
