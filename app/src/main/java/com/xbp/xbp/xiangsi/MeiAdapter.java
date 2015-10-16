package com.xbp.xbp.xiangsi;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xbp.xbp.xiangsi.bean.WeiXing_Entity;

import java.util.List;

/**
 * Created by Administrator on 2015/10/15.
 */
public class MeiAdapter extends BaseAdapter {

    private Context context;
    private List<WeiXing_Entity> datas;

    public MeiAdapter(Context context, List<WeiXing_Entity> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_meiwen,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(datas.get(position).getTitle());
        viewHolder.suorce.setText(datas.get(position).getSource());
        Uri uri= Uri.parse(datas.get(position).getFirstImg());
        viewHolder.imageview.setImageURI(uri);
        return convertView;
    }
    public   void addAll(List<WeiXing_Entity>data){
        datas.addAll(data);
        notifyDataSetChanged();
    }
    public static class ViewHolder{
        SimpleDraweeView imageview;
        TextView title;
        TextView suorce;
        public  ViewHolder(View view){
            imageview= (SimpleDraweeView) view.findViewById(R.id.mei_imageView);
            title= (TextView) view.findViewById(R.id.mei_title);
            suorce= (TextView) view.findViewById(R.id.mei_source);
        }
    }
}
