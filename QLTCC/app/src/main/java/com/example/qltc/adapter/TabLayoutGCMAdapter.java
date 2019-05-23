package com.example.qltc.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.qltc.fragment.ThemGhiChepMauChiTienFragment;
import com.example.qltc.fragment.ThemGhiChepMauThuTienFragment;

public class TabLayoutGCMAdapter extends FragmentStatePagerAdapter {
    private String listTab[] = {"CHI TIỀN","THU TIỀN"};
    private ThemGhiChepMauChiTienFragment firstFragment;
    private ThemGhiChepMauThuTienFragment secondFragment;

    public TabLayoutGCMAdapter(FragmentManager fm) {
        super(fm);
        firstFragment = new ThemGhiChepMauChiTienFragment();
        secondFragment = new ThemGhiChepMauThuTienFragment();

    }

    @Override
    public Fragment getItem(int i) {
        if(i == 0){
           return firstFragment;
        }else if(i ==1){
            return secondFragment;
        }else{

        }
        return null;
    }

    @Override
    public int getCount() {
        return listTab.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTab[position];
    }
}
