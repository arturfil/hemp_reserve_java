package com.arturofilio.hemp_reserve_java.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.arturofilio.hemp_reserve_java.model.ProductApi;
import com.arturofilio.hemp_reserve_java.model.ProductApiService;
import com.arturofilio.hemp_reserve_java.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends ViewModel {

    private ProductApiService apiService = new ProductApiService();
    private CompositeDisposable disposable = new CompositeDisposable();

    public MutableLiveData<List<ProductModel>> products = new MutableLiveData<>();
    public MutableLiveData<Boolean> loadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    public void refresh() {
        loading.setValue(true);
        getProducts();
    }

    private void getProducts() {
        disposable.add(
                apiService.getProdcuts()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<ProductModel>>() {

                            @Override
                            public void onSuccess(List<ProductModel> productModels) {
                                loadError.setValue(false);
                                loading.setValue(false);
                                products.setValue(productModels);
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                loading.setValue(false);
                                loadError.setValue(true);
                            }
                        })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
