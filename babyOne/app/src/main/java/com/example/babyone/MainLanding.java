package com.example.babyone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainLanding extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_landing);

        ViewPager2 viewPager = findViewById(R.id.viewPagerML);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new homeFragment());
        fragments.add(new profileFragment());
        fragments.add(new settingsFragment());

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(this, fragments);
        viewPager.setAdapter(adapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationViewML);
        bottomNavigationView.setOnItemSelectedListener(item -> {
                switch (item.getItemId()) {
                    case R.id.home:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.profile:
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.settings:
                        viewPager.setCurrentItem(2);
                        return true;
                    default:
                        return false;
                }
        });
    }
}