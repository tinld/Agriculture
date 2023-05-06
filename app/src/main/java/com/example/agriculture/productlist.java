package com.example.agriculture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.agriculture.adapter.MyAdapter;
import com.example.agriculture.adapter.ProductAdapter;
import com.example.agriculture.model.ProductCategory;
import com.example.agriculture.model.ProductItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class productlist extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter myAdapter;
    RecyclerView productCatRecycler;
    TextView txtUserName;
    ProductAdapter productAdapter;

    HomeFragment homeFragment = new HomeFragment();
    CategoryFragment categoryFragment = new CategoryFragment();
    SettingFragment settingFragment = new SettingFragment();
    PostProductFragment postProductFragment = new PostProductFragment();
    NewsFragment newsFragment = new NewsFragment();
    ArrayList<ProductItem> list;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productlist);

//        txtUserName = findViewById(R.id.tv_user);
//        Intent intent = getIntent();
//        Bundle bundle = intent.getBundleExtra("userLogin");
//        String userName = bundle.getString("nameKey");
//        txtUserName.setText("Hello, " + userName);
//        recyclerView = findViewById(R.id.recycleProduct);
//        database = FirebaseDatabase.getInstance().getReference("products");
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        list = new ArrayList<>();
//        myAdapter = new MyAdapter(this, list);
//        recyclerView.setAdapter(myAdapter);
//        bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment);
//        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//                if(id == R.id.homeMenu){
//                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment).commit();
//                } else if (id == R.id.categoryMenu) {
//                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, categoryFragment).commit();
//                }else if (id == R.id.postMenu){
//                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, postProductFragment).commit();
//                }else if (id == R.id.newsMenu){
//                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, newsFragment).commit();
//                }else if (id == R.id.settingsMenu){
//                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, settingFragment).commit();
//                }
//                return false;
//            }
//        });

//        List<ProductCategory> productCategoryList = new ArrayList<>();
//        productCategoryList.add(new ProductCategory(1, "Most Popular"));
//        productCategoryList.add(new ProductCategory(1, "All Products"));
//        productCategoryList.add(new ProductCategory(1, "Fruits"));
//        productCategoryList.add(new ProductCategory(1, "Vegetable"));
//        productCategoryList.add(new ProductCategory(1, "Cabbage"));
//        setProductCatRecycler(productCategoryList);
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
    }

//    private NavigationBarView.OnItemReselectedListener mOnNavigationItemSelectedListener = new NavigationBarView.OnItemReselectedListener() {
//        @Override
//        public void onNavigationItemReselected(@NonNull MenuItem item) {
//            Fragment fragment;
//            int id = item.getItemId();
//            if(id == R.id.homeMenu){
//                toolbar.setTitle("Home");
//                fragment = new HomeFragment();
//                loadFragment(fragment);
//            } else if (id == R.id.categoryMenu) {
//                toolbar.setTitle("Category");
//                fragment = new CategoryFragment();
//                loadFragment(fragment);
//            }else if (id == R.id.postMenu){
//                toolbar.setTitle("Post");
//                fragment = new PostProductFragment();
//                loadFragment(fragment);
//            }else if (id == R.id.newsMenu){
//                toolbar.setTitle("News");
//                fragment = new NewsFragment();
//                loadFragment(fragment);
//            }else if (id == R.id.settingsMenu){
//                toolbar.setTitle("Settings");
//                fragment = new SettingFragment();
//                loadFragment(fragment);
//            }
//        }
//    };
//    private void setProductCatRecycler(List<ProductCategory> productCategoryList){
//        productCatRecycler = findViewById(R.id.cat_recycler);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
//        productCatRecycler.setLayoutManager(layoutManager);
//        productAdapter = new ProductAdapter(this, productCategoryList);
//        productCatRecycler.setAdapter(productAdapter);
//    }
//
//
//    private void loadFragment(Fragment fragment) {
//        // load fragment
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.flFragment, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }

}