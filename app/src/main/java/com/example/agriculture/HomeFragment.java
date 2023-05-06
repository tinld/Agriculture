package com.example.agriculture;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agriculture.adapter.MyAdapter;
import com.example.agriculture.adapter.ProductAdapter;
import com.example.agriculture.model.ProductItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
//
//    RecyclerView recyclerView;
//    DatabaseReference database;
//    MyAdapter myAdapter;
//    RecyclerView productCatRecycler;
//    TextView txtUserName;
//    ProductAdapter productAdapter;
//
//    HomeFragment homeFragment = new HomeFragment();
//    CategoryFragment categoryFragment = new CategoryFragment();
//    SettingFragment settingFragment = new SettingFragment();
//    PostProductFragment postProductFragment = new PostProductFragment();
//    NewsFragment newsFragment = new NewsFragment();
//    ArrayList<ProductItem> list;
//    BottomNavigationView bottomNavigationView;
//    Button a;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
//        super.onViewCreated(view, savedInstanceState);
//
//        txtUserName = view.findViewById(R.id.tv_user);
//
//        recyclerView = view.findViewById(R.id.recycleProduct);
//        database = FirebaseDatabase.getInstance().getReference("products");
//        recyclerView.setHasFixedSize(true);
//
//        list = new ArrayList<>();
//        myAdapter = new MyAdapter(this, list);
//        recyclerView.setAdapter(myAdapter);
//
//        database.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
//                    ProductItem product = dataSnapshot.getValue(ProductItem.class);
//                    list.add(product);
//                }
//                myAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//
//    }

}