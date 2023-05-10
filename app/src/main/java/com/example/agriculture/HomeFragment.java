package com.example.agriculture;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.agriculture.adapter.MyAdapter;
import com.example.agriculture.adapter.ProductAdapter;
import com.example.agriculture.model.ProductItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    ImageSlider imageSlider;
    private RecyclerView recyclerView;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference("products");
    private MyAdapter myAdapter;
    private RecyclerView productCatRecycler;
    private TextView txtUserName;
    private ProductAdapter productAdapter;
    ArrayList<ProductItem> list;
    BottomNavigationView bottomNavigationView;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        imageSlider = view.findViewById(R.id.imageSlider);

        ArrayList<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.h1, null));
        imageList.add(new SlideModel(R.drawable.h2, null));
        imageList.add(new SlideModel(R.drawable.h3, null));
        imageList.add(new SlideModel(R.drawable.h4, null));

        imageSlider.setImageList(imageList);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        txtUserName = view.findViewById(R.id.tv_user);

        recyclerView = view.findViewById(R.id.recycleProduct);
        recyclerView.setHasFixedSize(true);

        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getBundleExtra("userLogin");
        String userName = bundle.getString("nameKey");
        txtUserName.setText("Hello, " + userName);

        list = new ArrayList<>();
        myAdapter = new MyAdapter(this.getContext(), list);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        if(list.isEmpty()){
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                        ProductItem product = dataSnapshot.getValue(ProductItem.class);
                        list.add(product);
                    }
                    myAdapter.notifyDataSetChanged();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }




}