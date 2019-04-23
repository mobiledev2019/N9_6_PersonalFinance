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

public class HangMucChiFragment extends Fragment {

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
               ChiTienFragment chiTienFragment = new ChiTienFragment();
               bundle.putString("key",chiTienArrayList.get(position).getTen().toString());
               bundle.putInt("anh",chiTienArrayList.get(position).getHinh());
               chiTienFragment.setArguments(bundle);


                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, chiTienFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        return view;
    }

    public void getData() {
        chiTienArrayList = new ArrayList<>();
        chiTienArrayList.add(new HangMucChiTien(R.drawable.an_uong,"Ăn uống"));
        chiTienArrayList.add(new HangMucChiTien(R.drawable.dich_vu_sinh_hoat,"Dịch vụ sinh hoạt"));
        chiTienArrayList.add(new HangMucChiTien(R.drawable.di_lai,"Đi lại"));
        chiTienArrayList.add(new HangMucChiTien(R.drawable.trang_phuc,"Trang phục"));
        chiTienArrayList.add(new HangMucChiTien(R.drawable.con_cai,"Con cái"));
        chiTienArrayList.add(new HangMucChiTien(R.drawable.huong_thu,"Hưởng thụ"));
        chiTienArrayList.add(new HangMucChiTien(R.drawable.hieu_hi,"Hiếu hỉ"));
        chiTienArrayList.add(new HangMucChiTien(R.drawable.nha_cua,"Nhà cửa"));
        chiTienArrayList.add(new HangMucChiTien(R.drawable.phat_trien_ban_than,"Phát triển bản thân"));
        chiTienArrayList.add(new HangMucChiTien(R.drawable.suc_khoe,"Sức khỏe"));
    }


}
