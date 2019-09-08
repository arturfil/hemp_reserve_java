package com.arturofilio.hemp_reserve_java.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arturofilio.hemp_reserve_java.R;
import com.arturofilio.hemp_reserve_java.model.ProductModel;
import com.arturofilio.hemp_reserve_java.viewmodel.ListViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *  A simple (@Link Fragment) subclass.
 */
public class ListFragment extends Fragment {

    @BindView(R.id.productRefreshLayout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.listError)
    TextView listError;

    @BindView(R.id.productlist)
    RecyclerView productList;

    @BindView(R.id.loadingView)
    ProgressBar loadingView;

    private ListViewModel viewModel;
    private ProductListAdapter listAdapter =  new ProductListAdapter();

    public ListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(ListViewModel.class);

        viewModel.products.observe(this, list -> {
            if(list != null) {
                productList.setVisibility(View.VISIBLE);
                listAdapter.updateProductList(list);
            }
        });
        viewModel.loading.observe(this, loading -> {
            loadingView.setVisibility(loading ? View.VISIBLE : View.GONE);
            if(loading) {
                listError.setVisibility(View.GONE);
                productList.setVisibility(View.GONE);
            }
        });

        viewModel.loadError.observe(this, error -> {
            listError.setVisibility(error ? View.VISIBLE : View.GONE);
        });

        viewModel.refresh();

        if(productList != null) {
            productList.setLayoutManager(new GridLayoutManager(getContext(), 2));
            productList.setAdapter(listAdapter);
        }

        refreshLayout.setOnRefreshListener(() -> {
            productList.setVisibility(View.GONE);
            listError.setVisibility(View.GONE);
            loadingView.setVisibility(View.VISIBLE);
            viewModel.refresh();
            refreshLayout.setRefreshing(false);
        });
    }
}