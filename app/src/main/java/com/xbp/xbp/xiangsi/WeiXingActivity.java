package com.xbp.xbp.xiangsi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xbp.xbp.xiangsi.bean.WeiXing_Entity;
import com.xbp.xbp.xiangsi.utis.HttpQuest;
import com.xbp.xbp.xiangsi.utis.Urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeiXingActivity extends AppCompatActivity {

    private List<WeiXing_Entity> datas;
    private MeiAdapter adapter;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_xing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundResource(R.color.colorAccent);
        setSupportActionBar(toolbar);
        listView= (ListView) findViewById(R.id.meiwen_listview);
        datas=new ArrayList<WeiXing_Entity>();
        adapter=new MeiAdapter(WeiXingActivity.this,datas);



        HttpQuest.loadData(WeiXingActivity.this, Urls.WeiXingMeiWei, new HttpQuest.Callback() {
            @Override
            public void response(String result) {
                try {
                    JSONArray array=new JSONObject(result).getJSONObject("result").getJSONArray("list");
                    WeiXing_Entity data=null;
                    for(int i=0;i<array.length();i++){
                        data=WeiXing_Entity.objectFromData(array.getJSONObject(i).toString());
                        datas.add(data);
                        Log.e("TAG",data.getTitle());
                    }

                    adapter.addAll(datas);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(WeiXingActivity.this, WebViewActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("url", datas.get(position).getUrl());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
