package com.example.qltc.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    SimpleDateFormat FD = new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat FM = new SimpleDateFormat("yyyyMM");
    SimpleDateFormat FH = new SimpleDateFormat("yyyyMMddHH");
    SimpleDateFormat FULL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat MyTime = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat MyCurrentTime = new SimpleDateFormat("HH:mm");
    SimpleDateFormat ddMM = new SimpleDateFormat("dd/MM");



    public MyTime getTime() {
        MyTime hitexTime = new MyTime();
        Date date = new Date();
        hitexTime.setFd(Integer.parseInt(FD.format(date)));
        hitexTime.setFm(Integer.parseInt(FM.format(date)));
        hitexTime.setFh(Integer.parseInt(FH.format(date)));
        hitexTime.setTimeFull(FULL.format(date));
        hitexTime.setTimeMilis(date.getTime());
        hitexTime.setMyTime(MyTime.format(date));
        hitexTime.setMyCurrentTime(MyCurrentTime.format(date));
        hitexTime.setDdMM(ddMM.format(date));

        return hitexTime;
    }
    public String getDayName(String name){
        switch(name){
            case "MONDAY" : return "Thứ 2";
            case "TUESDAY": return "Thứ 3";
            case "WEDNESDAY": return "Thứ 4";
            case "THURSDAY": return "Thứ 5";
            case "FRIDAY": return "Thứ 6";
            case "SATURDAY": return "Thứ 7";
            case "SUNDAY": return "Chủ nhật";
            default: return "";
        }
    }


    public TimeUtils() {
    }
}