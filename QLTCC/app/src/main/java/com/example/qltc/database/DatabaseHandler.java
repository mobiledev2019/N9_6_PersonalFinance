package com.example.qltc.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import com.example.qltc.model.ChiTien;
import com.example.qltc.model.GhiChepMauChi;
import com.example.qltc.model.GhiChepMauThu;
import com.example.qltc.model.HanMucChi;
import com.example.qltc.model.NganHang;
import com.example.qltc.model.ThuTien;
import com.example.qltc.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "abcd.db";
    private static final int DATABASE_VERSION = 1;
    //private static Context mContext;

    private static final String CREATE_TABLE_PAYRECORD = "CREATE TABLE pay_record( id INTEGER PRIMARY KEY AUTOINCREMENT, tien INTEGER, hang_muc VARCHAR(200)," +
            "dien_giai VARCHAR(200), ngay VARCHAR(200), chuyen_di VARCHAR(200), chi_cho_ai VARCHAR(200), dia_diem VARCHAR(200), gio VARCHAR(200), thang VARCHAR(200), nam VARCHAR(200),anh BLOB)";
    private static final String CREATE_TABLE_REVENUERECORD = "CREATE TABLE revenue_record( id INTEGER PRIMARY KEY AUTOINCREMENT, tien INTEGER, hang_muc VARCHAR(200)," +
            "dien_giai VARCHAR(200), ngay VARCHAR(200), chuyen_di VARCHAR(200), thu_tu_ai VARCHAR(200), dia_diem VARCHAR(200), gio VARCHAR(200),thang VARCHAR(200), nam VARCHAR(200),anh BLOB)";
    private static final String CREATE_TABLE_USER = "CREATE TABLE user( id INTEGER PRIMARY KEY AUTOINCREMENT, username VARCHAR(200), password VARCHAR(200), balance INTERGER)";
    private static final String CREATE_TABLE_HAN_MUC_CHI = "CREATE TABLE han_muc_chi( id INTEGER PRIMARY KEY AUTOINCREMENT, tien INTERGER, ten VARCHAR(200)"
            + ", hang_muc VARCHAR(200), xoay_vong VARCHAR(200), bat_dau VARCHAR(200), ket_thuc VARCHAR(200),boi_chi INTEGER)";
    private static final String CREATE_TABLE_GCM_CHI = "CREATE TABLE ghi_chep_mau_chi( id INTEGER PRIMARY KEY AUTOINCREMENT,ten VARCHAR(200), tien INTEGER, hang_muc VARCHAR(200)," +
            "dien_giai VARCHAR(200), ngay VARCHAR(200), chuyen_di VARCHAR(200), chi_cho_ai VARCHAR(200), dia_diem VARCHAR(200), gio VARCHAR(200), thang VARCHAR(200), nam VARCHAR(200),anh INTEGER)";
    private static final String CREATE_TABLE_GCM_THU = "CREATE TABLE ghi_chep_mau_thu( id INTEGER PRIMARY KEY AUTOINCREMENT,ten VARCHAR(200), tien INTEGER, hang_muc VARCHAR(200)," +
            "dien_giai VARCHAR(200), ngay VARCHAR(200), chuyen_di VARCHAR(200), thu_tu_ai VARCHAR(200), dia_diem VARCHAR(200), gio VARCHAR(200),thang VARCHAR(200), nam VARCHAR(200),anh INTEGER)";
    private static final String CREATE_TABLE_NGAN_HANG = "CREATE TABLE ngan_hang(id INTEGER PRIMARY KEY AUTOINCREMENT, ten VARCHAR(200))";
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
        db.execSQL(CREATE_TABLE_GCM_CHI);
        db.execSQL(CREATE_TABLE_GCM_THU);
        db.execSQL(CREATE_TABLE_NGAN_HANG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS pay_record");
        db.execSQL("DROP TABLE IF EXISTS revenue_record");
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS han_muc_chi");
        db.execSQL("DROP TABLE IF EXISTS ghi_chep_mau_chi");
        db.execSQL("DROP TABLE IF EXISTS ghi_chep_mau_thu");
        db.execSQL("DROP TABLE IF EXISTS ngan_hang");
    }
    public boolean capNhatThuTien(ThuTien thuTien){
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM user", null);
            c.moveToFirst();
            long balance = c.getLong(3);
            Cursor cursor = db.rawQuery("SELECT * FROM revenue_record WHERE id = "+thuTien.getId(),null);
            cursor.moveToFirst();
            long oldValue = cursor.getLong(cursor.getColumnIndex("tien"));
            long newValue = thuTien.getTien();
            ContentValues values1 = new ContentValues();
            values1.put("balance", balance + -oldValue + newValue);
            long result = db.update("user", values1, "id = " + 1, null);

            ContentValues valuesUpdate = new ContentValues();
            //                long tien, String hangMuc, String dienGiai, String ngay, String gio, String chuyenDi, String thuTuAi, String diaDiem, String thang, String nam, int anh, int id)
            valuesUpdate.put("tien",thuTien.getTien());
            valuesUpdate.put("hang_muc",thuTien.getHangMuc());
            valuesUpdate.put("dien_giai",thuTien.getDienGiai());
            valuesUpdate.put("ngay",thuTien.getNgay());
            valuesUpdate.put("gio",thuTien.getGio());
            valuesUpdate.put("chuyen_di",thuTien.getChuyenDi());
            valuesUpdate.put("thu_tu_ai",thuTien.getThuTuAi());
            valuesUpdate.put("dia_diem",thuTien.getDiaDiem());
            valuesUpdate.put("thang",thuTien.getThang());
            valuesUpdate.put("nam",thuTien.getNam());
            valuesUpdate.put("anh",thuTien.getAnh());
            long result2 = db.update("revenue_record",valuesUpdate,"id = "+thuTien.getId(),null);
            return  result2 > 0 ? true:false;


        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean capNhatChiTien(ChiTien chiTien){
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM user", null);
            c.moveToFirst();
            long balance = c.getLong(3);
            Cursor cursor = db.rawQuery("SELECT * FROM pay_record WHERE id = "+chiTien.getId(),null);
            cursor.moveToFirst();
            long oldValue = cursor.getLong(cursor.getColumnIndex("tien"));
            long newValue = chiTien.getTien();
            ContentValues values1 = new ContentValues();
            values1.put("balance", balance +oldValue - newValue);
            long result = db.update("user", values1, "id = " + 1, null);

            ContentValues valuesUpdate = new ContentValues();
//            (long tien, String hangMuc, String dienGiai, String ngay, String gio, String chuyenDi, String chiChoAi, String diaDiem, int anh, String thang, String nam)
            valuesUpdate.put("tien",chiTien.getTien());
            valuesUpdate.put("hang_muc",chiTien.getHangMuc());
            valuesUpdate.put("dien_giai",chiTien.getDienGiai());
            valuesUpdate.put("ngay",chiTien.getNgay());
            valuesUpdate.put("gio",chiTien.getGio());
            valuesUpdate.put("chuyen_di",chiTien.getChuyenDi());
            valuesUpdate.put("chi_cho_ai",chiTien.getChiChoAi());
            valuesUpdate.put("dia_diem",chiTien.getDiaDiem());
            valuesUpdate.put("thang",chiTien.getThang());
            valuesUpdate.put("nam",chiTien.getNam());
            valuesUpdate.put("anh",chiTien.getAnh());
            long result2 = db.update("pay_record",valuesUpdate,"id = "+chiTien.getId(),null);

            return  result2 > 0 ? true:false;


        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean xoaChiTien(int id){
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM user", null);
            c.moveToFirst();
            long balance = c.getLong(3);
            Cursor cursor = db.rawQuery("SELECT * FROM pay_record WHERE id = "+id,null);
            cursor.moveToFirst();
            long oldValue = cursor.getLong(cursor.getColumnIndex("tien"));
            ContentValues values1 = new ContentValues();
            values1.put("balance", balance +oldValue);
            long result = db.update("user", values1, "id = " + 1, null);
            long resultDelete = db.delete("pay_record","id="+id,null);
            return result >0?true:false;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaThuTien(int id){
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM user", null);
            c.moveToFirst();
            long balance = c.getLong(3);
            Cursor cursor = db.rawQuery("SELECT * FROM revenue_record WHERE id = "+id,null);
            cursor.moveToFirst();
            long oldValue = cursor.getLong(cursor.getColumnIndex("tien"));
            ContentValues values1 = new ContentValues();
            values1.put("balance", balance - oldValue);
            long result = db.update("user", values1, "id = " + 1, null);
            long resultDelete = db.delete("revenue_record","id="+id,null);

            return result >0?true:false;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public ArrayList<ThuTien> thuTienTheoNgay(String ngay,String thang,String nam){
        try {
            ArrayList<ThuTien> thuTiens = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM revenue_record WHERE ngay='" + ngay + "' AND thang ='" + thang + "' AND nam='" + nam + "'",null);
            while(cursor.moveToNext()){

                ThuTien a = (new ThuTien(cursor.getLong(cursor.getColumnIndex("tien")),
                        cursor.getString(cursor.getColumnIndex("hang_muc")),
                        cursor.getString(cursor.getColumnIndex("dien_giai")),
                        cursor.getString(cursor.getColumnIndex("ngay")),
                        cursor.getString(cursor.getColumnIndex("gio")),
                        cursor.getString(cursor.getColumnIndex("chuyen_di")),
                        cursor.getString(cursor.getColumnIndex("thu_tu_ai")),
                        cursor.getString(cursor.getColumnIndex("dia_diem")),
                        cursor.getString(cursor.getColumnIndex("thang")),
                        cursor.getString(cursor.getColumnIndex("nam")),
                        cursor.getBlob(cursor.getColumnIndex("anh")),
                        cursor.getInt(cursor.getColumnIndex("id"))));
                thuTiens.add(a);
            }
            db.close();
            cursor.close();
            return thuTiens;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<ThuTien> thuTienTheoThang(String thang,String nam){
        try {
            ArrayList<ThuTien> thuTiens = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM revenue_record WHERE thang ='" + thang + "' AND nam='" + nam + "'",null);
            while(cursor.moveToNext()){
                ThuTien a = (new ThuTien(cursor.getLong(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getBlob(11),
                        cursor.getInt(0)));
                thuTiens.add(a);
            }
            db.close();
            cursor.close();
            return thuTiens;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<ThuTien> thuTienTheoNam(String nam){
        try {
            ArrayList<ThuTien> thuTiens = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM revenue_record WHERE nam='" + nam + "'",null);
            while(cursor.moveToNext()){
                ThuTien a = (new ThuTien(cursor.getLong(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getBlob(11),
                        cursor.getInt(0)));
                thuTiens.add(a);
            }
            db.close();
            cursor.close();
            return thuTiens;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<ChiTien> chiTienTheoNgay(String ngay,String thang,String nam){
        try {
            ArrayList<ChiTien> chiTiens = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM pay_record WHERE ngay='" + ngay + "' AND thang ='" + thang + "' AND nam='" + nam + "'", null);
            while(cursor.moveToNext()){
                ChiTien a = (new ChiTien(cursor.getLong(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(8),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getBlob(11),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getInt(0)));
                chiTiens.add(a);
            }
            return chiTiens;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<ChiTien> chiTienTheoThang(String thang,String nam){
        try {
            ArrayList<ChiTien> chiTiens = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM pay_record WHERE thang ='" + thang + "' AND nam='" + nam + "'", null);
            while(cursor.moveToNext()){
                ChiTien a = (new ChiTien(cursor.getLong(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(8),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getBlob(11),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getInt(0)));
                chiTiens.add(a);
            }
            return chiTiens;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<ChiTien> chiTienTheoNam(String nam){
        try {
            ArrayList<ChiTien> chiTiens = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM pay_record WHERE  nam='" + nam + "'", null);
            while(cursor.moveToNext()){
                ChiTien a = (new ChiTien(cursor.getLong(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(8),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getBlob(11),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getInt(0)));
                chiTiens.add(a);
            }
            return chiTiens;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public ThuTien thuTienTheoId(int id) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM revenue_record WHERE id = "+id,null);
            cursor.moveToFirst();
            ThuTien a = (new ThuTien(cursor.getLong(cursor.getColumnIndex("tien")),
                    cursor.getString(cursor.getColumnIndex("hang_muc")),
                    cursor.getString(cursor.getColumnIndex("dien_giai")),
                    cursor.getString(cursor.getColumnIndex("ngay")),
                    cursor.getString(cursor.getColumnIndex("gio")),
                    cursor.getString(cursor.getColumnIndex("chuyen_di")),
                    cursor.getString(cursor.getColumnIndex("thu_tu_ai")),
                    cursor.getString(cursor.getColumnIndex("dia_diem")),
                    cursor.getString(cursor.getColumnIndex("thang")),
                    cursor.getString(cursor.getColumnIndex("nam")),
                    cursor.getBlob(cursor.getColumnIndex("anh")),
                    cursor.getInt(cursor.getColumnIndex("id"))));
            db.close();
            cursor.close();
            return a;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ChiTien chiTienTheoId(int id) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM pay_record WHERE id="+id,null);
            cursor.moveToFirst();
            ChiTien a = (new ChiTien(cursor.getLong(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(8),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getBlob(11),
                    cursor.getString(9),
                    cursor.getString(10),
                    cursor.getInt(0)));
            cursor.close();
            db.close();
            return a;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, Long> thongKeNgay(String ngay, String thang, String nam) {
        try {
            Map<String, Long> map = new HashMap<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT SUM(tien) FROM pay_record WHERE ngay='" + ngay + "' AND thang ='" + thang + "' AND nam='" + nam + "'", null);
            cursor.moveToFirst();
            long tongChi = cursor.getLong(0);
            map.put("Chi", tongChi);
            cursor = db.rawQuery("SELECT SUM(tien) FROM revenue_record WHERE ngay='" + ngay + "' AND thang ='" + thang + "' AND nam='" + nam + "'", null);
            cursor.moveToFirst();
            long tongThu = cursor.getLong(0);
            map.put("Thu", tongThu);
            map.put("Tong", tongChi - tongThu);
            cursor.close();
            db.close();
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Map<String, Long> thongKeThang(String thang, String nam) {
        try {
            Map<String, Long> map = new HashMap<>();
            SQLiteDatabase db = this.getReadableDatabase();
            String sql = "SELECT SUM(tien) FROM pay_record WHERE thang ='" + thang + "' AND nam='" + nam + "'";
            System.out.println(sql);
            Cursor cursor = db.rawQuery("SELECT SUM(tien) FROM pay_record WHERE thang ='" + thang + "' AND nam='" + nam + "'", null);
            cursor.moveToFirst();
            long tongChi = cursor.getLong(0);

            map.put("Chi", tongChi);
            cursor = db.rawQuery("SELECT SUM(tien) FROM revenue_record WHERE thang ='" + thang + "' AND nam='" + nam + "'", null);
            cursor.moveToFirst();
            long tongThu = cursor.getLong(0);
            map.put("Thu", tongThu);
            map.put("Tong", tongChi - tongThu);
            cursor.close();
            db.close();

            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, Long> thongKeNam(String nam) {
        try {
            Map<String, Long> map = new HashMap<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT SUM(tien) FROM pay_record WHERE nam='" + nam + "'", null);
            cursor.moveToFirst();
            long tongChi = cursor.getLong(0);
            map.put("Chi", tongChi);
            cursor = db.rawQuery("SELECT SUM(tien) FROM revenue_record WHERE  nam='" + nam + "'", null);
            cursor.moveToFirst();
            long tongThu = cursor.getLong(0);
            map.put("Thu", tongThu);
            map.put("Tong", tongChi - tongThu);
            cursor.close();
            db.close();
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GhiChepMauChi getGCMChiById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM ghi_chep_mau_chi WHERE id = " + id, null);
        c.moveToFirst();
//        (int id, String ten, long tien, String hangMuc, String dienGiai, String ngay, String chuyenDi
//        , String chiChoAi, String diaDiem, String gio, String thang, String nam, int anh)
        return new GhiChepMauChi(
                c.getInt(0),
                c.getString(1),
                c.getLong(2),
                c.getString(3),
                c.getString(4),
                c.getString(5),
                c.getString(6),
                c.getString(7),
                c.getString(8),
                c.getString(9),
                c.getString(10),
                c.getString(11),
                c.getInt(12));
    }

    public GhiChepMauThu getGCMThuById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM ghi_chep_mau_thu WHERE id = " + id, null);
        c.moveToFirst();
//        (int id, String ten, long tien, String hangMuc, String dienGiai, String ngay, String chuyenDi
//        , String chiChoAi, String diaDiem, String gio, String thang, String nam, int anh)
        return new GhiChepMauThu(
                c.getInt(0),
                c.getString(1),
                c.getLong(2),
                c.getString(3),
                c.getString(4),
                c.getString(5),
                c.getString(6),
                c.getString(7),
                c.getString(8),
                c.getString(9),
                c.getString(10),
                c.getString(11),
                c.getInt(12));
    }

    public boolean themGCMChi(GhiChepMauChi ghiChepMauChi) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("ten", ghiChepMauChi.getTen());
            values.put("tien", ghiChepMauChi.getTien());
            values.put("hang_muc", ghiChepMauChi.getHangMuc());
            values.put("dien_giai", ghiChepMauChi.getDienGiai());
            values.put("ngay", ghiChepMauChi.getNgay());
            values.put("chuyen_di", ghiChepMauChi.getChuyenDi());
            values.put("chi_cho_ai", ghiChepMauChi.getChiChoAi());
            values.put("dia_diem", ghiChepMauChi.getDiaDiem());
            values.put("gio", ghiChepMauChi.getGio());
            values.put("thang", ghiChepMauChi.getThang());
            values.put("nam", ghiChepMauChi.getNam());
            values.put("anh", ghiChepMauChi.getAnh());
            long result = db.insert("ghi_chep_mau_chi", null, values);
            db.close();
            return result > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<GhiChepMauChi> tatCaGhiChepMauChi() {
        ArrayList<GhiChepMauChi> list = new ArrayList<>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query("ghi_chep_mau_chi", null, null, new String[]{}, null, null, null);
            while (cursor.moveToNext()) {
                list.add(new GhiChepMauChi(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getLong(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11),
                        cursor.getInt(12)));
            }
            db.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean themGCMThu(GhiChepMauThu ghiChepMauThu) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put("ten", ghiChepMauThu.getTen());
            values.put("ngay", ghiChepMauThu.getNgay());
            values.put("thu_tu_ai", ghiChepMauThu.getThuTuAi());
            values.put("dia_diem", ghiChepMauThu.getDiaDiem());
            values.put("chuyen_di", ghiChepMauThu.getChuyenDi());
            values.put("dien_giai", ghiChepMauThu.getDienGiai());
            values.put("tien", ghiChepMauThu.getTien());
            values.put("hang_muc", ghiChepMauThu.getHangMuc());
            values.put("gio", ghiChepMauThu.getGio());
            values.put("thang", ghiChepMauThu.getThang());
            values.put("nam", ghiChepMauThu.getNam());
            values.put("anh", ghiChepMauThu.getAnh());
            long result = db.insert("ghi_chep_mau_thu", null, values);
            db.close();
            return result > 0 ? true : false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<GhiChepMauThu> tatCaGhiChepMauThu() {
        ArrayList<GhiChepMauThu> list = new ArrayList<>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query("ghi_chep_mau_thu", null, null, new String[]{}, null, null, null);
            while (cursor.moveToNext()) {
                list.add(new GhiChepMauThu(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getLong(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11),
                        cursor.getInt(12)));
            }
            db.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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

            Cursor c = db.rawQuery("SELECT * FROM user", null);

            c.moveToFirst();
            long balance = c.getLong(3);
            System.out.println("Số dư hiện tại: " + balance);
            System.out.println("Tiền vào : " + chiTien.getTien());
            ContentValues values1 = new ContentValues();
            values1.put("balance", balance - chiTien.getTien());
            long result = db.update("user", values1, "id = " + 1, null);
            db.close();

            return result > 0 ? true : false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateHanMucChi(ArrayList<HanMucChi> list, String hangMuc, long tienTru, String ngay) {
        try {
            for (HanMucChi e : list) {
                SQLiteDatabase db = this.getWritableDatabase();
                String ngaySS = ngay.split("-")[1].trim();
                String ngayBD = e.getNgayBatDau().split("-")[1].trim();
                String ngayKT = e.getNgayKetThuc().split("-")[1].trim();
                if (e.getHangMuc().equals(hangMuc) && ngaySS.compareTo(ngayBD) >= 0 && ngaySS.compareTo(ngayKT) <= 0) {
                    ContentValues values = new ContentValues();
                    values.put("boi_chi", e.getBoiChi() - tienTru);
                    db.update("han_muc_chi", values, "hang_muc='" + hangMuc + "' and id=" + e.getId(), null);
                }
                db.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean updateHanMucChi(HanMucChi hanMucChi) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c = db.rawQuery("SELECT boi_chi,tien FROM han_muc_chi WHERE id = " + hanMucChi.getId(), null);
            c.moveToFirst();
            long boiChi = c.getLong(0);
            long tien = c.getLong(1);
            System.out.println("Boi chi truoc do: " + boiChi);
            System.out.println("Tien truoc do: " + tien);
            System.out.println("So tien cap nhat: " + hanMucChi.getSoTien());

            ContentValues values = new ContentValues();
            values.put("tien", hanMucChi.getSoTien());
            values.put("ten", hanMucChi.getTen());
            values.put("hang_muc", hanMucChi.getHangMuc());
            values.put("xoay_vong", hanMucChi.getXoayVong());
            values.put("bat_dau", hanMucChi.getNgayBatDau());
            values.put("ket_thuc", hanMucChi.getNgayKetThuc());
            values.put("boi_chi", hanMucChi.getSoTien() - (tien - boiChi));

            long result = db.update("han_muc_chi", values, "id =" + hanMucChi.getId(), null);
            db.close();
            return result > 0 ? true : false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaHanMucChi(int id) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            long result = db.delete("han_muc_chi", "id=" + id, null);
            return result > 0 ? true : false;
        } catch (Exception e) {
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

            Cursor c = db.rawQuery("SELECT * FROM user", null);

            c.moveToFirst();
            long balance = c.getLong(3);

            ContentValues values1 = new ContentValues();
            values1.put("balance", balance + thuTien.getTien());
            long result = db.update("user", values1, "id = " + 1, null);
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
                    cursor.getBlob(11),
                    cursor.getInt(0)));
        }
        db.close();
        return thuTiens;
    }

    public boolean themHanMucChi(HanMucChi hanMucChi) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("tien", hanMucChi.getSoTien());
            values.put("ten", hanMucChi.getTen());
            values.put("hang_muc", hanMucChi.getHangMuc());
            values.put("xoay_vong", hanMucChi.getXoayVong());
            values.put("bat_dau", hanMucChi.getNgayBatDau());
            values.put("ket_thuc", hanMucChi.getNgayKetThuc());
            values.put("boi_chi", hanMucChi.getBoiChi());
            long i = db.insert("han_muc_chi", null, values);
            db.close();
            return i > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<HanMucChi> tatCaHanMucChi() {
        try {
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
        } catch (Exception e) {
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
                    cursor.getString(8),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getBlob(11),
                    cursor.getString(9),
                    cursor.getString(10),
                    cursor.getInt(0)));
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

    public ArrayList<NganHang> tatCaNganHang(){
        try {
            ArrayList<NganHang> nganHangs = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query("ngan_hang", null, null, new String[]{}, null, null, null);
            while(cursor.moveToNext()){
                nganHangs.add(new NganHang(cursor.getInt(0),cursor.getString(1)));
            }
            return nganHangs;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public boolean themNganHang(NganHang nganHang){
        try{
            SQLiteDatabase db =this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("ten",nganHang.getTen());
            long result = db.insert("ngan_hang", null, values);
            db.close();
            return result > 0 ? true : false;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
