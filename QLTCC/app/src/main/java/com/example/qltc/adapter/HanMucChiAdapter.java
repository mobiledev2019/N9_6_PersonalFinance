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
import com.example.qltc.model.HanMucChi;
import com.example.qltc.model.HanMucChiAD;


import java.util.ArrayList;

public class HanMucChiAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<HanMucChiAD> listHanMucChi;

    public HanMucChiAdapter(Context context, int layout, ArrayList<HanMucChiAD> listHanMucChi) {
        this.context = context;
        this.layout = layout;
        this.listHanMucChi = listHanMucChi;
    }


    @Override
    public int getCount() {
        return listHanMucChi.size();
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
        ImageView hmcImage;
        TextView hmcName;
        TextView hmcTime;
        TextView hmcTien;
        TextView hmcConLai;
        TextView hmcBoiChi;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HanMucChiAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new HanMucChiAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            viewHolder.hmcImage = convertView.findViewById(R.id.hcmImage);
            viewHolder.hmcName = convertView.findViewById(R.id.hmcTen);
            viewHolder.hmcTime = convertView.findViewById(R.id.hmcThoiGian);
            viewHolder.hmcTien = convertView.findViewById(R.id.hmcTien);
            viewHolder.hmcConLai = convertView.findViewById(R.id.hmcConLai);
            viewHolder.hmcBoiChi = convertView.findViewById(R.id.hmcBoiChi);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (HanMucChiAdapter.ViewHolder) convertView.getTag();
        }

        HanMucChiAD hanMucChi = listHanMucChi.get(position);
        viewHolder.hmcImage.setImageResource(hanMucChi.getHinh());
        viewHolder.hmcName.setText(hanMucChi.getName());
        viewHolder.hmcTime.setText(hanMucChi.getTime());
        viewHolder.hmcTien.setText(String.valueOf(hanMucChi.getTien()));
        viewHolder.hmcConLai.setText(hanMucChi.getConLai());
        if(hanMucChi.getBoChi() <= 0) {
            viewHolder.hmcBoiChi.setText("Bội chi: "+ String.valueOf(hanMucChi.getBoChi())+" đ");
            viewHolder.hmcBoiChi.setTextColor(Color.parseColor("#E41111"));

        }else{
            viewHolder.hmcBoiChi.setText(String.valueOf(hanMucChi.getBoChi())+" đ");
        }
        return convertView;
    }
}
