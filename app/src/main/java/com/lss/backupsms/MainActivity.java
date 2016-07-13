package com.lss.backupsms;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void click(View view){
        Uri uri = Uri.parse("content://sms/");
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{"address", "date", "type", "body"}, null, null, null);
        List<SmsInfo> smsInfos=new ArrayList<>();
        while (cursor.moveToNext()){
            String address = cursor.getString(0);
            long data = cursor.getLong(1);
            int type = cursor.getInt(2);
            String body = cursor.getString(3);
            SmsInfo smsInfo = new SmsInfo(data, type, body, address);
            smsInfos.add(smsInfo);
        }
        cursor.close();
        SmsUtils.backUpSms(smsInfos,this);
    }
}
