package cn.stock_forecast.controller;

import cn.stock_forecast.domain.SimpleStock;
import cn.stock_forecast.domain.Stock;
import cn.stock_forecast.utils.StockUtil;
import cn.stock_forecast.utils.UrlContent;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author: 程梁
 * @Date: 2021/12/05/13:32
 */
@Controller
@RequestMapping("/stock")
public class StockData {
    @RequestMapping("/data")
    public ModelAndView data(@RequestParam int num){
        ModelAndView modelAndView = new ModelAndView();
        List<Stock> stockList = new ArrayList<>();
        while (stockList.size() < num){
            String[] arr = {"600","601","603"};
            String url = "https://hq.sinajs.cn/list=sh"+arr[(int) (Math.random()*3)]+(int)(Math.random()*900);
            String charset = "GB18030";
            Stock stock = StockUtil.getStock(url, charset);
            if (stock != null){
                stockList.add(stock);
            }
        }
        modelAndView.addObject("stockList",stockList);
        modelAndView.setViewName("main");
        return modelAndView;
    }


    @RequestMapping("/historyData")
    @ResponseBody
    public String historyData(String id){
        String url = "https://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol="+id+"&scale=240&ma=no&datalen=800";
        String content = UrlContent.getContent(url, null);
        return content;
    }
    @RequestMapping("/historyData_forecast")
    @ResponseBody
    public String historyData_forecast(String id){
        String url = "https://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol="+id+"&scale=240&ma=no&datalen=50";
        String content = UrlContent.getContent(url, null);
        return content;
    }

    @RequestMapping("/searchStockId")
    @ResponseBody
    public String searchStockId(String id){
        String url = "https://hq.sinajs.cn/list="+id;
        System.out.println(url);
        Stock stock = null;
        try {
            stock = StockUtil.getStock(url, null);
        } catch (Exception e) {
            System.out.println(stock);
            return "false";
        }
        if (stock != null){
            System.out.println(stock);
            return "true";
        }else {
            System.out.println(stock);
            return "false";
        }
    }


    @RequestMapping("/searchStock")
    @ResponseBody
    public String searchStock(String id){
        String url = "https://hq.sinajs.cn/list="+id;
        Stock stock = StockUtil.getStock(url, null);
        try {
            stock.setName(URLEncoder.encode(stock.getName(),"utf-8"));
            stock.setTurnover(URLEncoder.encode(stock.getTurnover(),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("stock",stock);
        return jsonObject.toString();
    }
}
