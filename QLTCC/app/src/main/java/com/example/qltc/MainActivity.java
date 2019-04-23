package com.example.qltc;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.qltc.fragment.GhiChepMauFragment;
import com.example.qltc.fragment.HanMucChiFragment;
import com.example.qltc.fragment.HangMucChiFragment;
import com.example.qltc.fragment.KhacFragment;
import com.example.qltc.fragment.TaiKhoanFragment;
import com.example.qltc.fragment.ChiTienFragment;
import com.example.qltc.fragment.ThemGhiChepMauFragment;
import com.example.qltc.fragment.TongQuanFragment;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        toolbar = (Toolbar) findViewById(R.id.tbTongQuan);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Tổng quan");

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new TongQuanFragment()).commit();

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;
            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = new TongQuanFragment();
                    break;
                case R.id.nav_account:
                    selectedFragment = new GhiChepMauFragment();
                    break;
                case R.id.nav_plus:
                    selectedFragment = new ChiTienFragment();
                    break;
                case R.id.nav_limit_pay:
                    selectedFragment = new HanMucChiFragment();
                    break;
                case R.id.three_dot:
                    selectedFragment = new KhacFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };

}
