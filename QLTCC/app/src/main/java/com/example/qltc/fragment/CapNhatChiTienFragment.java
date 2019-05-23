package com.example.qltc.fragment;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
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
import com.example.qltc.model.ConfigImage;
import com.example.qltc.model.GhiChepMauChi;
import com.example.qltc.model.HanMucChi;
import com.example.qltc.model.MyTime;
import com.example.qltc.model.TimeUtils;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CapNhatChiTienFragment extends Fragment {
    private TextView textView;
    private ImageView anhMuc;
    private LinearLayout chonHangMucChi;
    private ImageView imgToRight;
    private Button btnGhi;
    private ImageView imgTich;
    private Button btnXoa;
    private Button btnLuu;
    private Button btnCamera;
    private Button btnChonAnh;
    private EditText editTien;
    private EditText editDienGiai;
    private TextView txtNgay;
    private TextView txtGio;
    private EditText editChuyenDi;
    private EditText editChiChoAi;
    private EditText editDiaDiem;
    private TextView txtChonMau;
    private ImageView imgHinh;


    public static String tien;
    public static String dienGiai;
    public static String ngay;
    public static String gio;
    public static String chuyenDi;
    public static String chiChoAi;
    public static String diaDiem;
    public static String ngayDB;
    public static String thangDB;
    public static String namDB;
    public int id_thu_chi = -1;
    public static Bitmap bitmap;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_chi_tien_update, container, false);
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
        txtChonMau = view.findViewById(R.id.txtChonMau);
        imgHinh = view.findViewById(R.id.imgHinh);
        btnLuu = view.findViewById(R.id.btnLuu);
        btnXoa = view.findViewById(R.id.btnXoa);
        btnCamera = view.findViewById(R.id.btnCamera);
        btnChonAnh = view.findViewById(R.id.btnChonAnh);
        //ngày giờ ban dầu
        String dateNow ;
        String timeNow;
        final MyTime myTime = new TimeUtils().getTime();
        dateNow = myTime.getMyTime();
        String arr[] = dateNow.split("/");
        ngayDB = arr[0];
        thangDB = arr[1];
        namDB = arr[2];

        dateNow = "Hôm nay - "+dateNow;
        txtNgay.setText(dateNow);

        timeNow = myTime.getMyCurrentTime();
        txtGio.setText(timeNow);

        Bundle bundle = getArguments();
        if (bundle != null){
            String str = bundle.getString("key");
            textView.setText(str);
            int anh = bundle.getInt("anh");
            String id_ghi_chep_mau = bundle.getString("id_ghi_chep_mau");
            id_thu_chi = bundle.getInt("id");
            if(id_thu_chi>0){
                DatabaseHandler db = new DatabaseHandler(getContext());
                ChiTien chiTien = db.chiTienTheoId(id_thu_chi);

                editTien.setText(String.valueOf(chiTien.getTien()));
                editDienGiai.setText(chiTien.getDienGiai());
                editChuyenDi.setText(chiTien.getChuyenDi());
                editChiChoAi.setText(chiTien.getChiChoAi());
                editDiaDiem.setText(chiTien.getDiaDiem());
                textView.setText(chiTien.getHangMuc());
                anhMuc.setImageResource(new ConfigImage().getImage(chiTien.getHangMuc()));
                bitmap = BitmapFactory.decodeByteArray(chiTien.getAnh(), 0, chiTien.getAnh().length);
                imgHinh.setImageBitmap(bitmap);

                txtNgay.setText(chiTien.getNgay()+"/"+chiTien.getThang()+"/"+chiTien.getNam());
                txtGio.setText(chiTien.getGio());
            }
             else  if(id_ghi_chep_mau!=null){
                int id = Integer.parseInt(id_ghi_chep_mau.split(" ")[0]);
                DatabaseHandler db = new DatabaseHandler(getContext());
                GhiChepMauChi ghiChepMauChi = db.getGCMChiById(id);
                System.out.println("Ghi chép mẫu đây: "+ ghiChepMauChi.toString());

                editTien.setText(String.valueOf(ghiChepMauChi.getTien()));
                editDienGiai.setText(ghiChepMauChi.getDienGiai());
                editChuyenDi.setText(ghiChepMauChi.getChuyenDi());
                editChiChoAi.setText(ghiChepMauChi.getChiChoAi());
                editDiaDiem.setText(ghiChepMauChi.getDiaDiem());
                textView.setText(ghiChepMauChi.getHangMuc());
                anhMuc.setImageResource(new ConfigImage().getImage(ghiChepMauChi.getHangMuc()));
            }else {
                anhMuc.setImageResource(anh);
                editTien.setText(tien);
                editDienGiai.setText(dienGiai);
                txtNgay.setText(ngay);
                txtGio.setText(gio);
                editChuyenDi.setText(chuyenDi);
                editChiChoAi.setText(chiChoAi);
                editDiaDiem.setText(diaDiem);
                imgHinh.setImageBitmap(bitmap);
            }
        }



        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler db = new DatabaseHandler(getContext());
                boolean result = db.xoaChiTien(id_thu_chi);
                if(result == true){
                    Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_LONG).show();
                    LichSuFragment chiTienFragment = new LichSuFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container,chiTienFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }else{
                    Toast.makeText(getContext(), "Xóa thất bại", Toast.LENGTH_LONG).show();
                }
            }
        });

        txtNgay.setOnClickListener(new View.OnClickListener() {
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
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgHinh.getDrawable();
                bitmap = bitmapDrawable.getBitmap();

                HangMucCapNhatChiTienFragment hangMucChiFragment = new HangMucCapNhatChiTienFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, hangMucChiFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }

        });

        imgTich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgHinh.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                byte[] hinhAnh = byteArray.toByteArray();
                DatabaseHandler databaseHandler = new DatabaseHandler(getContext());
                ChiTien a  = new ChiTien (Long.parseLong(editTien.getText().toString()),textView.getText().toString(),
                        editDienGiai.getText().toString(),ngayDB,txtGio.getText().toString()
                        ,editChuyenDi.getText().toString(),editChiChoAi.getText().toString(),editDiaDiem.getText().toString(),hinhAnh,thangDB,namDB,id_thu_chi);
//                System.out.println("NOW:"+ a.toString());
                boolean result = databaseHandler.capNhatChiTien(a);
                if(result) {
                    Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_LONG).show();
                    String resultStr = checkHCM(a.getHangMuc(),txtNgay.getText().toString(),Long.parseLong(editTien.getText().toString()));
                    ArrayList<HanMucChi> list = databaseHandler.tatCaHanMucChi();
                    databaseHandler.updateHanMucChi(list,a.getHangMuc(),a.getTien(),txtNgay.getText().toString()+"-");
                    if(resultStr.compareTo("")>0){
                        int NOTIFICATION_ID = 234;
                        NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                        String CHANNEL_ID = "Notification from QTTC";
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

                            CharSequence name = "NOTE";
                            String Description = "This is my channel";
                            int importance = NotificationManager.IMPORTANCE_HIGH;
                            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                            mChannel.setDescription(Description);
                            mChannel.enableLights(true);
                            mChannel.setLightColor(Color.RED);
                            mChannel.enableVibration(true);
                            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                            mChannel.setShowBadge(false);
                            notificationManager.createNotificationChannel(mChannel);
                        }
                        Intent intent  = new Intent(getContext(),HanMucChiFragment.class);
                        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(),1,intent,0);
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Hạn mức chi")
                                .setContentText("Hạn mức: "+ resultStr.trim() + " đã bội chi. bạn hãy điều chỉnh thói quen chi tiêu nhé")
                                .setContentIntent(pendingIntent).setAutoCancel(true);
                        notificationManager.notify(NOTIFICATION_ID, builder.build());
                    }
                    LichSuFragment chiTienFragment = new LichSuFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container,chiTienFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }else{
                    Toast.makeText(getContext(), "Cập nhật thất bại", Toast.LENGTH_LONG).show();
                }
                ArrayList<ChiTien> lisst = databaseHandler.tatCaChi();
                for(ChiTien e: lisst){
                    System.out.println(e);
                }
                databaseHandler.close();
            }
        });
        txtChonMau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tien = editTien.getText().toString();
                dienGiai = editDienGiai.getText().toString();
                ngay = txtNgay.getText().toString();
                gio = txtGio.getText().toString();
                chuyenDi = editChuyenDi.getText().toString();
                chiChoAi = editChiChoAi.getText().toString();
                diaDiem = editDiaDiem.getText().toString();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgHinh.getDrawable();
                bitmap = bitmapDrawable.getBitmap();

                GhiChepMauChiFragment thuTienFragment  = new GhiChepMauChiFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,thuTienFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgHinh.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                byte[] hinhAnh = byteArray.toByteArray();
                DatabaseHandler databaseHandler = new DatabaseHandler(getContext());
                ChiTien a  = new ChiTien (Long.parseLong(editTien.getText().toString()),textView.getText().toString(),
                        editDienGiai.getText().toString(),ngayDB,txtGio.getText().toString()
                        ,editChuyenDi.getText().toString(),editChiChoAi.getText().toString(),editDiaDiem.getText().toString(),hinhAnh ,thangDB,namDB,id_thu_chi);
//                System.out.println("NOW:"+ a.toString());
                boolean result = databaseHandler.capNhatChiTien(a);
                if(result) {
                    Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_LONG).show();
                    String resultStr = checkHCM(a.getHangMuc(),txtNgay.getText().toString(),Long.parseLong(editTien.getText().toString()));
                    ArrayList<HanMucChi> list = databaseHandler.tatCaHanMucChi();
                    databaseHandler.updateHanMucChi(list,a.getHangMuc(),a.getTien(),txtNgay.getText().toString()+"-");
                    if(resultStr.compareTo("")>0){
                        int NOTIFICATION_ID = 234;
                        NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                        String CHANNEL_ID = "Notification from QTTC";
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

                            CharSequence name = "NOTE";
                            String Description = "This is my channel";
                            int importance = NotificationManager.IMPORTANCE_HIGH;
                            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                            mChannel.setDescription(Description);
                            mChannel.enableLights(true);
                            mChannel.setLightColor(Color.RED);
                            mChannel.enableVibration(true);
                            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                            mChannel.setShowBadge(false);
                            notificationManager.createNotificationChannel(mChannel);
                        }
                        Intent intent  = new Intent(getContext(),HanMucChiFragment.class);
                        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(),1,intent,0);
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Hạn mức chi")
                                .setContentText("Hạn mức: "+ resultStr.trim() + " đã bội chi. bạn hãy điều chỉnh thói quen chi tiêu nhé")
                                .setContentIntent(pendingIntent).setAutoCancel(true);
                        notificationManager.notify(NOTIFICATION_ID, builder.build());
                    }
                    LichSuFragment chiTienFragment = new LichSuFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container,chiTienFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }else{
                    Toast.makeText(getContext(), "Cập nhật thất bại", Toast.LENGTH_LONG).show();
                }
                ArrayList<ChiTien> lisst = databaseHandler.tatCaChi();
                for(ChiTien e: lisst){
                    System.out.println(e);
                }
                databaseHandler.close();
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
    public String checkHCM(String hangMuc,String ngay, long tien){

        DatabaseHandler databaseHandler = new DatabaseHandler(getContext());
        ArrayList<HanMucChi> hanMucChis = databaseHandler.tatCaHanMucChi();
        String  result="";
        for(HanMucChi e:hanMucChis){
            String ngaySS = ngay.split("-")[0].trim();
            String ngayBD = e.getNgayBatDau().split("-")[1].trim();
            String ngayKT = e.getNgayKetThuc().split("-")[1].trim();

            if((e.getHangMuc().equals(hangMuc) && ngaySS.compareTo(ngayBD) >=0 && ngaySS.compareTo(ngayKT)<=0 && e.getSoTien()<=tien)
                    ||(e.getHangMuc().equals(hangMuc)&&e.getBoiChi() < 0)){
                result += e.getTen();
                result +=" ";
            }
        };
        databaseHandler.close();
        return result;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
