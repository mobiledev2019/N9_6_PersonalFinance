package com.example.qltc;

import android.Manifest;
import android.content.pm.PackageManager;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.qltc.fragment.ListGhiChepMauFragment;
import com.example.qltc.fragment.HanMucChiFragment;
import com.example.qltc.fragment.KhacFragment;
import com.example.qltc.fragment.ChiTienFragment;
import com.example.qltc.fragment.ThuTienFragment;
import com.example.qltc.fragment.TongQuanFragment;



public class MainActivity extends AppCompatActivity {
    private final static int REQUEST_CODE_PERMISSION_READ_SMS = 0;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String newString="";
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TongQuanFragment()).commit();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, REQUEST_CODE_PERMISSION_READ_SMS);
            }
        }
        if(savedInstanceState==null){
            Bundle extras =getIntent().getExtras();
            if(extras==null){
                newString =null;
            }else{
                newString = extras.getString("Loai");
                String nganHang=extras.getString("NganHang");
                long tien = extras.getLong("Tien");
                if(newString.compareTo("ChiTien")==0){
                    Bundle bundle = new Bundle();
                    bundle.putString("Loai",newString);
                    bundle.putString("Nganhang",nganHang);
                    bundle.putLong("Tien",tien);
                    ChiTienFragment chiTienFragment = new ChiTienFragment();
                    chiTienFragment.setArguments(bundle);
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container,chiTienFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }else if(newString.compareTo("ThuTien")==0){
                    Bundle bundle = new Bundle();
                    bundle.putString("Loai",newString);
                    bundle.putString("Nganhang",nganHang);
                    bundle.putLong("Tien",tien);
                    ThuTienFragment chiTienFragment = new ThuTienFragment();
                    chiTienFragment.setArguments(bundle);
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container,chiTienFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        }else{
            newString = (String) savedInstanceState.getSerializable("Loai");

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION_READ_SMS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Thank for permitting", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Well I can't do anything until you permit me", Toast.LENGTH_LONG).show();

                }
            }
        }
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
                    selectedFragment = new ListGhiChepMauFragment();
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
