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
import android.widget.ImageView;
import android.widget.ListView;

import com.example.qltc.R;
import com.example.qltc.adapter.HanMucChiAdapter;
import com.example.qltc.database.DatabaseHandler;
import com.example.qltc.model.ConfigImage;
import com.example.qltc.model.HanMucChi;
import com.example.qltc.model.HanMucChiAD;
import com.example.qltc.model.MyTime;
import com.example.qltc.model.TimeUtils;

import java.util.ArrayList;

public class HanMucChiFragment extends Fragment {
    private ImageView addHMC;
    private ListView listView;
    private ArrayList<HanMucChiAD> hanMucChiArrayList;
    private HanMucChiAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_item_list_hmc, container, false);
        addHMC = view.findViewById(R.id.addHMC);
        listView = view.findViewById(R.id.list_item);
        getData();
        adapter = new HanMucChiAdapter(getContext(), R.layout.fragment_item_hmc, hanMucChiArrayList);
        listView.setAdapter(adapter);
        addHMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemHanMucChiFragment themHanMucChiFragment = new ThemHanMucChiFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, themHanMucChiFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                XoaHanMucChiFragment xoaHanMucChiFragment = new XoaHanMucChiFragment();
                Bundle bundle = new Bundle();
                bundle.putString("name", hanMucChiArrayList.get(position).getName());
                bundle.putString("tien", hanMucChiArrayList.get(position).getTien());
                bundle.putString("ngay_bd", hanMucChiArrayList.get(position).getNgayBD());
                bundle.putInt("anh", hanMucChiArrayList.get(position).getHinh());
                bundle.putString("ngay_kt", hanMucChiArrayList.get(position).getNgayKT());
                bundle.putString("hang_muc",hanMucChiArrayList.get(position).getHangMuc());
                bundle.putInt("id",hanMucChiArrayList.get(position).getId());
                xoaHanMucChiFragment.setArguments(bundle);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, xoaHanMucChiFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }

    public void getData() {
        DatabaseHandler databaseHandler = new DatabaseHandler(getContext());
        ArrayList<HanMucChi> listHMC = databaseHandler.tatCaHanMucChi();
        hanMucChiArrayList = new ArrayList<>();
        if (listHMC.size() > 0) {
            listHMC.forEach(e -> {
                long soTien = e.getSoTien();
                String hangMuc = e.getHangMuc();
                ConfigImage configImage = new ConfigImage();
                int image = configImage.getImage(hangMuc);
                String ngayBatDau = e.getNgayBatDau();
                String ngay;
                TimeUtils timeUtils = new TimeUtils();
                MyTime myTime = timeUtils.getTime();
                String now = myTime.getDdMM();

                System.out.println("Thoi gian now:" + now);
                String arrBD[] = ngayBatDau.split("-");
                String arrBD1[] = arrBD[1].split("/");

                String ngayKetThuc = e.getNgayKetThuc();
                String arrKT[] = ngayKetThuc.split("-");
                String arrKT1[] = arrKT[1].split("/");
                String nowArr[] = now.split("/");
                ngay = arrBD1[0] + "/" + arrBD1[1] + " - " + arrKT1[0] + "/" + arrKT1[1];


                int conLai = Integer.parseInt(arrKT1[0].trim()) - Integer.parseInt(nowArr[0]);
                String ngayConLai;
                if (conLai >= 0) {
                    ngayConLai = "Còn lại " + conLai + " ngày";
                } else {
                    ngayConLai = "Hết hạn";
                }
                String ten = e.getTen();
                System.out.println("Han muc chi: " + e);
                HanMucChiAD ad = new HanMucChiAD(image, ten, ngay, String.valueOf(soTien), ngayConLai, e.getBoiChi(), e.getNgayBatDau(), e.getNgayKetThuc(),e.getHangMuc(),e.getId());
                hanMucChiArrayList.add(ad);
            });
        }
    }
}
