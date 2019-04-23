package com.example.qltc.model;

public class ThuTien {

    private long tien;
    private String hangMuc;
    private String dienGiai;
    private String ngay;
    private String gio;
    private String chuyenDi;
    private String thuTuAi;
    private String diaDiem;

    private String thang;
    private String nam;
    private int anh;

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

    @Override
    public String toString() {
        return "ThuTien{" +
                "tien=" + tien +
                ", hangMuc='" + hangMuc + '\'' +
                ", dienGiai='" + dienGiai + '\'' +
                ", ngay='" + ngay + '\'' +
                ", gio='" + gio + '\'' +
                ", chuyenDi='" + chuyenDi + '\'' +
                ", thuTuAi='" + thuTuAi + '\'' +
                ", diaDiem='" + diaDiem + '\'' +
                ", thang='" + thang + '\'' +
                ", nam='" + nam + '\'' +
                ", anh=" + anh +
                '}';
    }

    public String getChuyenDi() {
        return chuyenDi;
    }

    public void setChuyenDi(String chuyenDi) {
        this.chuyenDi = chuyenDi;
    }

    public String getThuTuAi() {
        return thuTuAi;
    }

    public void setThuTuAi(String thuTuAi) {
        this.thuTuAi = thuTuAi;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
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

    public int getAnh() {
        return anh;
    }

    public void setAnh(int anh) {
        this.anh = anh;
    }

    public ThuTien(long tien, String hangMuc, String dienGiai, String ngay, String gio, String chuyenDi, String thuTuAi, String diaDiem, String thang, String nam, int anh) {
        this.tien = tien;
        this.hangMuc = hangMuc;
        this.dienGiai = dienGiai;
        this.ngay = ngay;
        this.gio = gio;
        this.chuyenDi = chuyenDi;
        this.thuTuAi = thuTuAi;
        this.diaDiem = diaDiem;
        this.thang = thang;
        this.nam = nam;
        this.anh = anh;
    }
}
