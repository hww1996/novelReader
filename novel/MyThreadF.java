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
import android.widget.EditText;
import android.widget.ListView;



public class MyThreadF extends Thread{
    ListView ls;
    EditText et=null;
    Handler handler=null;
    ArrayList<HashMap<String,String> > ans=null;

    Context context=null;
    MyThreadF( EditText et, Handler handler,ListView ls, Context context){
        this.ls=ls;
        this.et=et;
        this.context=context;
        this.handler=handler;
    }
    public void run(){
        try {
            ans=new Novel().Search(et.getText().toString());
        } catch (IOException e) {

            e.printStackTrace();
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                /*ls.setAdapter(new SimpleAdapter(context,ans,R.layout.fpageitem,
                        new String[]{"title","summary","info"},new int[]{R.id.textView,R.id.textView1,R.id.textView2}));*/
                ls.setAdapter(new MyAdapter(context,ans));
            }
        });
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //  Toast.makeText(context,"ok",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context,Spage.class);
                intent.setClass(context,Spage.class);
                Bundle fBundle=new Bundle();
                fBundle.putString("url",ans.get(position).get("href"));
                intent.putExtras(fBundle);
                context.startActivity(intent);

            }

        });
    }
}