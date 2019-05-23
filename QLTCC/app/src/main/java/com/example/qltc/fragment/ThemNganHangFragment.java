package com.example.qltc.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.qltc.R;
import com.example.qltc.database.DatabaseHandler;
import com.example.qltc.model.NganHang;


public class ThemNganHangFragment extends Fragment {

    EditText editTenNganHang;
    Button bntThem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_them_ngan_hang,container,false);
        editTenNganHang = view.findViewById(R.id.editTenNganHang);
        bntThem = view.findViewById(R.id.btnThem);

        bntThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler db = new DatabaseHandler(getContext());
                db.themNganHang(new NganHang(editTenNganHang.getText().toString()));

                NganHangFragment nganHangFragment = new NganHangFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,nganHangFragment);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}
