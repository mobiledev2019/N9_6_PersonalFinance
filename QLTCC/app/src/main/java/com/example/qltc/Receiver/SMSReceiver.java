package com.example.qltc.Receiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.example.qltc.MainActivity;
import com.example.qltc.R;
import com.example.qltc.database.DatabaseHandler;
import com.example.qltc.fragment.HanMucChiFragment;
import com.example.qltc.model.ChiTien;
import com.example.qltc.model.NganHang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.media.RingtoneManager.getDefaultUri;

public class SMSReceiver extends BroadcastReceiver {

    public static Map<String, String> mapMessage;
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String TAG = "SmsBroadcastReceiver";
    String msg, phoneNo = "";

    @Override
    public void onReceive(Context context, Intent intent) {
        mapMessage = new HashMap<>();
        if (intent.getAction() == SMS_RECEIVED) {
            Bundle dataBundle = intent.getExtras();
            if (dataBundle != null) {
                Object[] mypdu = (Object[]) dataBundle.get("pdus");
                final SmsMessage[] messages = new SmsMessage[mypdu.length];
                for (int i = 0; i < mypdu.length; i++) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        String format = dataBundle.getString("format");
                        messages[i] = SmsMessage.createFromPdu((byte[]) mypdu[i], format);

                    } else {
                        messages[i] = SmsMessage.createFromPdu((byte[]) mypdu[i]);
                    }
                    msg += messages[i].getMessageBody();
                    phoneNo = messages[i].getOriginatingAddress();
                }
                mapMessage.put(phoneNo, msg);
            }
        }
        String tien = "";
        String loai = "";
        String nganHang = "";
        long tienLong = 0;
        if (mapMessage.size() > 0) {
            DatabaseHandler db = new DatabaseHandler(context);
            ArrayList<NganHang> nganHangs = db.tatCaNganHang();
            if (nganHangs.size() > 0) {
                for (NganHang e : nganHangs) {
                    if (mapMessage.containsKey(e.getTen())) {
                        String content = mapMessage.get(e.getTen());
                        nganHang = e.getTen();
                        if (content.contains("-")) {
                            loai = "ChiTien";
                            String first = content.split("VND")[0];
                            tien = first.split("-")[1];
                        } else if (content.contains("+")) {
                            loai = "ThuTien";
                            String first = content.split("VND")[0];
                            tien = first.split("\\+")[1];
                        }
                        String tienSpl[] = tien.split(",");
                        String result = "";
                        for (int i = 0; i < tienSpl.length; i++) {
                            result += tienSpl[i];
                        }
                        System.out.println("Tien now " + result);
                        tienLong = Long.parseLong(result);
//                        System.out.println("Loai: " + loai + "Ngan hang: " + nganHang + "tien: " + tienLong);

                        int NOTIFICATION_ID = 234;
                        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                        String CHANNEL_ID = "Notification from QTTC";
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            CharSequence name = "NOTE";
                            String Description = "This is my channel";
                            int importance = NotificationManager.IMPORTANCE_HIGH;
                            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                            mChannel.setDescription(Description);
                            mChannel.enableLights(true);
                            mChannel.setLightColor(Color.RED);
                            mChannel.enableVibration(true);
                            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                            mChannel.setShowBadge(false);
                            notificationManager.createNotificationChannel(mChannel);
                        }
                        Intent intent1 = new Intent(context, MainActivity.class);
                        intent1.putExtra("Loai", loai);
                        intent1.putExtra("NganHang", nganHang);
                        intent1.putExtra("Tien", tienLong);
//            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
                        String notificationType = "";
                        if (loai.compareTo("ChiTien") == 0) {
                            notificationType = "Chi tien";
                        } else if (loai.compareTo("ThuTien") == 0) {
                            notificationType = "Thu tien";
                        }

                        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Khuyến nghị " + notificationType + " từ ngân hàng")
                                .setContentText("Khoản "+notificationType+" từ ngân hàng " + nganHang + ": " + tien + ". Bạn có muốn ghi chép không?")
                                .setContentIntent(pendingIntent);
                        notificationManager.notify(NOTIFICATION_ID, builder.build());

                    } else {
                        //do nothing
                    }
                }
                ;
            } else {
                //do nothing
            }
        }


    }

}
