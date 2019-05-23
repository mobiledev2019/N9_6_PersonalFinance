package com.example.qltc.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
//import com.example.qltc.database.DatabaseHandler;
import com.example.qltc.database.DatabaseHandler;
import com.example.qltc.model.ChiTien;
import com.example.qltc.model.ConfigImage;
import com.example.qltc.model.GhiChepMauChi;
import com.example.qltc.model.GhiChepMauThu;
import com.example.qltc.model.MyTime;
import com.example.qltc.model.ThuTien;
import com.example.qltc.model.TimeUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ThuTienFragment extends Fragment {
    private TextView textView;
    private ImageView anhMuc;
    private LinearLayout chonHangMucChi;
    private ImageView imgToLeft;
    private ImageView btnTich;
    private Button btnGhi;
    private Button btnCamera;
    private Button btnChonAnh;
    private ImageView imgHinh;

    private EditText editTien;
    private EditText editDienGiai;
    private TextView txtNgay;
    private TextView txtGio;
    private EditText editChuyenDi;
    private EditText ediThuTuAi;
    private EditText editDiaDiem;
    private TextView txtChonMau;


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
    public static Bitmap bitmapImage;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thu_tien, container, false);
        anhMuc = view.findViewById(R.id.anhMuc);
        chonHangMucChi = view.findViewById(R.id.chonHangMucChi);
        textView = view.findViewById(R.id.chonmuc);
        imgToLeft = view.findViewById(R.id.toLeft);
        btnCamera = view.findViewById(R.id.btnCamera);
        btnChonAnh = view.findViewById(R.id.btnChonAnh);
        imgHinh = view.findViewById(R.id.imgHinh);

        editTien = view.findViewById(R.id.tien);
        editDienGiai = view.findViewById(R.id.dienGiai);
        txtNgay = view.findViewById(R.id.ngay);
        txtGio = view.findViewById(R.id.gio);
        editChuyenDi = view.findViewById(R.id.chuyenDi);
        ediThuTuAi = view.findViewById(R.id.thuTuAi);
        editDiaDiem = view.findViewById(R.id.diaDiemThu);
        btnGhi = view.findViewById(R.id.btnGhi);
        btnTich = view.findViewById(R.id.btnTich);
        txtChonMau = view.findViewById(R.id.txtChonMau);

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
        txtNgay.setText(dateNow);
        timeNow = myTime.getMyCurrentTime();
        txtGio.setText(timeNow);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String str = bundle.getString("key");
            textView.setText(str);
            int anh = bundle.getInt("anh");
            anhMuc.setImageResource(anh);
            String id_ghi_chep_mau = bundle.getString("id_ghi_chep_mau");
            if(id_ghi_chep_mau!=null) {
                int id = Integer.parseInt(id_ghi_chep_mau.split(" ")[0]);
                DatabaseHandler db = new DatabaseHandler(getContext());
                GhiChepMauThu ghiChepMauThu = db.getGCMThuById(id);
                System.out.println("Ghi chép mẫu đây: " + ghiChepMauThu.toString());
                editTien.setText(String.valueOf(ghiChepMauThu.getTien()));
                editDienGiai.setText(ghiChepMauThu.getDienGiai());
                editChuyenDi.setText(ghiChepMauThu.getChuyenDi());
                ediThuTuAi.setText(ghiChepMauThu.getThuTuAi());
                editDiaDiem.setText(ghiChepMauThu.getDiaDiem());
                textView.setText(ghiChepMauThu.getHangMuc());
                anhMuc.setImageResource(new ConfigImage().getImage(ghiChepMauThu.getHangMuc()));
            }else if(bundle.getString("Loai")!=null){
                String loai = bundle.getString("Loai");
                String nganHang = bundle.getString("Nganhang");
                long tien = bundle.getLong("Tien");
                anhMuc.setImageResource(new ConfigImage().getImage("NhanHangThu"));
                editTien.setText(String.valueOf(tien));
                editDienGiai.setText("Thu tiền "+nganHang);
                txtNgay.setText(dateNow);
                timeNow = myTime.getMyCurrentTime();
                txtGio.setText(timeNow);
                textView.setText("Thu từ ngân hàng");
            }
            else{
                editTien.setText(tienThu);
                editDienGiai.setText(dienGiaiThu);
                txtNgay.setText(ngayThu);
                txtGio.setText(gioThu);
                editChuyenDi.setText(chuyenDiThu);
                ediThuTuAi.setText(thuTuAi);
                editDiaDiem.setText(diaDiemThu);
                imgHinh.setImageBitmap(bitmapImage);
            }
        }

        txtChonMau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tienThu = editTien.getText().toString();
                dienGiaiThu = editDienGiai.getText().toString();
                ngayThu = txtNgay.getText().toString();
                gioThu = txtGio.getText().toString();
                chuyenDiThu = editChuyenDi.getText().toString();
                thuTuAi = ediThuTuAi.getText().toString();
                diaDiemThu = editDiaDiem.getText().toString();

                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgHinh.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                byte[] hinhAnh = byteArray.toByteArray();
                bitmapImage = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);

                GhiChepMauThuFragment thuTienFragment  = new GhiChepMauThuFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,thuTienFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        btnGhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                long tien, String hangMuc, String dienGiai, String ngay, String gio, String chuyenDi, String thuTuAi, String diaDiem
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgHinh.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                byte[] hinhAnh = byteArray.toByteArray();

                DatabaseHandler databaseHandler = new DatabaseHandler(getContext());
                ThuTien a = new ThuTien(Long.parseLong(editTien.getText().toString()), textView.getText().toString(),
                        editDienGiai.getText().toString(), ngayDB, txtGio.getText().toString()
                        , editChuyenDi.getText().toString(), ediThuTuAi.getText().toString(), editDiaDiem.getText().toString(),thangDB,namDB,hinhAnh);
                System.out.println(a.toString());
                boolean result = databaseHandler.themThuTien(a);
                if (result) {
                    Toast.makeText(getContext(), "Đã ghi", Toast.LENGTH_LONG).show();
                    ThuTienFragment thuTienFragment = new ThuTienFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, thuTienFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    ArrayList<ThuTien> lisst = databaseHandler.tatCaThu();
                    for (ThuTien e : lisst) {
                        System.out.println(e);
                    }
                    databaseHandler.close();
                } else {
                    Toast.makeText(getContext(), "SaiVL", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnTich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgHinh.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                byte[] hinhAnh = byteArray.toByteArray();

                DatabaseHandler databaseHandler = new DatabaseHandler(getContext());
                ThuTien a = new ThuTien(Long.parseLong(editTien.getText().toString()), textView.getText().toString(),
                        editDienGiai.getText().toString(), ngayDB, txtGio.getText().toString()
                        , editChuyenDi.getText().toString(), ediThuTuAi.getText().toString(), editDiaDiem.getText().toString(),thangDB,namDB,hinhAnh);
                boolean result = databaseHandler.themThuTien(a);
                if (result) {
                    Toast.makeText(getContext(), "Đã ghi", Toast.LENGTH_LONG).show();
                    ThuTienFragment thuTienFragment = new ThuTienFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, thuTienFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    ArrayList<ThuTien> lisst = databaseHandler.tatCaThu();
                    for (ThuTien e : lisst) {
                        System.out.println(e);
                    }
                    databaseHandler.close();
                } else {
                    Toast.makeText(getContext(), "SaiVL", Toast.LENGTH_LONG).show();
                }
            }
        });
        txtNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), R.style.DatePickerDialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        SimpleDateFormat MyTime = new SimpleDateFormat("dd/MM/yyyy");
                        mcurrentDate.set(year, month, dayOfMonth);
                        String[] days = new String[]{"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};
                        String day = days[mcurrentDate.get(Calendar.DAY_OF_WEEK) - 1];
                        txtNgay.setText(new TimeUtils().getDayName(day) + " - " + MyTime.format(mcurrentDate.getTime()));
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        txtGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int gio = calendar.get(Calendar.HOUR_OF_DAY);
                int phut = calendar.get(Calendar.MINUTE);
                TimePickerDialog dialog = new TimePickerDialog(getContext(), R.style.DatePickerDialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                        calendar.set(0, 0, 0, hourOfDay, minute);
                        txtGio.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, gio, phut, true);
                dialog.show();
            }
        });
        chonHangMucChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tienThu = editTien.getText().toString();
                dienGiaiThu = editDienGiai.getText().toString();
                ngayThu = txtNgay.getText().toString();
                gioThu = txtGio.getText().toString();
                chuyenDiThu = editChuyenDi.getText().toString();
                thuTuAi = ediThuTuAi.getText().toString();
                diaDiemThu = editDiaDiem.getText().toString();

                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgHinh.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                byte[] hinhAnh = byteArray.toByteArray();
                bitmapImage = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
                HangMucThuFragment hangMucThuFragment = new HangMucThuFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, hangMucThuFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }

        });
        imgToLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ChiTienFragment chiTienFragment = new ChiTienFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, chiTienFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                System.out.println("INTENTaaa"+intent);
                startActivityForResult(intent,1);
//                System.out.println("aaa");
            }
        });

        btnChonAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getContext(),ThemAnhActivity.class));
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,2);
//                System.out.println("aaa");
            }
        });
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1  && data!=null){
            Bitmap hinh =(Bitmap)data.getExtras().get("data");
//            System.out.println("bitmapnowfromfragment"+hinh);
            imgHinh.setImageBitmap(hinh);
        }
        if(requestCode == 2 && data!=null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                Bitmap hinh = BitmapFactory.decodeStream(inputStream);
//                System.out.println("bitmapnowfromfragment"+hinh);
                imgHinh.setImageBitmap(hinh);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
