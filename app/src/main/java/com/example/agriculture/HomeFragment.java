package com.example.agriculture;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.agriculture.adapter.MyAdapter;
import com.example.agriculture.adapter.ProductAdapter;
import com.example.agriculture.model.ProductItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    ImageSlider imageSlider;
    private RecyclerView recyclerView;
    private ImageView profileImg;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference("products");
    FirebaseAuth auth = FirebaseAuth.getInstance();
    private MyAdapter myAdapter;
    private RecyclerView productCatRecycler;
    private TextView txtUserName;
    private ProductAdapter productAdapter;
    private SearchView searchProduct;
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
        profileImg = view.findViewById(R.id.imageView);
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

        searchProduct = view.findViewById(R.id.search_product);
        searchProduct.clearFocus();
        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getBundleExtra("userLogin");
        String userName = bundle.getString("nameKey");
        showUserProfile();
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

        searchProduct.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });

    }

    public void searchList(String text){
        ArrayList<ProductItem> searchList = new ArrayList<>();
        for(ProductItem productItem: list){
            if(productItem.getProductItemName().toLowerCase().contains(text.toLowerCase())){
                searchList.add(productItem);
            }
            myAdapter.searchDataList(searchList);
        }
    }

    private void showUserProfile() {
        String userID = auth.getCurrentUser().getUid();
        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("users");
        referenceProfile.addValueEventListener(new ValueEventListener() {
            String name, email, address, phone, image;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot keyId: snapshot.getChildren()){
                    if(keyId.child("id").getValue().equals(userID)){
                        name = keyId.child("name").getValue(String.class);
                        email = keyId.child("email").getValue(String.class);
                        address = keyId.child("address").getValue(String.class);
                        phone = keyId.child("phone").getValue(String.class);
                        image = keyId.child("dataImage").getValue(String.class);
                        break;
                    }
                }

                String hello = "Hello, " + name + "!";
                txtUserName.setText(hello);
                Glide.with(getContext()).load(image).into(profileImg);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}