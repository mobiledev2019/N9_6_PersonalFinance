package com.example.qltc.model;

public class ChiTien {
    private long tien;
    private String hangMuc;
    private String dienGiai;
    private String ngay;
    private String gio;
    private String chuyenDi;
    private String chiChoAi;
    private String diaDiem;
    private int anh;
    private String thang;
    private String nam;

    public long getTien() {
        return tien;
    }

    public void setTien(long tien) {
        this.tien = tien;
    }

    public String getHangMuc() {
        return hangMuc;
    }

    public void setHangMuc(String hangMuc) {
        this.hangMuc = hangMuc;
    }

    public String getDienGiai() {
        return dienGiai;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getGio() {
        return gio;
    }

    public void setGio(String gio) {
        this.gio = gio;
    }

    public String getChuyenDi() {
        return chuyenDi;
    }

    public void setChuyenDi(String chuyenDi) {
        this.chuyenDi = chuyenDi;
    }

    public String getChiChoAi() {
        return chiChoAi;
    }

    public void setChiChoAi(String chiChoAi) {
        this.chiChoAi = chiChoAi;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public int getAnh() {
        return anh;
    }

    public void setAnh(int anh) {
        this.anh = anh;
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public ChiTien(long tien, String hangMuc, String dienGiai, String ngay, String gio, String chuyenDi, String chiChoAi, String diaDiem, int anh, String thang, String nam) {
        this.tien = tien;
        this.hangMuc = hangMuc;
        this.dienGiai = dienGiai;
        this.ngay = ngay;
        this.gio = gio;
        this.chuyenDi = chuyenDi;
        this.chiChoAi = chiChoAi;
        this.diaDiem = diaDiem;
        this.anh = anh;
        this.thang = thang;
        this.nam = nam;
    }

    @Override
    public String toString() {
        return "ChiTien{" +
                "tien=" + tien +
                ", hangMuc='" + hangMuc + '\'' +
                ", dienGiai='" + dienGiai + '\'' +
                ", ngay='" + ngay + '\'' +
                ", gio='" + gio + '\'' +
                ", chuyenDi='" + chuyenDi + '\'' +
                ", chiChoAi='" + chiChoAi + '\'' +
                ", diaDiem='" + diaDiem + '\'' +
                ", anh=" + anh +
                ", thang='" + thang + '\'' +
                ", nam='" + nam + '\'' +
                '}';
    }
}
