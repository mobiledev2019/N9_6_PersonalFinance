package com.example.qltc.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.qltc.R;
import com.example.qltc.adapter.TabLayoutGCMAdapter;

public class ThemGhiChepMauFragment extends Fragment {
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private ImageView backToList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_them_gcm, container, false);
        mViewPager = view.findViewById(R.id.viewpager_id);
        mViewPager.setAdapter(new TabLayoutGCMAdapter(getFragmentManager()));
        tabLayout = view.findViewById(R.id.tablayout_id);
        tabLayout.setupWithViewPager(mViewPager);
        backToList = view.findViewById(R.id.backToList);


        backToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListGhiChepMauFragment listGhiChepMauFragment = new ListGhiChepMauFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, listGhiChepMauFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }

}
