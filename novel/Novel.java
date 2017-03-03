package com.example.a10515.novel;

/**
 * Created by 10515 on 2017/3/3.
 */

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

class Novel{
    private final static String SourceURL="http://www.biquge.tw/";
    public Map<String,String> getContent(String nurl) throws IOException{
        String res="";
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet getmethod = new HttpGet(nurl);
        getmethod.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");
        HttpResponse response = httpclient.execute(getmethod);
        HttpEntity entity = response.getEntity();
        BufferedReader bf=new BufferedReader(new InputStreamReader(entity.getContent(),"utf-8"));
        String line=null;
        while((line=bf.readLine())!=null){
            res+=line;
        }
        bf.close();
        Map<String,String> ans=new HashMap<String, String>();
        Document doc=Jsoup.parse(res);
        String content=doc.select("div[id=\"content\"]").html();
        content=content
                .replaceAll("<br>|<br/>","\n")
                .replaceAll("<script[^>]*>[\\d\\D]*?</script>","")
                .replaceAll("&lt;", "<")
                .replaceAll("&gt;", ">")
                .replaceAll("&nbsp;", "  ");
        String title=doc.select("h1").text();
        String pre=doc.select("a[id=\"pager_prev\"]").attr("href");
        String next=doc.select("a[id=\"pager_next\"]").attr("href");
        ans.put("content",content);//内容
        ans.put("pre",pre);//上一章
        ans.put("next",next);//下一章
        ans.put("title",title);//标题
        return ans;
    }
    public ArrayList<HashMap<String,String> > getList(String url) throws IOException{
        String res="";
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet getmethod = new HttpGet(url);
        getmethod.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");
        HttpResponse response = httpclient.execute(getmethod);
        HttpEntity entity = response.getEntity();
        BufferedReader bf=new BufferedReader(new InputStreamReader(entity.getContent(),"utf-8"));
        String line=null;
        while((line=bf.readLine())!=null){
            res+=line;
        }
        bf.close();
        Document doc=Jsoup.parse(res);
        String l=doc.select("dl>dd").html();
        String[] split = l.split("\n");
        ArrayList<HashMap<String,String> > ans=new ArrayList<HashMap<String, String>>();
        for(String i:split){
            Pattern p=Pattern.compile("<a style=\"\" href=\"(.*?)\">(.*?)</a>");
            Matcher m=p.matcher(i);
            HashMap<String,String> mp=new HashMap<String, String>();
            if(m.find()){
                mp.put("href",m.group(1));
                mp.put("title",m.group(2));
                ans.add(mp);
            }
            else{
                mp.put("href",null);
                mp.put("title",null);
                ans.add(mp);
            }
        }
        return ans;
    }
    public ArrayList<HashMap<String,String> > Search(String content) throws IOException{
        String TargetURL="http://zhannei.baidu.com/cse/search?s=16829369641378287696&q="+content+"&isNeedCheckDomain=1&jump=1";
        String res="";
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet getmethod = new HttpGet(TargetURL);
        getmethod.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");
        HttpResponse response = httpclient.execute(getmethod);
        HttpEntity entity = response.getEntity();
        BufferedReader bf=new BufferedReader(new InputStreamReader(entity.getContent(),"utf-8"));
        String line=null;
        while((line=bf.readLine())!=null){
            res+=line;
        }
        bf.close();
        Document doc=Jsoup.parse(res);
        String list=doc.select("div[class=\"result-game-item-detail\"]").html();
        String[] split = list.split("</div>");
        ArrayList<HashMap<String,String> > ans=new ArrayList<HashMap<String, String>>();
        for(String i:split){
            HashMap<String,String> m=new HashMap<String, String>();
            Document d=Jsoup.parse(i+"</div>");
            String href=d.select("a[cpos=\"title\"]").attr("href");//url
            String title=d.select("a[cpos=\"title\"]").attr("title");//题目
            String summary=d.select("p[class=\"result-game-item-desc\"]").text();//简介
            String info=d.select("div[class=\"result-game-item-info\"]").html();//信息
            m.put("title",title);
            m.put("href",href);
            m.put("summary",summary);
            m.put("info", info);
            ans.add(m);
        }
        return ans;
    }
}


