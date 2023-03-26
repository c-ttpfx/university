package cn.stock_forecast.mapper;

import cn.stock_forecast.domain.UserStock;

import java.util.HashMap;

/**
 * @Author: 程梁
 * @Date: 2021/12/07/18:14
 */
public interface UserStockMapper {


    public int save(UserStock userStock);

    public UserStock judgeCollection(UserStock userStock);

    public int delete(UserStock userStock);

}
