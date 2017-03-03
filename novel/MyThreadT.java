package com.example.a10515.novel;

/**
 * Created by 10515 on 2017/3/3.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MyThreadT extends Thread{
    private String url=null,addr=null;
    private  Button pre;
    private Button next;
    private TextView tx=null;
    private Handler handler=null;
    private Map<String,String> ans=null;
    private Context context=null;
    MyThreadT(String addr,String url,TextView tx,Handler handler,Context context,Button pre,Button next){
        this.addr=addr;
        this.url=url;
        this.tx=tx;
        this.pre=pre;
        this.next=next;
        this.context=context;
        this.handler=handler;
    }
    public void run(){
        try {

            ans=new Novel().getContent(this.url);

        } catch (IOException e) {

            e.printStackTrace();
        }

        handler.post(new Runnable() {
            @Override

            public void run() {
                tx.setText(ans.get("title")+"\n");
                tx.append(ans.get("content"));



            }
        });
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String a=addr+ans.get("pre");
                 Toast.makeText(context,a,Toast.LENGTH_LONG).show();

             /*  try {
                    url=addr+ans.get("pre");
                    Toast.makeText(context,url,Toast.LENGTH_LONG).show();

                    ans=new Novel().getContent(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                tx.setText(ans.get("title"));
                tx.append(ans.get("content"));*/
                   new MyThreadT(addr,a,tx,new Handler(),context,pre,next).start();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String b=addr+ans.get("next");
             /*   try {
                    url=addr+ ans.get("next");
                    ans=new Novel().getContent(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                tx.setText(ans.get("title"));
                tx.append(ans.get("content"));*/
                  new MyThreadT(addr,b,tx,new Handler(),context,pre,next).start();
            }

        });







    }


    }

