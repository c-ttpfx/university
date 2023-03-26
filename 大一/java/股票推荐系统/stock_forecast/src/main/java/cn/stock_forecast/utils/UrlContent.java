package cn.stock_forecast.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sun.net.www.http.HttpClient;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UrlContent {

    public static String getContent(String path, String charset){
        if (charset == null){
            charset = "GB18030";
        }
//        InputStream in = null;
//        StringBuilder result = new StringBuilder();
//
//        try {
//            URL url = new URL(path);
//
//            URLConnection uc = url.openConnection();
//            in = uc.getInputStream();// 获取读入流
//
//            InputStream raw = new BufferedInputStream(in);// 放入缓冲流
//            Reader r = new InputStreamReader(raw,charset);	// 用Reader接收
//            BufferedReader br= new BufferedReader(r);  //这样便于可以按行读
//
//            String s = br.readLine();
//            while(s!= null) {
//                result.append(s);
//                s = br.readLine();
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (in != null) {
//                    in.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return result.toString();

        //1.获得一个httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //2.生成一个get请求
        HttpGet httpget = new HttpGet(path);
        httpget.setHeader("Referer", "http://finance.sina.com.cn");
        //3.执行get请求并返回结果
        String s = null;
        try {
            CloseableHttpResponse response = httpclient.execute(httpget);
            s = EntityUtils.toString(response.getEntity(), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}
