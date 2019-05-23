package com.example.qltc.fragment;

import android.app.DatePickerDialog;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.qltc.R;
import com.example.qltc.adapter.LichSuAdapter;
import com.example.qltc.database.DatabaseHandler;
import com.example.qltc.model.ChiTien;
import com.example.qltc.model.ConfigImage;
import com.example.qltc.model.LichSu;
import com.example.qltc.model.MyTime;
import com.example.qltc.model.ThuTien;
import com.example.qltc.model.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;

public class LichSuFragment extends Fragment {
    public ListView listView;
    public ArrayList<LichSu> lichSus;
    public LichSuAdapter adapter;
    private TextView tongThu;
    private TextView tongChi;
    private TextView picker;
    private ImageView toLeft;
    private ImageView toRight;


    private long tongChiLong = 0;
    private long tongThuLong = 0;


    public static String namDB;
    public static String ngayDB;
    public static String thangDB;
    public static  String time;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_lich_su, container, false);
        listView = view.findViewById(R.id.listItem);
        tongThu = view.findViewById(R.id.tongThu);
        tongChi = view.findViewById(R.id.tongChi);
        picker = view.findViewById(R.id.picker);
        toLeft = view.findViewById(R.id.toLeft);
        toRight = view.findViewById(R.id.toRight);

        getData(picker.getText().toString());
        adapter = new LichSuAdapter(getContext(), R.layout.fragment_lich_su_item_detail, lichSus);
        listView.setAdapter(adapter);


        tongChi.setText(String.valueOf(tongChiLong));
        tongThu.setText(String.valueOf(tongThuLong));

        MyTime myTime = new TimeUtils().getTime();
        String thang = myTime.getMyTime();

        picker.setText(thang.split("/")[1] + "/" + thang.split("/")[2]);

        getData(picker.getText().toString());
        adapter = new LichSuAdapter(getContext(), R.layout.fragment_lich_su_item_detail, lichSus);
        listView.setAdapter(adapter);
        tongChi.setText(String.valueOf(tongChiLong));
        tongThu.setText(String.valueOf(tongThuLong));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (lichSus.get(position).getId().split(" ")[1].compareTo("chi") == 0) {
                    CapNhatChiTienFragment xoaHanMucChiFragment = new CapNhatChiTienFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", Integer.parseInt(lichSus.get(position).getId().split(" ")[0]));
                    xoaHanMucChiFragment.setArguments(bundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, xoaHanMucChiFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }else{
                    CapNhatThuTienFragment xoaHanMucChiFragment = new CapNhatThuTienFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", Integer.parseInt(lichSus.get(position).getId().split(" ")[0]));
                    xoaHanMucChiFragment.setArguments(bundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, xoaHanMucChiFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });
        toLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(picker.getText().toString().length()==7){
                    MyTime myTime = new TimeUtils().getTime();
                    String ngay = myTime.getMyTime();
                    picker.setText(ngay);
                    getData(ngay);
                    adapter = new LichSuAdapter(getContext(), R.layout.fragment_lich_su_item_detail, lichSus);
                    listView.setAdapter(adapter);

                    tongChi.setText(String.valueOf(tongChiLong));
                    tongThu.setText(String.valueOf(tongThuLong));

                }
                else if(picker.getText().toString().length()==4){
                    MyTime myTime = new TimeUtils().getTime();
                    String thang = myTime.getMyTime().split("/")[1]+"/"+myTime.getMyTime().split("/")[2];
                    picker.setText(thang);
                    getData(thang);
                    adapter = new LichSuAdapter(getContext(), R.layout.fragment_lich_su_item_detail, lichSus);
                    listView.setAdapter(adapter);
                    tongChi.setText(String.valueOf(tongChiLong));
                    tongThu.setText(String.valueOf(tongThuLong));

                }else if(picker.getText().toString().length()==10){
                    MyTime myTime = new TimeUtils().getTime();
                    String nam = myTime.getMyTime().split("/")[2];
                    picker.setText(nam);
                    getData(nam);
                    adapter = new LichSuAdapter(getContext(), R.layout.fragment_lich_su_item_detail, lichSus);
                    listView.setAdapter(adapter);
                    tongChi.setText(String.valueOf(tongChiLong));
                    tongThu.setText(String.valueOf(tongThuLong));
                }
            }
        });
        toRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(picker.getText().toString().length()==7){
                    MyTime myTime = new TimeUtils().getTime();
                    String nam = myTime.getMyTime().split("/")[2];
                    picker.setText(nam);
                    getData(nam);
                    adapter = new LichSuAdapter(getContext(), R.layout.fragment_lich_su_item_detail, lichSus);
                    listView.setAdapter(adapter);
                    tongChi.setText(String.valueOf(tongChiLong));
                    tongThu.setText(String.valueOf(tongThuLong));

                }
                else if(picker.getText().toString().length()==10){
                    MyTime myTime = new TimeUtils().getTime();
                    String thang = myTime.getMyTime().split("/")[1]+"/"+myTime.getMyTime().split("/")[2];
                    picker.setText(thang);
                    getData(thang);
                    adapter = new LichSuAdapter(getContext(), R.layout.fragment_lich_su_item_detail, lichSus);
                    listView.setAdapter(adapter);
                    tongChi.setText(String.valueOf(tongChiLong));
                    tongThu.setText(String.valueOf(tongThuLong));


                }else if(picker.getText().toString().length() ==4){
                    MyTime myTime = new TimeUtils().getTime();
                    String ngay = myTime.getMyTime();
                    picker.setText(ngay);
                    getData(ngay);
                    adapter = new LichSuAdapter(getContext(), R.layout.fragment_lich_su_item_detail, lichSus);
                    listView.setAdapter(adapter);
                    tongChi.setText(String.valueOf(tongChiLong));
                    tongThu.setText(String.valueOf(tongThuLong));
                }
            }
        });
        picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar mcurrentDate = Calendar.getInstance();

                int mYear = mcurrentDate.get(Calendar.YEAR);
                namDB = String.valueOf(mYear);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                thangDB = String.valueOf(mMonth);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                ngayDB = String.valueOf(mDay);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),R.style.DatePickerDialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        SimpleDateFormat MyTime = new SimpleDateFormat("dd/MM/yyyy");
                        mcurrentDate.set(year,month,dayOfMonth);
                        if(picker.getText().toString().length() == 7 ) {
                            time = MyTime.format(mcurrentDate.getTime()).split("/")[1] +
                                    "/" + MyTime.format(mcurrentDate.getTime()).split("/")[2];

                        }else if(picker.getText().toString().length() == 4){
                            System.out.println();
                            time = MyTime.format(mcurrentDate.getTime()).split("/")[2];
                            System.out.println( time);
                        }else {
                            time = MyTime.format(mcurrentDate.getTime());
                        }
                        picker.setText(time);
                        getData(time);
                        adapter = new LichSuAdapter(getContext(), R.layout.fragment_lich_su_item_detail, lichSus);
                        listView.setAdapter(adapter);
                        tongChi.setText(String.valueOf(tongChiLong));
                        tongThu.setText(String.valueOf(tongThuLong));
                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        }
        );


        return view;
    }

    public void getData(String time) {
        lichSus = new ArrayList<>();
        if (picker.getText().toString().length() == 7) {
            tongThuLong =0;
            tongChiLong =0;
            DatabaseHandler db = new DatabaseHandler(getContext());
            ArrayList<ChiTien> chiTiens = db.chiTienTheoThang(time.split("/")[0], time.split("/")[1]);
            ArrayList<ThuTien> thuTiens = db.thuTienTheoThang(time.split("/")[0], time.split("/")[1]);
            chiTiens.forEach(e -> {
                tongChiLong += e.getTien();
//                public LichSu(int image, String hangMuc, String ngay, String thang, String nam, Long tien, String id) {
                int anh = new ConfigImage().getImage(e.getHangMuc());
                lichSus.add(new LichSu(anh, e.getHangMuc(), e.getNgay(), e.getThang(), e.getNam(), e.getTien(), e.getId() + " chi"));
            });

            thuTiens.forEach(e -> {
                tongThuLong += e.getTien();
                int anh = new ConfigImage().getImage(e.getHangMuc());
                lichSus.add(new LichSu(anh, e.getHangMuc(), e.getNgay(), e.getThang(), e.getNam(), e.getTien(), e.getId() + " thu"));
            });
        }
        else if (picker.getText().toString().length() == 4) {
            tongThuLong =0;
            tongChiLong =0;
            DatabaseHandler db = new DatabaseHandler(getContext());
            ArrayList<ChiTien> chiTiens = db.chiTienTheoNam(time);
            ArrayList<ThuTien> thuTiens = db.thuTienTheoNam(time);
            chiTiens.forEach(e -> {
                tongChiLong += e.getTien();
//                public LichSu(int image, String hangMuc, String ngay, String thang, String nam, Long tien, String id) {
                int anh = new ConfigImage().getImage(e.getHangMuc());
                lichSus.add(new LichSu(anh, e.getHangMuc(), e.getNgay(), e.getThang(), e.getNam(), e.getTien(), e.getId() + " chi"));
            });

            thuTiens.forEach(e -> {
                tongThuLong += e.getTien();
                int anh = new ConfigImage().getImage(e.getHangMuc());
                lichSus.add(new LichSu(anh, e.getHangMuc(), e.getNgay(), e.getThang(), e.getNam(), e.getTien(), e.getId() + " thu"));
            });
        }else if(picker.getText().toString().length() == 10){
            tongThuLong =0;
            tongChiLong =0;
            DatabaseHandler db = new DatabaseHandler(getContext());
            ArrayList<ChiTien> chiTiens = db.chiTienTheoNgay(time.split("/")[0],time.split("/")[1],time.split("/")[2]);
            ArrayList<ThuTien> thuTiens = db.thuTienTheoNgay(time.split("/")[0],time.split("/")[1],time.split("/")[2]);
            chiTiens.forEach(e -> {
                tongChiLong += e.getTien();
//                public LichSu(int image, String hangMuc, String ngay, String thang, String nam, Long tien, String id) {
                int anh = new ConfigImage().getImage(e.getHangMuc());
                lichSus.add(new LichSu(anh, e.getHangMuc(), e.getNgay(), e.getThang(), e.getNam(), e.getTien(), e.getId() + " chi"));
            });

            thuTiens.forEach(e -> {
                tongThuLong += e.getTien();
                int anh = new ConfigImage().getImage(e.getHangMuc());
                lichSus.add(new LichSu(anh, e.getHangMuc(), e.getNgay(), e.getThang(), e.getNam(), e.getTien(), e.getId() + " thu"));
            });
        }
    }
}
