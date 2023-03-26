package cn.stock_forecast.test;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EncodingUtils;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author: 程梁
 * @Date: 2022/05/04/16:03
 */
public class Test01 {

    @Test
    public void t1() throws IOException {
        String path = "http://hq.sinajs.cn/list=sh600519";

        //1.获得一个httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //2.生成一个get请求
        HttpGet httpget = new HttpGet(path);
        httpget.setHeader("Referer", "http://finance.sina.com.cn");
        //3.执行get请求并返回结果
        CloseableHttpResponse response = httpclient.execute(httpget);
        String s = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(s);
    }
}
