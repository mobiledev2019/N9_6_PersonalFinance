package com.example.qltc.model;

public class LichSu {
    private int image;
    private String hangMuc;
    private String ngay;
    private String thang;
    private String nam;
    private Long tien;
    private String id;

    public LichSu(int image, String hangMuc, String ngay, String thang, String nam, Long tien, String id) {
        this.image = image;
        this.hangMuc = hangMuc;
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
        this.tien = tien;
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

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
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

    public Long getTien() {
        return tien;
    }

    public void setTien(Long tien) {
        this.tien = tien;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LichSu{" +
                "image=" + image +
                ", hangMuc='" + hangMuc + '\'' +
                ", ngay='" + ngay + '\'' +
                ", thang='" + thang + '\'' +
                ", nam='" + nam + '\'' +
                ", tien=" + tien +
                ", id='" + id + '\'' +
                '}';
    }
}
