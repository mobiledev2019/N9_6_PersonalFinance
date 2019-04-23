package com.example.qltc.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qltc.R;
import com.example.qltc.database.DatabaseHandler;
import com.example.qltc.model.User;

public class TongQuanFragment extends Fragment {

    private TextView  textView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tong_quan, container, false);
        textView  = view.findViewById(R.id.money);

        DatabaseHandler databaseHandler = new DatabaseHandler(getContext());
        databaseHandler.themUser();
        User user  = databaseHandler.getUser();
        if(user.getBalance() > 0) {
            textView.setText(String.valueOf(user.getBalance()) + "đ");
            textView.setTextColor(Color.parseColor("#24ABE6"));
        }
        else{
            textView.setText(String.valueOf(user.getBalance()) + "đ");
            textView.setTextColor(Color.parseColor("#E41111"));
        }
        return view;
    }
}
