package com.arturofilio.hemp_reserve_java.view;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arturofilio.hemp_reserve_java.R;
import com.arturofilio.hemp_reserve_java.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    private ArrayList<ProductModel> productList = new ArrayList<>();

    public void updateProductList(List<ProductModel> newProductList) {
        productList.clear();
        productList.addAll(newProductList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ImageView productImage = holder.itemView.findViewById(R.id.productImage);
        TextView productName = holder.itemView.findViewById(R.id.productName);

        productName.setText(productList.get(position).name);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        public ProductViewHolder(View view) {
            super(view);
        }
    }

}
