package com.example.qltc.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import com.example.qltc.model.ChiTien;
import com.example.qltc.model.HanMucChi;
import com.example.qltc.model.ThuTien;
import com.example.qltc.model.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "abcd.db";
    private static final int DATABASE_VERSION = 1;
    //private static Context mContext;

    private static final String CREATE_TABLE_PAYRECORD = "CREATE TABLE pay_record( id INTEGER PRIMARY KEY AUTOINCREMENT, tien INTEGER, hang_muc VARCHAR(200)," +
            "dien_giai VARCHAR(200), ngay VARCHAR(200), chuyen_di VARCHAR(200), chi_cho_ai VARCHAR(200), dia_diem VARCHAR(200), gio VARCHAR(200), thang VARCHAR(200), nam VARCHAR(200),anh INTEGER)";
    private static final String CREATE_TABLE_REVENUERECORD = "CREATE TABLE revenue_record( id INTEGER PRIMARY KEY AUTOINCREMENT, tien INTEGER, hang_muc VARCHAR(200)," +
            "dien_giai VARCHAR(200), ngay VARCHAR(200), chuyen_di VARCHAR(200), thu_tu_ai VARCHAR(200), dia_diem VARCHAR(200), gio VARCHAR(200),thang VARCHAR(200), nam VARCHAR(200),anh INTEGER)";
    private static final String CREATE_TABLE_USER = "CREATE TABLE user( id INTEGER PRIMARY KEY AUTOINCREMENT, username VARCHAR(200), password VARCHAR(200), balance INTERGER)";
    private static final String CREATE_TABLE_HAN_MUC_CHI = "CREATE TABLE han_muc_chi( id INTEGER PRIMARY KEY AUTOINCREMENT, tien INTERGER, ten VARCHAR(200)"
            + ", hang_muc VARCHAR(200), xoay_vong VARCHAR(200), bat_dau VARCHAR(200), ket_thuc VARCHAR(200),boi_chi INTEGER)";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PAYRECORD);
        db.execSQL(CREATE_TABLE_REVENUERECORD);
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_HAN_MUC_CHI);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS pay_record");
        db.execSQL("DROP TABLE IF EXISTS revenue_record");
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS han_muc_chi");
    }

    public boolean themChiTien(ChiTien chiTien) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("tien", chiTien.getTien());
            values.put("hang_muc", chiTien.getHangMuc());
            values.put("dien_giai", chiTien.getDienGiai());
            values.put("ngay", chiTien.getNgay());
            values.put("chuyen_di", chiTien.getChuyenDi());
            values.put("chi_cho_ai", chiTien.getChiChoAi());
            values.put("dia_diem", chiTien.getDiaDiem());
            values.put("gio", chiTien.getGio());
            values.put("thang", chiTien.getThang());
            values.put("nam", chiTien.getNam());
            values.put("anh", chiTien.getAnh());
            db.insert("pay_record", null, values);

            Cursor c = db.rawQuery("SELECT * FROM user",null);

            c.moveToFirst();
            long balance = c.getLong(3);

            ContentValues values1 = new ContentValues();
            values1.put("balance",balance-chiTien.getTien());
            long result = db.update("user",values1,"id = "+1,null);
            db.close();

            return result > 0 ? true : false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public void updateHanMucChi(ArrayList<HanMucChi> list,String hangMuc,long tienTru,String ngay){
        for(HanMucChi e: list){
            SQLiteDatabase db = this.getWritableDatabase();
            String ngaySS = ngay.split("-")[1].trim();
            String ngayBD = e.getNgayBatDau().split("-")[1].trim();
            String ngayKT = e.getNgayKetThuc().split("-")[1].trim();
            if(e.getHangMuc().equals(hangMuc)&& ngaySS.compareTo(ngayBD) >=0 && ngaySS.compareTo(ngayKT)<=0){
                ContentValues values = new ContentValues();
                values.put("boi_chi",e.getBoiChi()-tienTru);
                db.update("han_muc_chi",values,"hang_muc='"+hangMuc+"' and id="+e.getId(),null);
            }
            db.close();
        }
    }
    public boolean updateHanMucChi(HanMucChi hanMucChi){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c = db.rawQuery("SELECT boi_chi,tien FROM han_muc_chi WHERE id = "+hanMucChi.getId(),null);
            c.moveToFirst();
            long boiChi = c.getLong(0);
            long tien = c.getLong(1);
            System.out.println("Boi chi truoc do: "+ boiChi);
            System.out.println("Tien truoc do: "+ tien);
            System.out.println("So tien cap nhat: "+ hanMucChi.getSoTien());

            ContentValues values = new ContentValues();
            values.put("tien",hanMucChi.getSoTien());
            values.put("ten",hanMucChi.getTen());
            values.put("hang_muc",hanMucChi.getHangMuc());
            values.put("xoay_vong",hanMucChi.getXoayVong());
            values.put("bat_dau",hanMucChi.getNgayBatDau());
            values.put("ket_thuc",hanMucChi.getNgayKetThuc());
            values.put("boi_chi",hanMucChi.getSoTien() - (tien - boiChi));

            long result = db.update("han_muc_chi",values,"id ="+hanMucChi.getId(),null);
            db.close();
            return result > 0 ? true : false;

        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean xoaHanMucChi(int id){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            long result = db.delete("han_muc_chi","id="+id,null);
            return result>0? true:false;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean themThuTien(ThuTien thuTien) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put("ngay", thuTien.getNgay());
            values.put("thu_tu_ai", thuTien.getThuTuAi());
            values.put("dia_diem", thuTien.getDiaDiem());
            values.put("chuyen_di", thuTien.getChuyenDi());
            values.put("dien_giai", thuTien.getDienGiai());
            values.put("tien", thuTien.getTien());
            values.put("hang_muc", thuTien.getHangMuc());
            values.put("gio", thuTien.getGio());
            values.put("thang", thuTien.getThang());
            values.put("nam", thuTien.getNam());
            values.put("anh", thuTien.getAnh());

           db.insert("revenue_record", null, values);

            Cursor c = db.rawQuery("SELECT * FROM user",null);

            c.moveToFirst();
            long balance = c.getLong(3);

            ContentValues values1 = new ContentValues();
            values1.put("balance",balance+thuTien.getTien());
            long result = db.update("user",values1,"id = "+1,null);
            db.close();

            return result > 0 ? true : false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<ThuTien> tatCaThu() {
        ArrayList<ThuTien> thuTiens = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("revenue_record", null, null, new String[]{}, null, null, null);
        while (cursor.moveToNext()) {
            thuTiens.add(new ThuTien(cursor.getLong(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9),
                    cursor.getString(10),
                    cursor.getInt(11)));
        }
        db.close();
        return thuTiens;
    }
    public boolean themHanMucChi(HanMucChi hanMucChi){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("tien",hanMucChi.getSoTien());
            values.put("ten",hanMucChi.getTen());
            values.put("hang_muc",hanMucChi.getHangMuc());
            values.put("xoay_vong",hanMucChi.getXoayVong());
            values.put("bat_dau",hanMucChi.getNgayBatDau());
            values.put("ket_thuc",hanMucChi.getNgayKetThuc());
            values.put("boi_chi",hanMucChi.getBoiChi());
            long i = db.insert("han_muc_chi",null,values);
            db.close();
            return i > 0 ? true:false;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<HanMucChi> tatCaHanMucChi(){
        try{
            ArrayList<HanMucChi> hanMucChis = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query("han_muc_chi", null, null, new String[]{}, null, null, null);
            while (cursor.moveToNext()) {
                hanMucChis.add(new HanMucChi(cursor.getLong(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getLong(7),
                        cursor.getInt(0)));
            }
            db.close();
            return hanMucChis;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<ChiTien> tatCaChi() {
        ArrayList<ChiTien> chiTienList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("pay_record", null, null, new String[]{}, null, null, null);
        while (cursor.moveToNext()) {
            chiTienList.add(new ChiTien(cursor.getLong(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getInt(9),
                    cursor.getString(10),
                    cursor.getString(11)));
        }
        db.close();
        return chiTienList;
    }

    public boolean themUser() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("password", "test");
            values.put("balance", 0);
            values.put("username", "test");

            long result = db.insert("user", null, values);
            db.close();
            return result > 0 ? true : false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("user", null, null, new String[]{}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        User student = new User(cursor.getString(1), cursor.getString(2), cursor.getLong(3));
        db.close();
        return student;
    }

}
