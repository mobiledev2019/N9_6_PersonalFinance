package com.example.qltc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qltc.R;
import com.example.qltc.model.HangMucChiTien;

import java.util.ArrayList;

public class HangMucChiTienAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<HangMucChiTien> listHangMucChiTieu;

    public HangMucChiTienAdapter(Context context, int layout, ArrayList<HangMucChiTien> listHangMucChiTieu) {
        this.context = context;
        this.layout = layout;
        this.listHangMucChiTieu = listHangMucChiTieu;
    }

    @Override
    public int getCount() {
        return listHangMucChiTieu.size();
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
        TextView txtChiTieu;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            viewHolder.imgChiTieu = convertView.findViewById(R.id.imgChiTieu);

            viewHolder.txtChiTieu = convertView.findViewById(R.id.txtChiTieu);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        HangMucChiTien hangMucChiTien = listHangMucChiTieu.get(position);
        viewHolder.txtChiTieu.setText(hangMucChiTien.getTen());
        viewHolder.imgChiTieu.setImageResource(hangMucChiTien.getHinh());
        return convertView;
    }
}
