package com.example.agriculture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.example.agriculture.R;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewParent;
import android.widget.TextView;

import com.example.agriculture.adapter.MyAdapter;
import com.example.agriculture.adapter.ProductAdapter;
import com.example.agriculture.model.ProductItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//
    BottomNavigationView bottomNavigationView;
//    RecyclerView recyclerView;
//    DatabaseReference database;
//    MyAdapter myAdapter;
//    RecyclerView productCatRecycler;
//    TextView txtUserName;
//    ProductAdapter productAdapter;
//
    static HomeFragment homeFragment = new HomeFragment();
    static CategoryFragment categoryFragment = new CategoryFragment();
    static SettingFragment settingFragment = new SettingFragment();
    static PostProductFragment postProductFragment = new PostProductFragment();
    static NewsFragment newsFragment = new NewsFragment();
    ViewPager viewPager;
//    ArrayList<ProductItem> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
//        viewPager = findViewById(R.id.flFragment);
//        viewPager.setAdapter(pagerAdapter);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.homeMenu){
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment).commit();
                } else if (id == R.id.categoryMenu) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, categoryFragment).commit();
                }else if (id == R.id.postMenu){
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, postProductFragment).commit();
                }else if (id == R.id.newsMenu){
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, newsFragment).commit();
                }else if (id == R.id.settingsMenu){
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, settingFragment).commit();
                }
                return false;
            }
        });
    }

    private static class PagerAdapter extends FragmentPagerAdapter {
        private static final int NUM_PAGES = 5;

        public PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return homeFragment;
                case 1:
                    return categoryFragment;
                case 2:
                    return postProductFragment;
                case 3:
                    return newsFragment;
                case 4:
                    return settingFragment;
                default:
                    return null;
            }
        }
    }
}