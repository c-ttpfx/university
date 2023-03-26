import cn.stock_forecast.domain.SimpleStock;
import cn.stock_forecast.domain.Stock;
import cn.stock_forecast.utils.StockUtil;
import cn.stock_forecast.utils.UrlContent;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 程梁
 * @Date: 2021/12/04/13:05
 */
public class UtilsTest {
    @Test
    public void test01(){
        String content = UrlContent.getContent("http://hq.sinajs.cn/list=sh601006", "GB18030");
        System.out.println(content);
    }
    @Test
    public void test02(){
        Stock stock = StockUtil.getStock("https://hq.sinajs.cn/list=sh601006", "GB18030");
        System.out.println(stock);
    }

    @Test
    public void test03(){
        List<Stock> stockList = new ArrayList<>();
        while (stockList.size() < 10){
            String[] arr = {"600","601","603"};
            String url = "https://hq.sinajs.cn/list=sh"+arr[(int) (Math.random()*3)]+(int)(Math.random()*900);
            String charset = "GB18030";
            Stock stock = StockUtil.getStock(url, charset);
            if (stock != null){
                stockList.add(stock);
            }
        }
        for (Stock stock : stockList) {
            System.out.println(stock);
        }
    }
    @Test
    public void test04(){
        String content = UrlContent.getContent("https://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol=sz000001&scale=240&ma=no&datalen=10", null);
        System.out.println(content);
    }
    @Test
    public void test05(){
        String content = UrlContent.getContent("https://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol=sz000001&scale=240&ma=no&datalen=10", null);
        System.out.println(content);
        List<SimpleStock> simpleStocks = JSON.parseArray(content, SimpleStock.class);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < simpleStocks.size()-1; i++) {
            sb.append(simpleStocks.get(i).getJson());
            sb.append(",");
        }
        sb.append(simpleStocks.get(simpleStocks.size()-1));
        sb.append("]");
        System.out.println(sb);
    }
    @Test
    public void test07(){
        JSONObject jsonObject = new JSONObject();
        String content = UrlContent.getContent("http://hq.sinajs.cn/list=sh000000", "GB18030");
    }
}
