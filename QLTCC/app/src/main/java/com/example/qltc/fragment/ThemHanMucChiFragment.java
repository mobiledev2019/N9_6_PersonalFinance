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

public class ThemHanMucChiFragment extends Fragment {
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


    public static String hangThangSt;
    public static int anhSt;
    public static String chonMucSt;
    public static String tien;
    public static String ten;

    private String ngay;
    private String ngayKetThuc1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { ;
        View view = inflater.inflate(R.layout.fragment_them_hcm, container, false);
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
            }

            if(bundle.get("keyTime")!=null) {
                hangThangSt = bundle.getString("keyTime");

                chonMuc.setText(chonMucSt);
                anhMuc.setImageResource(anhSt);
                hangThang.setText(hangThangSt);
                txtTien.setText(tien);
                txtTen.setText(ten);
            }
        }
        chonHanMucChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tien = txtTien.getText().toString();
                ten = txtTen.getText().toString();

                HangMucHanMucChiFragment hangMucChiFragment = new HangMucHanMucChiFragment();
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
                HanMucChi hanMucChi = new HanMucChi(Long.parseLong(tien),ten,chonMucSt,hangThangSt,txtNgay.getText().toString(),ngayKetThuc.getText().toString(),Long.parseLong(tien));
                boolean insert  = databaseHandler.themHanMucChi(hanMucChi);
                if(insert){
                    Toast.makeText(getContext(), "Đã ghi", Toast.LENGTH_LONG).show();
                    ThemHanMucChiFragment chiTienFragment = new ThemHanMucChiFragment();
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
                HanMucChi hanMucChi = new HanMucChi(Long.parseLong(tien),ten,chonMucSt,hangThangSt,txtNgay.getText().toString(),ngayKetThuc.getText().toString(),Long.parseLong(tien));
                boolean insert  = databaseHandler.themHanMucChi(hanMucChi);
                if(insert){
                    Toast.makeText(getContext(), "Đã ghi", Toast.LENGTH_LONG).show();
                    ThemHanMucChiFragment chiTienFragment = new ThemHanMucChiFragment();
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
