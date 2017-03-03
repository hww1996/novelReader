package com.example.a10515.novel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 10515 on 2017/3/3.
 */

public class MyAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<HashMap<String,String>> data;
    private LayoutInflater inflater;
    public MyAdapter(Context context, ArrayList<HashMap<String, String>> data){
        this.context=context;
        this.data=data;
        this.inflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=inflater.inflate(R.layout.fpageitem,null);
        TextView tv= (TextView) view.findViewById(R.id.textView);
        TextView tv1= (TextView) view.findViewById(R.id.textView1);
        //WebView wb= (WebView) view.findViewById(R.id.textView2);
        tv.setText(data.get(i).get("title"));
        tv1.setText(data.get(i).get("summary"));
        //wb.getSettings().setDefaultTextEncodingName("UTF-8");
        //wb.loadData(data.get(i).get("info"),"text/html","GBK");
        //wb.loadData(data.get(i).get("info"),"text/html; charset=UTF-8", null);
        return view;
    }
}
