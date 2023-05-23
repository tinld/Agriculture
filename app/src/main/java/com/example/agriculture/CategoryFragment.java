package com.example.agriculture;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agriculture.adapter.CartAdapter;
import com.example.agriculture.adapter.MyAdapter;
import com.example.agriculture.model.CartProduct;
import com.example.agriculture.model.ProductItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class CategoryFragment extends Fragment {

    private RecyclerView recyclerView;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference("cart");
    private CartAdapter cartAdapter;
    private ArrayList<CartProduct> list;
    private TextView totalPrice;
    private Integer price=0;

    private Button payment;

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list = new ArrayList<>();

        recyclerView = view.findViewById(R.id.cartList);
        payment = view.findViewById(R.id.paymentBtn);
        totalPrice = view.findViewById(R.id.totalPrice);
        cartAdapter = new CartAdapter(this.getContext(), list);
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        price = 0;
        if(list.isEmpty()){
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                        CartProduct cart = dataSnapshot.getValue(CartProduct.class);
                        if(cart.getStatus().equals("Unpayment")){
                            price += cart.getProductPrice()*cart.getProductQuantity();
                            list.add(cart);
                        }
                    }
                    cartAdapter.notifyDataSetChanged();
                    String totalPriceText = "Total Price:   " + price.toString();
                    totalPrice.setText(totalPriceText);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Shipping.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("carts", list);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }
}