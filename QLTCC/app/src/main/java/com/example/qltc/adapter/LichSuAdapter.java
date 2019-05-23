package com.example.qltc.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qltc.R;
import com.example.qltc.model.ChiTien;
import com.example.qltc.model.ConfigImage;
import com.example.qltc.model.LichSu;

import java.util.ArrayList;

public class LichSuAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<LichSu> lichSus;

    public LichSuAdapter(Context context, int layout, ArrayList<LichSu> lichSus) {
        this.context = context;
        this.layout = layout;
        this.lichSus = lichSus;
    }

    @Override
    public int getCount() {
        return lichSus.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder {
        ImageView imgChiTieu;
        TextView txtTien;
        TextView txtNgay;
        TextView txtTitle;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LichSuAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new LichSuAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder.imgChiTieu = convertView.findViewById(R.id.anh1);
            viewHolder.txtTien = convertView.findViewById(R.id.tien1);
            viewHolder.txtNgay = convertView.findViewById(R.id.thoigian1);
            viewHolder.txtTitle = convertView.findViewById(R.id.ten1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (LichSuAdapter.ViewHolder) convertView.getTag();
        }
        LichSu chiTien = lichSus.get(position);
        int image = new ConfigImage().getImage(chiTien.getHangMuc());
        viewHolder.imgChiTieu.setImageResource(image);
        if(chiTien.getId().split(" ")[1].compareTo("chi")==0){
            viewHolder.txtTien.setTextColor(Color.parseColor("#E41111"));
        }else{
            viewHolder.txtTien.setTextColor(Color.parseColor("#24ABE6"));
        }
        viewHolder.txtTien.setText(String.valueOf(chiTien.getTien()));
        String ngay = chiTien.getNgay()+"/"+chiTien.getThang()+"/"+chiTien.getNam();
        viewHolder.txtNgay.setText(ngay);
        viewHolder.txtTitle.setText(chiTien.getHangMuc());
        return convertView;
    }
}
