package com.example.qltc.model;

public class GhiChepGanDay {
    private int image;
    private String hangMuc;
    private String ngay;
    private Long tien;

    public GhiChepGanDay(int image, String hangMuc, String ngay, Long tien) {
        this.image = image;
        this.hangMuc = hangMuc;
        this.ngay = ngay;
        this.tien = tien;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getHangMuc() {
        return hangMuc;
    }

    public void setHangMuc(String hangMuc) {
        this.hangMuc = hangMuc;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public Long getTien() {
        return tien;
    }

    public void setTien(Long tien) {
        this.tien = tien;
    }
}
