package com.example.a10515.novel;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.transition.TransitionManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.IOException;
import java.util.Map;

import static android.view.MotionEvent.ACTION_MOVE;


/**
 * Created by 10515 on 2017/2/28.
 */

public class Tpage extends Activity{
    private ScrollView scrollView;
    private TextView tx;
    private Novel n;
    private Map<String,String> content ,content_pre,content_next;
    private Button btn,btn1;
    private String addr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tpage);
        scrollView=(ScrollView)findViewById(R.id.scrollView);
        tx=(TextView)findViewById(R.id.textView);
        btn=(Button)findViewById(R.id.button);
        btn1=(Button)findViewById(R.id.button1);

     /*   btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    tx.setText("");
                content_pre=n.getContent(content.get("pre"));
                    tx.setText(content_pre.get("title"));
                    tx.append(content_pre.get("content"));
                    content=content_pre;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    tx.setText("");
                    content_next=n.getContent(content.get("next"));
                    tx.setText(content_next.get("title"));
                    tx.append(content_next.get("content"));
                    content=content_next;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
*/
        Bundle sBundle=getIntent().getExtras();
        String url =sBundle.getString("url");
        String addr=sBundle.getString("addr");
        new MyThreadT(addr,url,tx,new Handler(),Tpage.this,btn,btn1).start();




    }
}
