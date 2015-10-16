package com.xbp.xbp.xiangsi;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.xbp.xbp.xiangsi.bean.FestivalLab;
import com.xbp.xbp.xiangsi.bean.Msg;
import com.xbp.xbp.xiangsi.fragment.FestivalCategoryFragment;

public class ChoseMsgActivity extends AppCompatActivity {

    private ListView mLvMsgs;
    private FloatingActionButton mFabToSend;
    private int mFestivalId;
    private LayoutInflater mInflater;

    private ArrayAdapter<Msg>mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_msg);
        mFestivalId=getIntent().getIntExtra(FestivalCategoryFragment.ID_FESTIVAL, -1);
      setTitle(FestivalLab.getInstance().getFestivalById(mFestivalId).getName());
        mInflater=LayoutInflater.from(this);

        initViews();
        initEvent();
    }

    private void initEvent() {
        mFabToSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SendMsgActivity.toActivity(ChoseMsgActivity.this,mFestivalId,-1);

            }
        });

    }

    private void initViews() {
        mLvMsgs= (ListView) findViewById(R.id.id_lv_msgs);
        mFabToSend= (FloatingActionButton) findViewById(R.id.id_fab_toSend);

        mLvMsgs.setAdapter(mAdapter=new ArrayAdapter<Msg>(this,-1, FestivalLab.getInstance().getMsgsByFestivalId(mFestivalId)){
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                if(convertView==null){
                    convertView=mInflater.inflate(R.layout.item_msg,parent,false);
                }

                TextView content = (TextView) convertView.findViewById(R.id.id_tv_content);
                Button toSend= (Button) convertView.findViewById(R.id.id_btn_toSend);

                content.setText("   "+getItem(position).getContent());
                toSend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SendMsgActivity.toActivity(ChoseMsgActivity.this,mFestivalId,getItem(position).getId());
                    }
                });
                return convertView;
            }
        });

    }
}
