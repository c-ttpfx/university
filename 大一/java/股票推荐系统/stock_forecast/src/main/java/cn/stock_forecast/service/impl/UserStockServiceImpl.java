package cn.stock_forecast.service.impl;

import cn.stock_forecast.domain.UserStock;
import cn.stock_forecast.mapper.UserStockMapper;
import cn.stock_forecast.service.UserStockService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: 程梁
 * @Date: 2021/12/07/18:15
 */
public class UserStockServiceImpl implements UserStockService {

    @Autowired
    private UserStockMapper userStockMapper;
    @Override
    public boolean save(UserStock userStock) {
        int rows = 0;
        try {
            rows = userStockMapper.save(userStock);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return rows > 0;
    }

    @Override
    public boolean judgeCollection(UserStock userStock) {
        UserStock us = null;
        try {
            us = userStockMapper.judgeCollection(userStock);
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
        return us != null;
    }

    @Override
    public boolean delete(UserStock userStock) {
        int rows = 0;
        try {
            rows = userStockMapper.delete(userStock);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return rows > 0;
    }
}
