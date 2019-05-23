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
import com.example.qltc.model.GhiChepMauThu;
import com.example.qltc.model.MyTime;
import com.example.qltc.model.ThuTien;
import com.example.qltc.model.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ThemGhiChepMauThuTienFragment extends Fragment {
    private TextView textView;
    private ImageView anhMuc;
    private LinearLayout chonHangMucChi;

    private Button btnGhi;


    private EditText editTien;
    private EditText editDienGiai;
//    private TextView txtNgay;
//    private TextView txtGio;
    private EditText editChuyenDi;
    private EditText ediThuTuAi;
    private EditText editDiaDiem;
    private EditText editTen;

    public static String tienThu;
    public static String dienGiaiThu;
    public static String ngayThu;
    public static String gioThu;
    public static String chuyenDiThu;
    public static String thuTuAi;
    public static String diaDiemThu;
    public static String thangDB;
    public static String namDB;
    public static int anh;
    public static String ngayDB;
    public static String ten;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_them_gcm_tab_thu_tien, container, false);
        anhMuc = view.findViewById(R.id.anhMuc);
        chonHangMucChi = view.findViewById(R.id.chonHangMucChi);
        textView = view.findViewById(R.id.chonmuc);


        editTien = view.findViewById(R.id.tien);
        editDienGiai = view.findViewById(R.id.dienGiai);
//        txtNgay = view.findViewById(R.id.ngay);
//        txtGio = view.findViewById(R.id.gio);
        editChuyenDi = view.findViewById(R.id.chuyenDi);
        ediThuTuAi = view.findViewById(R.id.thuTuAi);
        editDiaDiem = view.findViewById(R.id.diaDiemThu);
        btnGhi = view.findViewById(R.id.btnGhi);
        editTen = view.findViewById(R.id.ten);


        //ngày giờ ban dầu
        String dateNow;
        String timeNow;
        final MyTime myTime = new TimeUtils().getTime();
        dateNow = myTime.getMyTime();
        String arr[] = dateNow.split("/");
        System.out.println("date now"+ dateNow);
        ngayDB = arr[0];
        System.out.println("ngay db" + ngayDB);
        thangDB = arr[1];
        System.out.println("thang db"+ thangDB);
        namDB = arr[2];
        System.out.println("nam db"+namDB);
        dateNow = "Hôm nay - " + dateNow;
//        txtNgay.setText(dateNow);
        timeNow = myTime.getMyCurrentTime();
//        txtGio.setText(timeNow);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String str = bundle.getString("key");
            textView.setText(str);
            int anh = bundle.getInt("anh");
            anhMuc.setImageResource(anh);

            editTien.setText(tienThu);
            editDienGiai.setText(dienGiaiThu);
//            txtNgay.setText(ngayThu);
//            txtGio.setText(gioThu);
            editChuyenDi.setText(chuyenDiThu);
            ediThuTuAi.setText(thuTuAi);
            editDiaDiem.setText(diaDiemThu);
            editTen.setText(ten);
        }

        btnGhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                long tien, String hangMuc, String dienGiai, String ngay, String gio, String chuyenDi, String thuTuAi, String diaDiem
                DatabaseHandler databaseHandler = new DatabaseHandler(getContext());
                GhiChepMauThu a = new GhiChepMauThu(editTen.getText().toString(),Long.parseLong(editTien.getText().toString()), textView.getText().toString(),
                        editDienGiai.getText().toString(), ngayDB, editChuyenDi.getText().toString(), ediThuTuAi.getText().toString(), editDiaDiem.getText().toString(), "",thangDB,namDB,anhMuc.getImageAlpha());
                boolean result = databaseHandler.themGCMThu(a);
                if (result) {
                    Toast.makeText(getContext(), "Đã ghi", Toast.LENGTH_LONG).show();
                    ListGhiChepMauFragment thuTienFragment = new ListGhiChepMauFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, thuTienFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    databaseHandler.close();
                } else {
                    Toast.makeText(getContext(), "SaiVL", Toast.LENGTH_LONG).show();
                }
            }
        });

//        txtNgay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Calendar mcurrentDate = Calendar.getInstance();
//                int mYear = mcurrentDate.get(Calendar.YEAR);
//                int mMonth = mcurrentDate.get(Calendar.MONTH);
//                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
//                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), R.style.DatePickerDialog, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        SimpleDateFormat MyTime = new SimpleDateFormat("dd/MM/yyyy");
//                        mcurrentDate.set(year, month, dayOfMonth);
//                        String[] days = new String[]{"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};
//                        String day = days[mcurrentDate.get(Calendar.DAY_OF_WEEK) - 1];
//                        txtNgay.setText(new TimeUtils().getDayName(day) + " - " + MyTime.format(mcurrentDate.getTime()));
//                    }
//                }, mYear, mMonth, mDay);
//                datePickerDialog.show();
//            }
//        });
//        txtGio.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Calendar calendar = Calendar.getInstance();
//                int gio = calendar.get(Calendar.HOUR_OF_DAY);
//                int phut = calendar.get(Calendar.MINUTE);
//                TimePickerDialog dialog = new TimePickerDialog(getContext(), R.style.DatePickerDialog, new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
//                        calendar.set(0, 0, 0, hourOfDay, minute);
//                        txtGio.setText(simpleDateFormat.format(calendar.getTime()));
//                    }
//                }, gio, phut, true);
//                dialog.show();
//            }
//        });
        chonHangMucChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tienThu = editTien.getText().toString();
                dienGiaiThu = editDienGiai.getText().toString();
//                ngayThu = txtNgay.getText().toString();
//                gioThu = txtGio.getText().toString();
                chuyenDiThu = editChuyenDi.getText().toString();
                thuTuAi = ediThuTuAi.getText().toString();
                diaDiemThu = editDiaDiem.getText().toString();
                ten = editTen.getText().toString();

                HangMucGhiChepMauThuFragment hangMucThuFragment = new HangMucGhiChepMauThuFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, hangMucThuFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }

        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
