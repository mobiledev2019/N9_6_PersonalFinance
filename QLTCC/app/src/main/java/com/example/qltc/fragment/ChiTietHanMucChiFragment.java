package com.example.qltc.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.qltc.R;
import com.example.qltc.adapter.ChiTietHMCAdapter;
import com.example.qltc.database.DatabaseHandler;
import com.example.qltc.model.ChiTien;

import java.util.ArrayList;

public class ChiTietHanMucChiFragment extends Fragment {
    ListView listChiTiet;
    ArrayList<ChiTien> chiTienArrayList;
    ChiTietHMCAdapter adapterp;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_chi_tiet_khoan_chi, container, false);
        listChiTiet = view.findViewById(R.id.listChiTiet);

        return view;
    }
    public void getData(){
        DatabaseHandler databaseHandler = new DatabaseHandler(getContext());
        chiTienArrayList = databaseHandler.tatCaChi();

    }
}
