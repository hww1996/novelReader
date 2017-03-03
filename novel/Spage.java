package com.example.a10515.novel;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 10515 on 2017/2/28.
 */
//implements AdapterView.OnItemClickListener
public class Spage extends Activity  {
    private ListView listView;
    private SimpleAdapter simp_adapter;
    private List<Map<String, Object>> dataList;
    private Novel n;
    private ArrayList<HashMap<String, String>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spage);
        listView = (ListView) findViewById(R.id.listView);

        Bundle sBundle = getIntent().getExtras();
        String url = sBundle.getString("url");

        new MyThreadS(url, listView, new Handler(), Spage.this).start();

      /*  try {
            list=n.getList(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        dataList=new ArrayList<Map<String,Object>>();
        simp_adapter=new SimpleAdapter(this,getData(),R.layout.spageitem,new String[]{"textView"},new int[]{R.id.textView});
        listView.setAdapter(simp_adapter);
        listView.setOnItemClickListener(this);

    }
    public List<Map<String,Object>> getData(){
        for(int i=0;i<list.size();i++){
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("textView",list.get(i).get("title"));
            dataList.add(map);

        }
        return dataList;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


    }*/
    }
}
