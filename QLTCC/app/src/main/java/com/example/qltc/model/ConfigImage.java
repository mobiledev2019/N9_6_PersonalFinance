package com.example.qltc.model;

import com.example.qltc.R;

public class ConfigImage {
    public int getImage(String name){
        switch (name){
            case "Ăn uống" : return R.drawable.an_uong;
            case "Dịch vụ sinh hoạt": return R.drawable.dich_vu_sinh_hoat;
            case "Đi lại": return R.drawable.di_lai;
            case "Trang phục": return R.drawable.trang_phuc;
            case "Con cái": return R.drawable.con_cai;
            case "Hưởng thụ": return R.drawable.huong_thu;
            case "Hiếu hỉ": return R.drawable.hieu_hi;
            case "Nhà cửa": return R.drawable.nha_cua;
            case "Phát triển bản thân": return R.drawable.phat_trien_ban_than;
            case "Sức khỏe": return R.drawable.suc_khoe;
            case "Lương": return R.drawable.luong;
            case "Lãi tiết kiệm": return R.drawable.tiet_kiem;
            case "Thưởng":return R.drawable.thuong;
            case "Được cho/tặng": return R.drawable.cho_tang;
            case "Tiền lãi": return  R.drawable.lai;
            case "Khác": return R.drawable.khac;
            case "NganHangChi": return R.drawable.tax_48px;
            case "NhanHangThu": return R.drawable.refund_48px;
            case "Thu từ ngân hàng": return R.drawable.tax_48px;
            case "Chi từ ngân hàng": return R.drawable.refund_48px;
            default: return 0;
        }
    }
}
