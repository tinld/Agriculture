package com.example.agriculture;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agriculture.adapter.CartAdapter;
import com.example.agriculture.adapter.MyAdapter;
import com.example.agriculture.model.CartProduct;
import com.example.agriculture.model.ProductItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class NewsFragment extends Fragment {
    ArrayList<CartProduct> list;
    private RecyclerView recyclerView;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference("cart");
    private CartAdapter cartAdapter;
    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        list = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerview_id);
        cartAdapter = new CartAdapter(this.getContext(), list);
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        if(list.isEmpty()){
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                        CartProduct cart = dataSnapshot.getValue(CartProduct.class);
                        if(cart.getStatus().equals("Payment")){
                            list.add(cart);
                        }
                        cartAdapter.notifyDataSetChanged();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}