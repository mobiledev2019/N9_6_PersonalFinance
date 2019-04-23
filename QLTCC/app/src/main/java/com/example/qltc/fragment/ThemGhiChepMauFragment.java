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
import com.example.qltc.adapter.SessionPageAdapter;

public class ThemGhiChepMauFragment extends Fragment {
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private ImageView backToList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_them_gcm, container,false);
        tabLayout = view.findViewById(R.id.tablayout_id);
        mViewPager = view.findViewById(R.id.viewpager_id);
        backToList = view.findViewById(R.id.backToList);

        SessionPageAdapter adapter =null;
        if(adapter==null) {
            adapter = new SessionPageAdapter(getFragmentManager());
            adapter.addFragment(new ThemGhiChepMauChiTienFragment(), "CHI TIỀN");
            adapter.addFragment(new ThemGhiChepMauThuTienFragment(), "THU TIỀN");
        }

        mViewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(mViewPager);

        backToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GhiChepMauFragment thuTienFragment  = new GhiChepMauFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,thuTienFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_them_gcm);
//        tabLayout = view.findViewById(R.id.tablayout_id);
//        mViewPager = view.findViewById(R.id.viewpager_id);
//
//        SessionPageAdapter adapter = new SessionPageAdapter(getSupportFragmentManager());
//        adapter.addFragment(new ThemGhiChepMauChiTienFragment(),"CHI TIỀN");
//        adapter.addFragment(new ThemGhiChepMauThuTienFragment(),"THU TIỀN");
//
//        mViewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(mViewPager);
//    }
}
