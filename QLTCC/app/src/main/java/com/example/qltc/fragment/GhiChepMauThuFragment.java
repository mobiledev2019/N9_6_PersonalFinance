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
import com.example.qltc.adapter.GhiChepMauAdapter;
import com.example.qltc.database.DatabaseHandler;
import com.example.qltc.model.ConfigImage;
import com.example.qltc.model.GhiChepMauChi;
import com.example.qltc.model.GhiChepMauEx;
import com.example.qltc.model.GhiChepMauThu;

import java.util.ArrayList;

public class GhiChepMauThuFragment extends Fragment {
    private ListView listView;
    private ArrayList<GhiChepMauEx> ghiChepMauExs;
    private GhiChepMauAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chon_mau_chi, container, false);

        listView = view.findViewById(R.id.list_item);
        getData();
        adapter = new GhiChepMauAdapter(getContext(),R.layout.fragment_ghi_chep_mau_item,ghiChepMauExs);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                ThuTienFragment chiTienFragment = new ThuTienFragment();
                bundle.putString("id_ghi_chep_mau",ghiChepMauExs.get(position).getId());

                chiTienFragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, chiTienFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
    public void getData(){
        DatabaseHandler db = new DatabaseHandler(getContext());
        ghiChepMauExs = new ArrayList<>();
        ArrayList<GhiChepMauThu> ghiChepMauThus = db.tatCaGhiChepMauThu();

        ghiChepMauThus.forEach(e->{
            String hangMuc = e.getHangMuc();
            int image =  new ConfigImage().getImage(hangMuc);
            ghiChepMauExs.add(new GhiChepMauEx(e.getId()+" thu",image,e.getHangMuc(),e.getTen(),e.getTien()));
        });

    }
}
