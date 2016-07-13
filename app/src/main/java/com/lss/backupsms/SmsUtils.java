package com.lss.backupsms;

import android.content.Context;
import android.os.Environment;
import android.util.Xml;
import android.widget.Toast;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by shuai on 16-7-13.
 */
public class SmsUtils {
    public static void backUpSms(List<SmsInfo> smsInfos, Context context){
        try{
            XmlSerializer serializer = Xml.newSerializer();
            File file = new File(Environment.getExternalStorageDirectory(), "sms.xml");
            FileOutputStream os = new FileOutputStream(file);
            serializer.setOutput(os,"utf-8");
            serializer.startDocument("utf-8",true);
            serializer.startTag(null,"smss");
            for (SmsInfo info:smsInfos){
                //id
                serializer.startTag(null,"sms");
                serializer.attribute(null,"id",info.getId()+"");
                //body
                serializer.startTag(null,"body");
                serializer.text(info.getBody());
                serializer.endTag(null,"body");
                //address
                serializer.startTag(null,"address");
                serializer.text(info.getAddress());
                serializer.endTag(null,"address");
                //type
                serializer.startTag(null,"type");
                serializer.text(info.getType()+"");
                serializer.endTag(null,"type");
                //date
                serializer.startTag(null,"date");
                serializer.text(info.getDate()+"");
                serializer.endTag(null,"date");
                serializer.endTag(null,"sms");
            }
            serializer.endTag(null,"smss");
            serializer.endDocument();
            os.close();
            Toast.makeText(context,"备份成功",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context,"备份失败",Toast.LENGTH_LONG).show();
        }
    }
}
