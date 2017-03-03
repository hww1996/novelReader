package com.example.a10515.novel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.apache.http.conn.scheme.HostNameResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 10515 on 2017/2/28.
 */

public class Fpage extends Activity {

    private Button bt;
    private EditText et;
    private ListView listView;
    private SimpleAdapter simp_adapter;
    private List<Map<String,Object>> dataList;
    private ArrayList<HashMap<String,String> > search_res;
    private Novel n;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fpage);

        bt=(Button)findViewById(R.id.button);
        et=(EditText)findViewById(R.id.editText);
        listView=(ListView)findViewById(R.id.listView);
        dataList=new ArrayList<Map<String,Object>>();



        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MyThreadF(et,  new Handler(),listView, Fpage.this).start();


            /*    simp_adapter=new SimpleAdapter(Fpage.this,getData(),R.layout.fpageitem,
                        new String[]{"textView","textView1","webView"},new int[]{R.id.textView,R.id.textView1,R.id.webView});
                listView.setAdapter(simp_adapter);
                listView.setOnItemClickListener(Fpage.this);*/
            }
        });


    }
    /*public List<Map<String,Object>> getData(){
        Map<String,Object>map=new HashMap<String,Object>();
        for(int i=0;i<search_res.size();i++){
            map.put("textView",search_res.get(i).get("title"));
            map.put("textView1",search_res.get(i).get("summary"));
            map.put("textView2",search_res.get(i).get("info"));
            dataList.add(map);
        }


        return dataList;
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
           Intent intent = new Intent(Fpage.this,Spage.class);
           intent.setClass(Fpage.this,Spage.class);
                Bundle fBundle=new Bundle();
                fBundle.putString("url",search_res.get(i).get("href"));
                intent.putExtras(fBundle);
                startActivity(intent);

    }*/
}
