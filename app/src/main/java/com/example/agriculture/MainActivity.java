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
        viewPager = findViewById(R.id.flFragment);
        viewPager.setAdapter(pagerAdapter);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeMenu:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.categoryMenu:
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.postMenu:
                        viewPager.setCurrentItem(2);
                        return true;
                    case R.id.newsMenu:
                        viewPager.setCurrentItem(3);
                        return true;
                    case R.id.settingsMenu:
                        viewPager.setCurrentItem(4);
                        return true;
                    default:
                        return false;
                }
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Không cần thực hiện gì trong phương thức này
            }

            @Override
            public void onPageSelected(int position) {
                // Cập nhật item được chọn trong BottomNavigationView khi vuốt màn hình
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Không cần thực hiện gì trong phương thức này
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