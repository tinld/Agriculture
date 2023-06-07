package com.example.agriculture.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.agriculture.ProductDetails;
import com.example.agriculture.model.CartProduct;
import com.example.agriculture.model.ProductItem;
import com.example.agriculture.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    Context context;
    ArrayList<CartProduct> list;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    public CartAdapter(Context context, ArrayList<CartProduct> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CartProduct cart = list.get(position);
        String nameProduct = "Name: " + cart.getProductItemName();
        String quantityProduct = "Quantity: " +  String.valueOf(cart.getProductQuantity());
        String priceProduct = "Price: " + String.valueOf(cart.getProductPrice());
        holder.nameProduct.setText(nameProduct);
        holder.quantityProduct.setText(quantityProduct);
        holder.priceProduct.setText(priceProduct);
        Glide.with(context).load(cart.getDataImage()).into(holder.imageProduct);

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new AlertDialog.Builder(context)
//                        .setTitle("Delete item")
//                        .setMessage("Do you really want to delete item")
//                        .setNegativeButton("CANCEL", (dialog1, which) -> dialog1.dismiss())
//                        .setPositiveButton("OK", (dialog12, which) -> {
//                            FirebaseDatabase.getInstance()
//                                    .getReference("cart")
//                                    .child(cart.getKey())
//                                    .removeValue()
//                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
//
//                                        }
//                                    });
//                        });
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Do you really want to delete item?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                FirebaseDatabase.getInstance()
                                    .getReference("cart")
                                    .child(cart.getKey())
                                    .removeValue()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                        }
                                    });
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void deleteFromFirebase(CartProduct cartProduct, View v){
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nameProduct, quantityProduct, priceProduct;
        ImageView imageProduct;
        ImageButton deleteBtn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameProduct = itemView.findViewById(R.id.nameProduct);
            quantityProduct = itemView.findViewById(R.id.quantityProduct);
            priceProduct = itemView.findViewById(R.id.priceProduct);
            imageProduct = itemView.findViewById(R.id.productItemImg);
            deleteBtn = itemView.findViewById(R.id.cancelCartProduct);
        }
    }
}
