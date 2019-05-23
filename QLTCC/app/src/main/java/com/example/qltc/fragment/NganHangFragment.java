package com.example.qltc.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.qltc.R;
import com.example.qltc.database.DatabaseHandler;
import com.example.qltc.model.NganHang;

import java.util.ArrayList;
import java.util.Map;

public class NganHangFragment extends Fragment {
    ListView listView;
    ImageView themNganHang;

//    public Map<String,String> messageNganHang;


    public ArrayList<String> tenNganHang;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ngan_hang, container, false);
        listView = view.findViewById(R.id.lvChiTieuA);
        themNganHang = view.findViewById(R.id.themNganHang);

        getData();

        final ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, tenNganHang);
        listView.setAdapter(adapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Bundle bundle = new Bundle();
//                TongQuanFragment chiTienFragment = new TongQuanFragment();
//                bundle.putString("keyTime",thoiGian.get(position));
//                chiTienFragment.setArguments(bundle);
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragment_container, chiTienFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//
//            }
//        });
        themNganHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemNganHangFragment themNganHangFragment = new ThemNganHangFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, themNganHangFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });



        return view;
    }


    public void getData(){
        ArrayList<NganHang> nganHangList = new ArrayList<>();
        tenNganHang = new ArrayList<>();
        DatabaseHandler db = new DatabaseHandler(getContext());
        nganHangList = db.tatCaNganHang();
        nganHangList.forEach(e->{
            tenNganHang.add(e.getTen());
        });
    }
//    public void getMessage(){
//        messageNganHang = new HashMap<>();
//        ContentResolver cResolver = getContext().getContentResolver();
//        Cursor smsInboxCursor =cResolver.query(Uri.parse("content://sms/inbox"),
//                null,null,null,null);
//        int indexBody = smsInboxCursor.getColumnIndex("body");
//        int indexAddress = smsInboxCursor.getColumnIndex("address");
//        if(indexBody < 0 ||!smsInboxCursor.moveToFirst()) return;
//        do {
//            messageNganHang.put(smsInboxCursor.getString(indexAddress),smsInboxCursor.getString(indexBody));
//        }while(smsInboxCursor.moveToNext());
//    }
}
