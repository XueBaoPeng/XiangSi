package com.xbp.xbp.xiangsi;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xbp.xbp.xiangsi.bean.Festival;
import com.xbp.xbp.xiangsi.bean.FestivalLab;
import com.xbp.xbp.xiangsi.bean.Msg;
import com.xbp.xbp.xiangsi.bean.SendedMsg;
import com.xbp.xbp.xiangsi.biz.SmsBiz;
import com.xbp.xbp.xiangsi.view.FlowLayout;

import java.util.HashSet;

public class SendMsgActivity extends AppCompatActivity {

    public static final String KEY_FESTIVAL_ID="festivalId" ;
    public static final String KEY_MSG_ID="msgId";
    private static final int CODE_REQUEST=1;
    private int mFestivalId;
    private int msgId;

    private Festival mFestival;
    private Msg mMsg;


    private EditText mEdMsg;
    private Button mBtnAdd;
    private FlowLayout mFlcontacts;
    private FloatingActionButton mFabSend;
    private View mLaoutLoading;

    private HashSet<String>mContactNames=new HashSet<>();
    private HashSet<String>mContactNums=new HashSet<>();
    private LayoutInflater mInflater;

    public static final String ACTION_SEND_MSG="ACTION_SEND_MSG";
    public static final String ACTION_DELIVER_MSG="ACTION_DELIVER_MSG";
    private PendingIntent mSendPi;
    private PendingIntent mDeliverPi;

    //接收广播
    private BroadcastReceiver mSendBoroadcastReceiver;
    private BroadcastReceiver mDeliverBoroadcastReceiver;

    //发送业务层
    private SmsBiz mSmsBiz;


    private  int mMsgSendCount;
    //短发发送成功数
    private int mTotalCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_msg);
        mInflater=LayoutInflater.from(this);
        mSmsBiz=new SmsBiz(this);
        initDatas();
        initViews();
        initEvents();
        initReceivers();
    }

   private void initReceivers() {
        Intent sendIntent=new Intent(ACTION_SEND_MSG);
        mSendPi=PendingIntent.getBroadcast(this,0,sendIntent,0);
        Intent deliverIntent=new Intent(ACTION_DELIVER_MSG);
        mDeliverPi=PendingIntent.getBroadcast(this,0,deliverIntent,0);
        registerReceiver(mSendBoroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                mMsgSendCount++;
                if (getResultCode() == RESULT_OK) {
                    Log.e("TAG", "短信发送成功" + mMsgSendCount + "/" + mTotalCount);
                } else {
                    Log.e("TAG", "短信发送失败");
                }

                if (mMsgSendCount == mTotalCount) {
                    finish();
                }
                Toast.makeText(SendMsgActivity.this, mMsgSendCount + "/" + mTotalCount + "短信发送成功", Toast.LENGTH_SHORT).show();
            }
        }, new IntentFilter(ACTION_SEND_MSG));

        registerReceiver(mDeliverBoroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.e("TAG", "联系人已经成功接收到我们的短信");
            }
        }, new IntentFilter(ACTION_DELIVER_MSG));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mSendBoroadcastReceiver);
        unregisterReceiver(mDeliverBoroadcastReceiver);
    }

    /**
     * 添加联系人
     */
    private void initEvents() {
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, CODE_REQUEST);
            }
        });
        /**
         * 发送短信
         */
        mFabSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContactNums.size() == 0) {
                    Toast.makeText(SendMsgActivity.this, "请先选择联系人", Toast.LENGTH_SHORT).show();
                    return;
                }
                String msg = mEdMsg.getText().toString();
                if (TextUtils.isEmpty(msg)) {
                    Toast.makeText(SendMsgActivity.this, "" +
                            "短信内容不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                mLaoutLoading.setVisibility(View.VISIBLE);
              mTotalCount= mSmsBiz.sendMsg(mContactNums,buildSendMsg( msg), mSendPi, mDeliverPi);
                mMsgSendCount = 0;
            }
        });
    }

    private SendedMsg buildSendMsg(String msg) {

        SendedMsg sendedMsg=new SendedMsg();
        sendedMsg.setMsg(msg);
        sendedMsg.setFestivalName(mFestival.getName());
        String names="";
        for(String name:mContactNames){
            names += name +":";
        }
        String numbers="";
        for(String number:mContactNums){
            numbers += number +":";
        }
        sendedMsg.setName(names.substring(0, names.length() - 1));

        sendedMsg.setNumbers(numbers.substring(0, numbers.length() - 1));
        return sendedMsg;
    }

    private void initViews() {
        mEdMsg= (EditText) findViewById(R.id.id_et_content);
        mBtnAdd= (Button) findViewById(R.id.id_btn_add);
        mFlcontacts= (FlowLayout) findViewById(R.id.id_fl_contacts);
        mFabSend= (FloatingActionButton) findViewById(R.id.id_fab_send);
        mLaoutLoading=findViewById(R.id.id_layout_loading);
        mLaoutLoading.setVisibility(View.GONE);
        if(msgId!=-1){
            mMsg=FestivalLab.getInstance().getMsgByMsgId(msgId);
            mEdMsg.setText(mMsg.getContent());
        }
    }

    private void initDatas() {
        mFestivalId=getIntent().getIntExtra(KEY_FESTIVAL_ID,-1);
        msgId=getIntent().getIntExtra(KEY_MSG_ID,-1);
        mFestival=FestivalLab.getInstance().getFestivalById(mFestivalId);

        setTitle(mFestival.getName());

    }

    /**
     * 联系人添加返回内容的方法
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==CODE_REQUEST){
            if(resultCode==RESULT_OK){
                //获取联系人的名称
                Uri contactUri=data.getData();
                Cursor cursor=getContentResolver().query(contactUri, null, null, null, null);
                cursor.moveToFirst();
                String contactName=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));


                //获取联系人的电话号码
                String number=getContactNumber(cursor);
                if(!TextUtils.isEmpty(number)){
                    mContactNames.add(contactName);
                    mContactNums.add(number);
                }

                addTag(contactName);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 显示标签名
     * @param contactName
     */
    private void addTag(String contactName) {
        TextView view= (TextView) mInflater.inflate(R.layout.tag, mFlcontacts, false);
        view.setText(contactName);
        mFlcontacts.addView(view);
    }

    private String getContactNumber(Cursor cursor) {

       int numberCount= cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
        String number=null;
        if(numberCount>0){
           int contactId= cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phoneCursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" +contactId,null,null);
            phoneCursor.moveToFirst();
            number= phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            phoneCursor.close();
        }
        cursor.close();
        return  number;
    }

    public static void toActivity(Context context,int festivalId,int msgId){
        Intent intent=new Intent(context,SendMsgActivity.class);
        intent.putExtra(KEY_FESTIVAL_ID,festivalId);
        intent.putExtra(KEY_MSG_ID,msgId);
        context.startActivity(intent);
    }


}
