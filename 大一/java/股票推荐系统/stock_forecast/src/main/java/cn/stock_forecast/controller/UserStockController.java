package cn.stock_forecast.controller;

import cn.stock_forecast.domain.Page;
import cn.stock_forecast.domain.Stock;
import cn.stock_forecast.domain.User;
import cn.stock_forecast.domain.UserStock;
import cn.stock_forecast.service.UserStockService;
import cn.stock_forecast.utils.StockUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 程梁
 * @Date: 2021/12/07/15:08
 */
@Controller
@RequestMapping("userStock")
public class UserStockController {

    @Autowired
    private UserStockService userStockService;



    @RequestMapping("deleteAllStock")
    public ModelAndView deleteAllStock(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }


    @RequestMapping("showAllStock")
    public ModelAndView showAllStock(HttpServletRequest request,HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("myCollection");
        User user = new User();
        int uid = (Integer)request.getSession().getAttribute("uid");
        String username = (String)request.getSession().getAttribute("username");
        String password = (String)request.getSession().getAttribute("password");
        user.setId(uid);
        user.setUsername(username);
        user.setPassword(password);
        modelAndView.addObject("page",getAllStock(request,user));
        return modelAndView;
    }

    @RequestMapping("judgeCollection")
    @ResponseBody
    public String judgeCollection(UserStock userStock,HttpServletRequest request){
        int uid = 0;
        try {
            uid = (Integer)request.getSession().getAttribute("uid");
        } catch (Exception e) {
            System.out.println(request.getSession().getAttribute("uid"));
            System.out.println(request.getSession().getAttributeNames());
        }
        userStock.setUid(uid);
        return userStockService.judgeCollection(userStock)+"";
    }

    @RequestMapping("collection")
    @ResponseBody
    public String collection(UserStock userStock,HttpServletRequest request){
        int uid = (Integer)request.getSession().getAttribute("uid");
        userStock.setUid(uid);
        boolean flag = userStockService.save(userStock);
        if (flag){
            return "succeed";
        }else {
            return "fail";
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(UserStock userStock,HttpServletRequest request){
        int uid = (Integer)request.getSession().getAttribute("uid");
        userStock.setUid(uid);
        System.out.println(userStock);
        boolean flag = userStockService.delete(userStock);
        if (flag){
            return "succeed";
        }else {
            return "fail";
        }
    }

    public Page<Stock> getAllStock(HttpServletRequest request,User user) {
        String stock_name = request.getParameter("stock_name");
        String stock_id = request.getParameter("stock_id");
        int currentPage = 1;
        if (request.getParameter("currentPage") != null && !"".equals(request.getParameter("currentPage"))){
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
            if (currentPage < 1){
                currentPage = 1;
            }
        }
        int rows = 5;

        if (request.getParameter("rows") != null && !"".equals(request.getParameter("rows"))){
            rows = Integer.parseInt(request.getParameter("rows"));
            if (rows < 1){
                rows = 1;
            }
        }
        stock_name = stock_name == null ? "%" : "%"+stock_name+"%";
        stock_id = stock_id == null ? "%" : "%"+stock_id+"%";
        String sql = "select * from user_stock where stock_name like ? and stock_id like ? and uid = ? limit ?,?";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
        List<UserStock> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserStock.class), stock_name, stock_id, user.getId(),(currentPage - 1)*rows,rows);

        Page<Stock> page = new Page<>();

        String sqlAll = "select count(*) from user_stock where stock_name like ? and stock_id like ? and uid = ?";
        long temp = jdbcTemplate.queryForObject(sqlAll, Long.class, stock_name, stock_id, user.getId());
        int totalCount = (int)temp;
        page.setTotalCount(totalCount);
        page.setList(getStocks(query));
        page.setRows(rows);
        page.setCurrentPage(currentPage);
        page.setTotalPage((int)Math.ceil((double) totalCount/rows));
        return page;
    }
    public List<Stock> getStocks(List<UserStock> userStockList){
        List<Stock> stockList = new ArrayList<>();
        for (int i = 0; i < userStockList.size();i ++){
            String url = "https://hq.sinajs.cn/list="+userStockList.get(i).getStock_id();
            String charset = "GB18030";
            Stock stock = StockUtil.getStock(url, charset);
            stockList.add(stock);
        }
        return stockList;
    }
}
