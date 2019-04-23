package com.example.qltc.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.qltc.R;
import com.example.qltc.adapter.HangMucChiTienAdapter;
import com.example.qltc.model.HangMucChiTien;

import java.util.ArrayList;

public class HangMucThuFragment extends Fragment {

    private ListView listView;
    private ArrayList<HangMucChiTien> chiTienArrayList;
    private HangMucChiTienAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { ;
        View view = inflater.inflate(R.layout.fragment_item_chi, container, false);
        getData();
        listView = view.findViewById(R.id.lvChiTieu);
        adapter = new HangMucChiTienAdapter(getContext(), R.layout.fragment_hang_muc_thu_chi, chiTienArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                ThuTienFragment thuTienFragment = new ThuTienFragment();
                bundle.putString("key",chiTienArrayList.get(position).getTen().toString());
                bundle.putInt("anh",chiTienArrayList.get(position).getHinh());
                thuTienFragment.setArguments(bundle);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, thuTienFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        return view;
    }

    public void getData() {
        chiTienArrayList = new ArrayList<>();
        chiTienArrayList.add(new HangMucChiTien(R.drawable.luong,"Lương"));
        chiTienArrayList.add(new HangMucChiTien(R.drawable.thuong,"Thưởng"));
        chiTienArrayList.add(new HangMucChiTien(R.drawable.cho_tang,"Được cho/tặng"));
        chiTienArrayList.add(new HangMucChiTien(R.drawable.tiet_kiem,"Lãi tiết kiệm"));
        chiTienArrayList.add(new HangMucChiTien(R.drawable.lai,"Tiền lãi"));
        chiTienArrayList.add(new HangMucChiTien(R.drawable.khac,"Khác"));
    }

}
