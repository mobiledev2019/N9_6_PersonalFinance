package com.example.qltc.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.qltc.R;
import com.example.qltc.database.DatabaseHandler;
import com.example.qltc.model.ChiTien;
import com.example.qltc.model.MyTime;
import com.example.qltc.model.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ThemGhiChepMauChiTienFragment extends Fragment {

    private TextView textView;
    private ImageView anhMuc;
    private LinearLayout chonHangMucChi;
    private ImageView imgToRight;
    private Button btnGhi;
    private ImageView imgTich;

    private EditText editTien;
    private EditText editDienGiai;
    private TextView txtNgay;
    private TextView txtGio;
    private EditText editChuyenDi;
    private EditText editChiChoAi;
    private EditText editDiaDiem;


    public static String tien;
    public static String dienGiai;
    public static String ngay;
    public static String gio;
    public static String chuyenDi;
    public static String chiChoAi;
    public static String diaDiem;


    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_them_gcm_tab_chi_tien, container, false);
        anhMuc = view.findViewById(R.id.anhMuc);
        chonHangMucChi = view.findViewById(R.id.chonHangMucChi);
        textView = view.findViewById(R.id.chonmuc);
        imgToRight = view.findViewById(R.id.toRight);
        btnGhi = view.findViewById(R.id.btnGhi);
        imgTich = view.findViewById(R.id.btnTich);

        editTien =view.findViewById(R.id.chiTien);
        editDienGiai = view.findViewById(R.id.dienGiai);
        txtNgay = view.findViewById(R.id.ngay);
        txtGio = view.findViewById(R.id.gio);
        editChuyenDi = view.findViewById(R.id.chuyenDi);
        editChiChoAi = view.findViewById(R.id.chiChoAi);
        editDiaDiem = view.findViewById(R.id.diaDiem);



        //ngày giờ ban dầu
        String dateNow ;
        String timeNow;
        final MyTime myTime = new TimeUtils().getTime();
        dateNow = myTime.getMyTime();
        dateNow = "Hôm nay - "+dateNow;
        txtNgay.setText(dateNow);
        timeNow = myTime.getMyCurrentTime();
        txtGio.setText(timeNow);

//        DatabaseHandler databaseHandler = new DatabaseHandler(getContext());
//        boolean re = databaseHandler.themUser();
//        User user = databaseHandler.getUser();
//        System.out.printf(String.valueOf("Them user "+ re));
//        System.out.println("TThem " + user);

        Bundle bundle = getArguments();
        if (bundle != null){
            String str = bundle.getString("key");
            textView.setText(str);
            int anh = bundle.getInt("anh");
            anhMuc.setImageResource(anh);

            editTien.setText(tien);
            editDienGiai.setText(dienGiai);
            txtNgay.setText(ngay);
            txtGio.setText(gio);
            editChuyenDi.setText(chuyenDi);
            editChiChoAi.setText(chiChoAi);
            editDiaDiem.setText(diaDiem);
        }
        txtNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        txtGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                final int gio1= calendar.get(Calendar.HOUR_OF_DAY);
                int phut = calendar.get(Calendar.MINUTE);
                TimePickerDialog dialog = new TimePickerDialog(getContext(),R.style.DatePickerDialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                        calendar.set(0,0,0,hourOfDay,minute);
                        gio =String.valueOf(simpleDateFormat.format(calendar.getTime()));
                        txtGio.setText(simpleDateFormat.format(calendar.getTime())

                        );

                    }
                },gio1,phut,true);
                dialog.show();
            }
        });
        chonHangMucChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tien = editTien.getText().toString();
                dienGiai = editDienGiai.getText().toString();
                ngay = txtNgay.getText().toString();
                gio = txtGio.getText().toString();
                chuyenDi = editChuyenDi.getText().toString();
                chiChoAi = editChiChoAi.getText().toString();
                diaDiem = editDiaDiem.getText().toString();

                HangMucGhiChepMauChiFragment hangMucChiFragment = new HangMucGhiChepMauChiFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, hangMucChiFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }

        });

//        imgTich.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatabaseHandler databaseHandler = new DatabaseHandler(getContext());
//                ChiTien a  = new ChiTien (Long.parseLong(tien),textView.getText().toString(),
//                        editDienGiai.getText().toString(),txtNgay.getText().toString(),txtGio.getText().toString()
//                        ,editChuyenDi.getText().toString(),editChiChoAi.getText().toString(),editDiaDiem.getText().toString());
//                boolean result = databaseHandler.themChiTien(a);
//                if(result) {
//                    Toast.makeText(getContext(), "Đã ghi", Toast.LENGTH_LONG).show();
//                    ChiTienFragment chiTienFragment = new ChiTienFragment();
//                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                    transaction.replace(R.id.fragment_container,chiTienFragment);
//                    transaction.addToBackStack(null);
//                    transaction.commit();
//                }else{
//                    Toast.makeText(getContext(), "SaiVL", Toast.LENGTH_LONG).show();
//                }
////                ArrayList<ChiTien> lisst = databaseHandler.tatCaChi();
////                for(ChiTien e: lisst){
////                    System.out.println(e);
////                }
//                databaseHandler.close();
//            }
//        });

//        btnGhi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatabaseHandler databaseHandler = new DatabaseHandler(getContext());
//                ChiTien a  = new ChiTien (Long.parseLong(tien),textView.getText().toString(),
//                        editDienGiai.getText().toString(),txtNgay.getText().toString(),txtGio.getText().toString()
//                        ,editChuyenDi.getText().toString(),editChiChoAi.getText().toString(),editDiaDiem.getText().toString());
//                boolean result = databaseHandler.themChiTien(a);
//                if(result) {
//                    Toast.makeText(getContext(), "Đã ghi", Toast.LENGTH_LONG).show();
//                    ChiTienFragment chiTienFragment = new ChiTienFragment();
//                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                    transaction.replace(R.id.fragment_container,chiTienFragment);
//                    transaction.addToBackStack(null);
//                    transaction.commit();
//                }else{
//                    Toast.makeText(getContext(), "SaiVL", Toast.LENGTH_LONG).show();
//                }
////                ArrayList<ChiTien> lisst = databaseHandler.tatCaChi();
////                for(ChiTien e: lisst){
////                    System.out.println(e);
////                }
//                databaseHandler.close();
//            }
//        });
//        imgToRight.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ThuTienFragment thuTienFragment  = new ThuTienFragment();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragment_container,thuTienFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        });
        return view;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
