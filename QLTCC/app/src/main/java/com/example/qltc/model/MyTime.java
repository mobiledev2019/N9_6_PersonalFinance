package com.example.qltc.model;

public class MyTime {
    private int fd;
    private int fm;
    private int fh;
    private long timeMilis;
    private String ngayThangNam;
    private String timeFull;
    private String myTime;
    private String myCurrentTime;
    private String ddMM;

    public String getDdMM() {
        return ddMM;
    }

    public void setDdMM(String ddMM) {
        this.ddMM = ddMM;
    }

    public String getMyTime() {
        return myTime;
    }


    public void setMyTime(String myTime) {
        this.myTime = myTime;
    }

    public String getMyCurrentTime() {
        return myCurrentTime;
    }

    public void setMyCurrentTime(String myCurrentTime) {
        this.myCurrentTime = myCurrentTime;
    }

    public MyTime(int fd, int fm, int fh, long timeMilis, String ngayThangNam, String timeFull, String myTime, String myCurrentTime) {
        this.fd = fd;
        this.fm = fm;
        this.fh = fh;
        this.timeMilis = timeMilis;
        this.ngayThangNam = ngayThangNam;
        this.timeFull = timeFull;
        this.myTime = myTime;
        this.myCurrentTime = myCurrentTime;
    }

    public int getFd() {
        return fd;
    }

    public void setFd(int fd) {
        this.fd = fd;
    }

    public int getFm() {
        return fm;
    }

    public void setFm(int fm) {
        this.fm = fm;
    }

    public int getFh() {
        return fh;
    }

    public void setFh(int fh) {
        this.fh = fh;
    }

    public long getTimeMilis() {
        return timeMilis;
    }

    public void setTimeMilis(long timeMilis) {
        this.timeMilis = timeMilis;
    }

    public String getNgayThangNam() {
        return ngayThangNam;
    }

    public void setNgayThangNam(String ngayThangNam) {
        this.ngayThangNam = ngayThangNam;
    }

    public String getTimeFull() {
        return timeFull;
    }

    public void setTimeFull(String timeFull) {
        this.timeFull = timeFull;
    }

    public MyTime() {
    }
}
