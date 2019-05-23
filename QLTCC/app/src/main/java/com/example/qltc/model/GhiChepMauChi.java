package com.example.qltc.model;

public class GhiChepMauChi {
    private int id;
    private String ten;
    private long tien;
    private String hangMuc;
    private String dienGiai;
    private String ngay;
    private String chuyenDi;
    private String chiChoAi;
    private String diaDiem;
    private String gio;
    private String thang;
    private String nam;
    private int anh;

    public GhiChepMauChi(int id, String ten, long tien, String hangMuc, String dienGiai, String ngay, String chuyenDi, String chiChoAi, String diaDiem, String gio, String thang, String nam, int anh) {
        this.id = id;
        this.ten = ten;
        this.tien = tien;
        this.hangMuc = hangMuc;
        this.dienGiai = dienGiai;
        this.ngay = ngay;
        this.chuyenDi = chuyenDi;
        this.chiChoAi = chiChoAi;
        this.diaDiem = diaDiem;
        this.gio = gio;
        this.thang = thang;
        this.nam = nam;
        this.anh = anh;
    }

    public GhiChepMauChi(String ten, long tien, String hangMuc, String dienGiai, String ngay, String chuyenDi, String chiChoAi, String diaDiem, String gio, String thang, String nam, int anh) {
        this.ten = ten;
        this.tien = tien;
        this.hangMuc = hangMuc;
        this.dienGiai = dienGiai;
        this.ngay = ngay;
        this.chuyenDi = chuyenDi;
        this.chiChoAi = chiChoAi;
        this.diaDiem = diaDiem;
        this.gio = gio;
        this.thang = thang;
        this.nam = nam;
        this.anh = anh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

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

    public String getGio() {
        return gio;
    }

    public void setGio(String gio) {
        this.gio = gio;
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

    @Override
    public String toString() {
        return "GhiChepMauChi{" +
                "id=" + id +
                ", ten='" + ten + '\'' +
                ", tien=" + tien +
                ", hangMuc='" + hangMuc + '\'' +
                ", dienGiai='" + dienGiai + '\'' +
                ", ngay='" + ngay + '\'' +
                ", chuyenDi='" + chuyenDi + '\'' +
                ", chiChoAi='" + chiChoAi + '\'' +
                ", diaDiem='" + diaDiem + '\'' +
                ", gio='" + gio + '\'' +
                ", thang='" + thang + '\'' +
                ", nam='" + nam + '\'' +
                ", anh=" + anh +
                '}';
    }
}
