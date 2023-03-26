package cn.stock_forecast.service;

import cn.stock_forecast.domain.User;
import cn.stock_forecast.domain.UserStock;

/**
 * @Author: 程梁
 * @Date: 2021/12/07/18:15
 */
public interface UserStockService {

    public boolean save(UserStock userStock);

    public boolean judgeCollection(UserStock userStock);

    public boolean delete(UserStock userStock);
}
