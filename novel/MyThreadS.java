package com.example.a10515.novel;

/**
 * Created by 10515 on 2017/3/3.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class MyThreadS extends Thread{
    String url=null;
    ListView ls=null;
    Handler handler=null;
    ArrayList<HashMap<String,String> > ans=null;
    private final String source="http://www.biquge.tw";
    Context context=null;
    MyThreadS(String url,ListView ls,Handler handler,Context context){
        this.url=url;
        this.ls=ls;
        this.context=context;
        this.handler=handler;
    }
    public void run(){
        try {
            ans=new Novel().getList(this.url);
        } catch (IOException e) {

            e.printStackTrace();
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                ls.setAdapter(new SimpleAdapter(context,ans,R.layout.spageitem,new String[]{"title"},new int[]{R.id.textView}));
            }
        });
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(context,Tpage.class);
                intent.setClass(context,Tpage.class);
                Bundle fBundle=new Bundle();
                fBundle.putString("url",source+ans.get(position).get("href"));
                fBundle.putString("addr",url);
                intent.putExtras(fBundle);
                context.startActivity(intent);

            }

        });
    }
}
