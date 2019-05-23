package com.example.qltc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qltc.R;
import com.example.qltc.model.ChiTien;

import java.util.ArrayList;

public class ChiTietHMCAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<ChiTien> chiTiens;

    public ChiTietHMCAdapter(Context context, int layout, ArrayList<ChiTien> chiTiens) {
        this.context = context;
        this.layout = layout;
        this.chiTiens = chiTiens;
    }

    @Override
    public int getCount() {
        return chiTiens.size();
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
        ChiTietHMCAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ChiTietHMCAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder.imgChiTieu = convertView.findViewById(R.id.imgChiTieu);
            viewHolder.txtTien = convertView.findViewById(R.id.tien);
            viewHolder.txtNgay = convertView.findViewById(R.id.ngay);
            viewHolder.txtTitle = convertView.findViewById(R.id.txtChiTieu);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChiTietHMCAdapter.ViewHolder) convertView.getTag();
        }
        ChiTien chiTien = chiTiens.get(position);
//        viewHolder.imgChiTieu.setImageResource(chiTien.getAnh());
        viewHolder.txtTien.setText(String.valueOf(chiTien.getTien()));
        String ngay = chiTien.getNgay()+"/"+chiTien.getThang()+"/"+chiTien.getNam();
        viewHolder.txtNgay.setText(ngay);
        viewHolder.txtTitle.setText(chiTien.getHangMuc());
        return convertView;
    }
}
