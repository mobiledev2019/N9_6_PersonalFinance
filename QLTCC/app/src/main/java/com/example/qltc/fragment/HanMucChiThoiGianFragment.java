package com.example.qltc.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.qltc.R;

import java.util.ArrayList;

public class HanMucChiThoiGianFragment extends Fragment {
    ListView listView;
    ArrayList<String> thoiGian;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thoi_gian_hmc, container, false);
        listView = view.findViewById(R.id.lvChiTieuA);

        thoiGian = new ArrayList<>();
        thoiGian.add("Hôm nay");
//        thoiGian.add("Tuần này");
        thoiGian.add("Tháng này");
        thoiGian.add("Năm nay");


        final ArrayAdapter adapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,thoiGian);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                TongQuanFragment chiTienFragment = new TongQuanFragment();
                bundle.putString("keyTime",thoiGian.get(position));
                chiTienFragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, chiTienFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        return view;
    }
}
