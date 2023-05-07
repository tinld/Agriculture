package com.example.agriculture.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.agriculture.ProductDetails;
import com.example.agriculture.model.ProductItem;
import com.example.agriculture.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<ProductItem> list;

    public MyAdapter(Context context, ArrayList<ProductItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ProductItem product = list.get(position);
        holder.productItemPrice.setText(String.valueOf(product.getProductItemPrice() +" VND/" + product.getUnitItem()));
        holder.productItemName.setText(product.getProductItemName());
        holder.getProductIteQty.setText(String.valueOf(product.getGetProductIteQty()));
        Glide.with(context).load(product.getDataImage()).into(holder.dataImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ProductDetails.class);
                Bundle itemData = new Bundle();
                itemData.putString("Image", product.getDataImage());
                itemData.putString("Name", product.getProductItemName());
                itemData.putString("Unit", product.getUnitItem());
                itemData.putInt("Price", product.getProductItemPrice());
                itemData.putInt("Number", product.getGetProductIteQty());
                i.putExtras(itemData);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView getProductIteQty, locationItem, productItemName, productItemPrice, unitItem;
        ImageView dataImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            getProductIteQty = itemView.findViewById(R.id.tv_qty);
            productItemName = itemView.findViewById(R.id.productItemName);
            productItemPrice = itemView.findViewById(R.id.tv_price);
            dataImage = itemView.findViewById(R.id.productItemImg);
        }
    }
}
