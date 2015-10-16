package com.xbp.xbp.xiangsi.biz;

import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.telephony.SmsManager;

import com.xbp.xbp.xiangsi.bean.SendedMsg;
import com.xbp.xbp.xiangsi.db.SmsProvider;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

/**
 * Created by Administrator on 2015/10/14.
 */
public class SmsBiz {

    private Context context;
    public SmsBiz(Context context){
        this.context=context;
    }
    /**
     * 发送一条短信
     * @param number
     * @param msg
     * @param sendPi
     * @param deliverPi
     * @return
     */
    public int sendMsg(String number,String msg,PendingIntent sendPi,PendingIntent deliverPi){
        /**
         * 建立短信管理器
         */
        SmsManager smsManager=SmsManager.getDefault();
        /**
         * 分解短信
         */
        ArrayList<String>contents=smsManager.divideMessage(msg);
        for(String content:contents){
            smsManager.sendTextMessage(number,null,content,sendPi,deliverPi);

        }
        return  contents.size();

    }

    /**
     * 群发短信
     * @param numbers
     * @param msg
     * @param sendPi
     * @param deliverPi
     * @return
     */
    public int sendMsg(Set<String> numbers,SendedMsg msg,PendingIntent sendPi,PendingIntent deliverPi){
        save(msg);
        int result=0;
        for (String number:numbers){
            int count=sendMsg(number,msg.getMsg(),sendPi,deliverPi);
            result+=count;
        }
        return result;
    }

  private void save(SendedMsg sendedMsg){
        sendedMsg.setDate(new Date());
        ContentValues values=new ContentValues();
        values.put(SendedMsg.COLUMN_DATE,sendedMsg.getDate().getTime());
        values.put(SendedMsg.COLUMN_FES_NAME,sendedMsg.getFestivalName());
        values.put(SendedMsg.COLUMN_MSG, sendedMsg.getMsg());
        values.put(SendedMsg.COLUMN_NAMES,sendedMsg.getName());
        values.put(SendedMsg.COLUMN_NUMBERS,sendedMsg.getNumbers());

        context.getContentResolver().insert(SmsProvider.URI_SMS_ALL,values);

    }
}
