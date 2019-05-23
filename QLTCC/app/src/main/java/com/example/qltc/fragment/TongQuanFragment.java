package com.example.qltc.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qltc.R;
import com.example.qltc.Screenshot;
import com.example.qltc.database.DatabaseHandler;
import com.example.qltc.model.ChiTien;
import com.example.qltc.model.ConfigImage;
import com.example.qltc.model.MyTime;
import com.example.qltc.model.ThuTien;
import com.example.qltc.model.TimeUtils;
import com.example.qltc.model.User;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.ShareApi;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
//import com.github.mikephil.charting.charts.BarChart;
//import com.github.mikephil.charting.data.BarData;
//import com.github.mikephil.charting.data.BarDataSet;
//import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TongQuanFragment extends Fragment {

    //    private BarChart barChart;
    private ImageView shareFacebook;
    private View cotThu;
    private View cotChi;
    private TextView textView;
    private ImageView anh1;
    private ImageView anh2;
    private ImageView anh3;
    private TextView ten1;
    private TextView ten2;
    private TextView ten3;
    private TextView thoiGian1;
    private TextView thoiGian2;
    private TextView thoiGian3;
    private TextView tien1;
    private TextView tien2;
    private TextView tien3;
    private LinearLayout layout1;
    private LinearLayout layout2;
    private LinearLayout layout3;

    private TextView chonThoiGian;
    private TextView tienThu;
    private TextView tienChi;
    private TextView tienTong;

    private TextView xemthem;
    DatabaseHandler databaseHandler;
    ArrayList<ChiTien> chiTiens = new ArrayList<>();
    ArrayList<ThuTien> thuTiens = new ArrayList<>();
    ThuTien thuTien1;
    ThuTien thuTien2;
    ChiTien chiTien1;
    ShareDialog shareDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tong_quan, container, false);
        chonThoiGian = view.findViewById(R.id.chonThoiGian);
        shareDialog = new ShareDialog(this);
        CallbackManager callbackManager = CallbackManager.Factory.create();

        DatabaseHandler databaseHandler = new DatabaseHandler(getContext());
        databaseHandler.themUser();
        databaseHandler.getUser();
        FacebookSdk.sdkInitialize(getContext());
        Bundle bundle = getArguments();
        if (bundle != null) {
            String time = bundle.getString("keyTime");
            System.out.println();
            if (time != null) {
                chonThoiGian.setText(time);
            }

        }

        textView = view.findViewById(R.id.money);
        anh1 = view.findViewById(R.id.anh1);
        anh2 = view.findViewById(R.id.anh2);
        anh3 = view.findViewById(R.id.anh3);

        ten1 = view.findViewById(R.id.ten1);
        ten2 = view.findViewById(R.id.ten2);
        ten3 = view.findViewById(R.id.ten3);

        thoiGian1 = view.findViewById(R.id.thoigian1);
        thoiGian2 = view.findViewById(R.id.thoigian2);
        thoiGian3 = view.findViewById(R.id.thoigian3);

        tien1 = view.findViewById(R.id.tien1);
        tien2 = view.findViewById(R.id.tien2);
        tien3 = view.findViewById(R.id.tien3);


        layout1 = view.findViewById(R.id.item1);
        layout2 = view.findViewById(R.id.item2);
        layout3 = view.findViewById(R.id.item3);

        tienThu = view.findViewById(R.id.tienThu);
        tienChi = view.findViewById(R.id.tienChi);
        tienTong = view.findViewById(R.id.tienTong);

        cotThu = view.findViewById(R.id.cotThu);
        cotChi = view.findViewById(R.id.cotChi);
        xemthem = view.findViewById(R.id.xemthem);
        shareFacebook = view.findViewById(R.id.shareFacebook);

//        barChart = view.findViewById(R.id.barChart);

        databaseHandler = new DatabaseHandler(getContext());
        databaseHandler.themUser();
        User user = databaseHandler.getUser();

        if (user.getBalance() > 0) {
            textView.setText(String.valueOf(user.getBalance()));
            textView.setTextColor(Color.parseColor("#24ABE6"));
        } else {
            textView.setText(String.valueOf(user.getBalance()));
            textView.setTextColor(Color.parseColor("#E41111"));
        }
        tinhHinhThuChi();
        ghiChepGanDay();

        chonThoiGian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HanMucChiThoiGianFragment hangMucChiFragment = new HanMucChiThoiGianFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, hangMucChiFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        xemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LichSuFragment hangMucChiFragment = new LichSuFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, hangMucChiFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CapNhatThuTienFragment xoaHanMucChiFragment = new CapNhatThuTienFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id", thuTien1.getId());
                xoaHanMucChiFragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, xoaHanMucChiFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CapNhatThuTienFragment xoaHanMucChiFragment = new CapNhatThuTienFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id", thuTien1.getId());
                xoaHanMucChiFragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, xoaHanMucChiFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CapNhatChiTienFragment xoaHanMucChiFragment = new CapNhatChiTienFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id", chiTien1.getId());
                xoaHanMucChiFragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, xoaHanMucChiFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        Target target = new Target(){
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                SharePhoto sharePhoto = new SharePhoto.Builder()
                        .setBitmap(bitmap)
                        .build();

                if(ShareDialog.canShow(SharePhotoContent.class)){
                    SharePhotoContent  content = new SharePhotoContent.Builder()
                            .addPhoto(sharePhoto)
                            .build();
                    shareDialog.show(content);
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        shareFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bitmap b = Screenshot.takescreenshotOfRootView(v);
//                Bitmap c = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.add_filled_100px);
//                SharePhoto photo = new SharePhoto.Builder()
////                        .setBitmap(c)
//                        .setCaption("Chia sẻ từ QLTC")
//                        .build();
//                ArrayList<SharePhoto> photos = new ArrayList<>();
//                photos.add(photo);
//                SharePhotoContent photoContent = new SharePhotoContent.Builder()
////                        .addPhoto(photo)
//                        .build();
//                if (ShareDialog.canShow(SharePhotoContent.class)) {
//
//                    shareDialog.show(photoContent);
//                }
//
//                shareDialog.show(photoContent);

                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setQuote("From QLTC")
                        .setContentUrl(Uri.parse("https://www.youtube.com/watch?v=FoJ-9XEe4Hw"))
//                        .setImageUrl(Uri.parse("https://www.google.com/search?q=gai+xinh&rlz=1C1SQJL_viVN836VN836&source=lnms&tbm=isch&sa=X&ved=0ahUKEwjypbDS_afiAhXk-GEKHS9_A64Q_AUIDigB&biw=1366&bih=657#imgrc=GRwQubJjNMqODM:"))
                        .build();
                if(ShareDialog.canShow(ShareLinkContent.class)){
                    shareDialog.show(linkContent);
                }
            }
        });
        return view;
    }

    public void tinhHinhThuChi() {
        databaseHandler = new DatabaseHandler(getContext());
        if (chonThoiGian.getText().toString().compareTo("Tháng này") == 0) {
            MyTime myTime = new TimeUtils().getTime();
            String time = myTime.getMyTime();
            System.out.println("String " + time.split("/")[1] + time.split("/")[2]);
            Map<String, Long> map = new HashMap<>();
            map = databaseHandler.thongKeThang(time.split("/")[1], time.split("/")[2]);
            if (map.size() > 0) {
                tienThu.setText(String.valueOf(map.get("Thu")));
                tienChi.setText(String.valueOf(map.get("Chi")));
                tienTong.setText(String.valueOf(map.get("Tong")));

                ViewGroup.LayoutParams lp1 = cotThu.getLayoutParams();
                lp1.width = 150;
                lp1.height = (int) (((double) (map.get("Thu")) / (map.get("Thu") + map.get("Chi"))) * 100) * 3;
                cotThu.requestLayout();
                System.out.println("lp1:" + lp1.height);
                System.out.println("lp1:" + lp1.width);
                ViewGroup.LayoutParams lp2 = cotChi.getLayoutParams();
                lp2.width = 150;
                lp2.height = (int) (((double) (map.get("Chi")) / (map.get("Thu") + map.get("Chi"))) * 100) * 3;
                System.out.println("lp2:" + lp2.height);
                System.out.println("lp2:" + lp2.width);
                cotChi.requestLayout();
            }
        }
        if (chonThoiGian.getText().toString().compareTo("Năm nay") == 0) {
            MyTime myTime = new TimeUtils().getTime();
            String time = myTime.getMyTime();
            Map<String, Long> map = new HashMap<>();
            map = databaseHandler.thongKeNam(time.split("/")[2]);
            if (map.size() > 0) {
                tienThu.setText(String.valueOf(map.get("Thu")));
                tienChi.setText(String.valueOf(map.get("Chi")));
                tienTong.setText(String.valueOf(map.get("Tong")));

                ViewGroup.LayoutParams lp1 = cotThu.getLayoutParams();
                lp1.width = 150;
                lp1.height = (int) (((double) (map.get("Thu")) / (map.get("Thu") + map.get("Chi"))) * 100) * 3;
                cotThu.requestLayout();

                ViewGroup.LayoutParams lp2 = cotChi.getLayoutParams();
                lp2.width = 150;
                lp2.height = (int) (((double) (map.get("Chi")) / (map.get("Thu") + map.get("Chi"))) * 100) * 3;
                cotChi.requestLayout();
            }
        }
        if (chonThoiGian.getText().toString().compareTo("Hôm nay") == 0) {
            MyTime myTime = new TimeUtils().getTime();
            String time = myTime.getMyTime();
            System.out.println("String " + time.split("/")[1] + time.split("/")[2]);
            Map<String, Long> map = new HashMap<>();
            map = databaseHandler.thongKeNgay(time.split("/")[0], time.split("/")[1], time.split("/")[2]);
            if (map.size() > 0) {
                tienThu.setText(String.valueOf(map.get("Thu")));
                tienChi.setText(String.valueOf(map.get("Chi")));
                tienTong.setText(String.valueOf(map.get("Tong")));
                ViewGroup.LayoutParams lp1 = cotThu.getLayoutParams();
                lp1.width = 150;
                lp1.height = (int) (((double) (map.get("Thu")) / (map.get("Thu") + map.get("Chi"))) * 100) * 3;
                System.out.println("lp1:" + lp1.height);
                System.out.println("lp1:" + lp1.width);
                cotThu.requestLayout();

                ViewGroup.LayoutParams lp2 = cotChi.getLayoutParams();

                lp2.width = 150;
                lp2.height = (int) (((double) (map.get("Chi")) / (map.get("Thu") + map.get("Chi"))) * 100) * 3;
                System.out.println("lp2:" + lp2.height);
                System.out.println("lp2:" + lp2.width);

                cotChi.requestLayout();
            }

        }
    }

    public void ghiChepGanDay() {
        databaseHandler = new DatabaseHandler(getContext());
        thuTiens = databaseHandler.tatCaThu();
        chiTiens = databaseHandler.tatCaChi();
        if (thuTiens.size() > 2 && chiTiens.size() > 1) {
            thuTien1 = thuTiens.get(thuTiens.size() - 2);
            thuTien2 = thuTiens.get(thuTiens.size() - 1);
            chiTien1 = chiTiens.get(chiTiens.size() - 1);

            anh1.setImageResource(new ConfigImage().getImage(thuTien1.getHangMuc()));
            anh2.setImageResource(new ConfigImage().getImage(thuTien2.getHangMuc()));
            anh3.setImageResource(new ConfigImage().getImage(chiTien1.getHangMuc()));

            ten1.setText(thuTien1.getHangMuc());
            ten2.setText(thuTien2.getHangMuc());
            ten3.setText(chiTien1.getHangMuc());

            String time1 = thuTien1.getNgay() + "/" + thuTien1.getThang() + "/" + thuTien1.getNam();
            String time2 = thuTien2.getNgay() + "/" + thuTien2.getThang() + "/" + thuTien2.getNam();
            String time3 = chiTien1.getNgay() + "/" + chiTien1.getThang() + "/" + chiTien1.getNam();

            MyTime myTime = new TimeUtils().getTime();

            String timeNow = myTime.getMyTime();

            System.out.println("MY TIME: " + myTime.getMyTime());
            System.out.println("TIME 1: " + time1);
            System.out.println("TIME 2: " + time2);
            System.out.println("TIME 3: " + time3);
            if (timeNow.compareTo(time1) == 0) {
                thoiGian1.setText("Hôm nay");
            } else {
                thoiGian1.setText(time1);
            }
            if (timeNow.compareTo(time2) == 0) {
                thoiGian2.setText("Hôm nay");
            } else {
                thoiGian2.setText(time2);
            }
            if (timeNow.compareTo(time3) == 0) {
                thoiGian3.setText("Hôm nay");
            } else {
                thoiGian3.setText(time3);
            }
            tien1.setText(String.valueOf(thuTien1.getTien()));
            tien2.setText(String.valueOf(thuTien2.getTien()));
            tien3.setText(String.valueOf(chiTien1.getTien()));
        }
    }

}
