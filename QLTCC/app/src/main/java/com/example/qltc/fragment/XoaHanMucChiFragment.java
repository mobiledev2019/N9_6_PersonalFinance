package com.example.qltc.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qltc.R;
import com.example.qltc.database.DatabaseHandler;
import com.example.qltc.model.HanMucChi;
import com.example.qltc.model.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class XoaHanMucChiFragment  extends Fragment {
    private LinearLayout chonHanMucChi;
    private ImageView anhMuc;
    private TextView chonMuc;
    private TextView txtNgay;
    private TextView ngayKetThuc;
    private TextView hangThang;
    private TextView txtTien;
    private TextView txtTen;
    private ImageView backToList;
    private Button btnLuu;
    private ImageView imgTich;
    private Button btnXoa;
    private LinearLayout chitiet;

    public static String hangThangSt;
    public static int anhSt;
    public static String chonMucSt;
    public static String tien;
    public static String ten;
    public static String ngayBdSt;
    public static String ngayKtSt;
    public static int id;


    private String ngay;
    private String ngayKetThuc1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { ;
        View view = inflater.inflate(R.layout.fragment_xoa_hmc, container, false);
        chonHanMucChi = view.findViewById(R.id.chonHanMucChi);
        anhMuc = view.findViewById(R.id.anhMuc);
        chonMuc = view.findViewById(R.id.chonmuc);
        txtNgay = view.findViewById(R.id.txtNgay);
        ngayKetThuc = view.findViewById(R.id.ngayKetThuc);
//        hangThang = view.findViewById(R.id.hangThang);
        txtTien = view.findViewById(R.id.chiTien);
        txtTen = view.findViewById(R.id.tenhmc);
        backToList = view.findViewById(R.id.backToList);
        btnLuu = view.findViewById(R.id.btnLuu);
        imgTich = view.findViewById(R.id.imgTich);
        btnXoa = view.findViewById(R.id.btnXoa);
        chitiet = view.findViewById(R.id.chitiet);

        hangThangSt = "Hằng tháng";

        Bundle bundle = getArguments();
        if (bundle != null){
            if(bundle.get("key")!=null && bundle.get("anh")!=null){
                chonMucSt = bundle.getString("key");
                anhSt = bundle.getInt("anh");

                chonMuc.setText(chonMucSt);
                anhMuc.setImageResource(anhSt);
//                hangThang.setText(hangThangSt);
                txtTien.setText(tien);
                txtTen.setText(ten);
                txtNgay.setText(ngayBdSt);
                ngayKetThuc.setText(ngayKtSt);
            }
            if(bundle.get("keyTime")!=null) {
                hangThangSt = bundle.getString("keyTime");

                chonMuc.setText(chonMucSt);
                anhMuc.setImageResource(anhSt);
//                hangThang.setText(hangThangSt);
                txtTien.setText(tien);
                txtTen.setText(ten);
                txtNgay.setText(ngayBdSt);
                ngayKetThuc.setText(ngayKtSt);
            }
            if(bundle.get("ngay_bd")!=null){
                ten = bundle.getString("name");
                tien = bundle.getString("tien");
                ngayBdSt = bundle.getString("ngay_bd");
                ngayKtSt = bundle.getString("ngay_kt");
                anhSt = bundle.getInt("anh");
                chonMucSt = bundle.getString("hang_muc");
                id = bundle.getInt("id");
                txtTen.setText(ten);
                tien = tien.split(" ")[0];
                txtTien.setText(tien);
                anhMuc.setImageResource(anhSt);
                txtNgay.setText(ngayBdSt);
                ngayKetThuc.setText(ngayKtSt);
                chonMuc.setText(chonMucSt);
            }
        }
        chitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChiTietHanMucChiFragment hangMucChiFragment = new ChiTietHanMucChiFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, hangMucChiFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler databaseHandler = new DatabaseHandler(getContext());
                boolean a = databaseHandler.xoaHanMucChi(id);
                if(a){
                    Toast.makeText(getContext(), "Đã xóa", Toast.LENGTH_LONG).show();
                    HanMucChiFragment hangMucChiFragment = new HanMucChiFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, hangMucChiFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }else{
                    Toast.makeText(getContext(), "Có lỗi xảy ra!", Toast.LENGTH_LONG).show();
                }
            }
        });
        chonHanMucChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tien = txtTien.getText().toString();
                ten = txtTen.getText().toString();
                ngayBdSt = txtNgay.getText().toString();
                ngayKtSt = ngayKetThuc.getText().toString();

                HangMucHanMucChiXoaFragment hangMucChiFragment = new HangMucHanMucChiXoaFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, hangMucChiFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        txtNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tien = txtTien.getText().toString();
                ten = txtTen.getText().toString();
                ngayBdSt = txtNgay.getText().toString();
                ngayKtSt = ngayKetThuc.getText().toString();
                final Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),R.style.DatePickerDialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        SimpleDateFormat MyTime = new SimpleDateFormat("dd/MM/yyyy");
                        mcurrentDate.set(year,month,dayOfMonth);
                        String[] days = new String[] { "SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY" };
                        String day = days[mcurrentDate.get(Calendar.DAY_OF_WEEK)-1];
                        txtNgay.setText(new TimeUtils().getDayName(day)+" - "+MyTime.format(mcurrentDate.getTime()));
                        ngay = MyTime.format(mcurrentDate.getTime());

                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });
        backToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HanMucChiFragment hangMucChiFragment = new HanMucChiFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, hangMucChiFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        ngayKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tien = txtTien.getText().toString();
                ten = txtTen.getText().toString();
                ngayBdSt = txtNgay.getText().toString();
                ngayKtSt = ngayKetThuc.getText().toString();
                final Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),R.style.DatePickerDialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        SimpleDateFormat MyTime = new SimpleDateFormat("dd/MM/yyyy");
                        mcurrentDate.set(year,month,dayOfMonth);
                        String[] days = new String[] { "SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY" };
                        String day = days[mcurrentDate.get(Calendar.DAY_OF_WEEK)-1];
                        ngayKetThuc.setText(new TimeUtils().getDayName(day)+" - "+MyTime.format(mcurrentDate.getTime()));
                        ngayKetThuc1 = MyTime.format(mcurrentDate.getTime());

                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });

//        hangThang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tien = txtTien.getText().toString();
//                ten = txtTen.getText().toString();
//                HanMucChiThoiGianFragment hangMucChiFragment = new HanMucChiThoiGianFragment();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragment_container, hangMucChiFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        });
        imgTich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler databaseHandler = new DatabaseHandler(getContext());
                HanMucChi hanMucChi = new HanMucChi(Long.parseLong(txtTien.getText().toString()),ten,chonMucSt,hangThangSt,txtNgay.getText().toString(),ngayKetThuc.getText().toString(),Long.parseLong(txtTien.getText().toString()),id);
                boolean insert  = databaseHandler.updateHanMucChi(hanMucChi);
                if(insert){
                    Toast.makeText(getContext(), "Đã ghi", Toast.LENGTH_LONG).show();
                    HanMucChiFragment chiTienFragment = new HanMucChiFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container,chiTienFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }else{
                    Toast.makeText(getContext(), "Sai", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler databaseHandler = new DatabaseHandler(getContext());
                HanMucChi hanMucChi = new HanMucChi(Long.parseLong(txtTien.getText().toString()),ten,chonMucSt,hangThangSt,txtNgay.getText().toString(),ngayKetThuc.getText().toString(),Long.parseLong(txtTien.getText().toString()),id);
                boolean insert  = databaseHandler.updateHanMucChi(hanMucChi);
                if(insert){
                    Toast.makeText(getContext(), "Đã ghi", Toast.LENGTH_LONG).show();
                    HanMucChiFragment chiTienFragment = new HanMucChiFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container,chiTienFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }else{
                    Toast.makeText(getContext(), "Sai", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }
}

//
