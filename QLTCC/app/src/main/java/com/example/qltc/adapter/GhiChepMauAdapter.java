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
import com.example.qltc.model.GhiChepMauEx;


import java.util.ArrayList;

public class GhiChepMauAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<GhiChepMauEx> ghiChepMauExes;

    public GhiChepMauAdapter(Context context, int layout, ArrayList<GhiChepMauEx> ghiChepMauExes) {
        this.context = context;
        this.layout = layout;
        this.ghiChepMauExes = ghiChepMauExes;

    }

    @Override
    public int getCount() {
        return ghiChepMauExes.size();
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
        TextView txtTien;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GhiChepMauAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new GhiChepMauAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder.imgChiTieu = convertView.findViewById(R.id.imgChiTieu);
            viewHolder.txtChiTieu = convertView.findViewById(R.id.txtChiTieu);
            viewHolder.txtTien = convertView.findViewById(R.id.tienChiTieu);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GhiChepMauAdapter.ViewHolder) convertView.getTag();
        }

        GhiChepMauEx ghiChepMauEx = ghiChepMauExes.get(position);
        viewHolder.txtChiTieu.setText(ghiChepMauEx.getTen());
        viewHolder.imgChiTieu.setImageResource(ghiChepMauEx.getImage());
        viewHolder.txtTien.setText(String.valueOf(ghiChepMauEx.getTien()));
        if(ghiChepMauEx.getId().split(" ")[1].compareTo("chi")==0){
            viewHolder.txtTien.setTextColor(Color.parseColor("#E41111"));
        }else{
            viewHolder.txtTien.setTextColor(Color.parseColor("#24ABE6"));
        }

        return convertView;
    }
}
