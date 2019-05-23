package com.example.qltc.model;

public class HanMucChiAD {

    private int hinh;
    private String name;
    private String time;
    private String tien;
    private String conLai;
    private long boChi;
    private String ngayBD;
    private String ngayKT;
    private String hangMuc;
    private int id;

    public HanMucChiAD(int hinh, String name, String time, String tien, String conLai, long boChi, String ngayBD, String ngayKT, String hangMuc, int id) {
        this.hinh = hinh;
        this.name = name;
        this.time = time;
        this.tien = tien;
        this.conLai = conLai;
        this.boChi = boChi;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.hangMuc = hangMuc;
        this.id = id;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTien() {
        return tien;
    }

    public void setTien(String tien) {
        this.tien = tien;
    }

    public String getConLai() {
        return conLai;
    }

    public void setConLai(String conLai) {
        this.conLai = conLai;
    }

    public long getBoChi() {
        return boChi;
    }

    public void setBoChi(long boChi) {
        this.boChi = boChi;
    }

    public String getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(String ngayBD) {
        this.ngayBD = ngayBD;
    }

    public String getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(String ngayKT) {
        this.ngayKT = ngayKT;
    }

    public String getHangMuc() {
        return hangMuc;
    }

    public void setHangMuc(String hangMuc) {
        this.hangMuc = hangMuc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
