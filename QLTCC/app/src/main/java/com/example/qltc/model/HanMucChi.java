package com.example.qltc.model;

public class HanMucChi {

   private long soTien;
   private String ten;
   private String hangMuc;
   private String xoayVong;
   private String ngayBatDau;
   private String ngayKetThuc;
   private long boiChi;
    private int id;

    public HanMucChi(long soTien, String ten, String hangMuc, String xoayVong, String ngayBatDau, String ngayKetThuc, long boiChi) {
        this.soTien = soTien;
        this.ten = ten;
        this.hangMuc = hangMuc;
        this.xoayVong = xoayVong;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.boiChi = boiChi;
    }

    public HanMucChi(long soTien, String ten, String hangMuc, String xoayVong, String ngayBatDau, String ngayKetThuc, long boiChi, int id) {
        this.soTien = soTien;
        this.ten = ten;
        this.hangMuc = hangMuc;
        this.xoayVong = xoayVong;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.boiChi = boiChi;
        this.id = id;
    }

    @Override
    public String toString() {
        return "HanMucChi{" +
                "soTien=" + soTien +
                ", ten='" + ten + '\'' +
                ", hangMuc='" + hangMuc + '\'' +
                ", xoayVong='" + xoayVong + '\'' +
                ", ngayBatDau='" + ngayBatDau + '\'' +
                ", ngayKetThuc='" + ngayKetThuc + '\'' +
                ", boiChi=" + boiChi +
                ", id=" + id +
                '}';
    }

    public long getSoTien() {
        return soTien;
    }

    public void setSoTien(long soTien) {
        this.soTien = soTien;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHangMuc() {
        return hangMuc;
    }

    public void setHangMuc(String hangMuc) {
        this.hangMuc = hangMuc;
    }

    public String getXoayVong() {
        return xoayVong;
    }

    public void setXoayVong(String xoayVong) {
        this.xoayVong = xoayVong;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public long getBoiChi() {
        return boiChi;
    }

    public void setBoiChi(long boiChi) {
        this.boiChi = boiChi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
