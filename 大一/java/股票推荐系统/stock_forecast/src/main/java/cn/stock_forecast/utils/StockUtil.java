package cn.stock_forecast.utils;

import cn.stock_forecast.domain.Stock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: 程梁
 * @Date: 2021/12/05/12:52
 */
public class StockUtil {
    public static Stock getStock(String url,String charset) {
        String content = UrlContent.getContent(url, charset);
        String[] split = content.split("=");
        if (split[1].length() < 10){
            return null;
        }
        String s = split[1].substring(1,split[1].length()-1);
        String[] strings = s.split(",");
        Stock stock = new Stock();
        stock.setId(split[0].substring(split[0].length()-8));
        stock.setName(strings[0]);
        stock.setOpenPrice(Double.parseDouble(strings[1]));
        stock.setClosePrice(Double.parseDouble(strings[2]));
        if (stock.getOpenPrice()==0 || stock.getClosePrice() == 0){
            return null;
        }
        stock.setCurrentPrice(Double.parseDouble(strings[3]));
        stock.setHighestPrice(Double.parseDouble(strings[4]));
        stock.setLowestPrice(Double.parseDouble(strings[5]));
        stock.setTransactionNumber(Long.parseLong(strings[8]));
        stock.setTransactionPrice(Double.parseDouble(strings[9]));
        stock.setDate(strings[30]+" "+strings[31] );
        String s1 = (stock.getHighestPrice()-stock.getLowestPrice())/stock.getClosePrice()*100+"";
        String[] split1 = s1.split("\\.");
        String amp3 = null;
        try {
            String amp1 = split1[0]+"."+ (split1[1].length()>2?split1[1].substring(0,2):split1[1]);
            stock.setAmplitude(amp1+"%");
            String s2 = (stock.getCurrentPrice()-stock.getClosePrice())/ stock.getClosePrice()*100+"";
            String[] split2 = s2.split("\\.");
            String amp2 = split2[0]+"."+ (split2[1].length()>2?split2[1].substring(0,2):split2[1]);
            stock.setFluctuationRange(amp2+"%");
            String s3 = stock.getTransactionPrice()/10000+"";
            String[] split3 = s3.split("\\.");
            amp3 = split3[0]+"."+ (split3[1].length()>2?split3[1].substring(0,2):split3[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        stock.setTurnover(amp3+"万元");
        return stock;
    }
}
