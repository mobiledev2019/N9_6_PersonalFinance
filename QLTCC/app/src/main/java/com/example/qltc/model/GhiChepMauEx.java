package com.example.qltc.model;

public class GhiChepMauEx {
    private String id;
    private int image;
    private String hangMuc;
    private String ten;
    private long tien;

    public GhiChepMauEx(String id, int image, String hangMuc, String ten, long tien) {
        this.id = id;
        this.image = image;
        this.hangMuc = hangMuc;
        this.ten = ten;
        this.tien = tien;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "GhiChepMauEx{" +
                "id='" + id + '\'' +
                ", image=" + image +
                ", hangMuc='" + hangMuc + '\'' +
                ", ten='" + ten + '\'' +
                ", tien='" + tien + '\'' +
                '}';
    }
}
