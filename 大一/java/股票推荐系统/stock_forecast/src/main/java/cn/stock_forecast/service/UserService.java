package cn.stock_forecast.service;

import cn.stock_forecast.domain.User;

/**
 * @Author: 程梁
 * @Date: 2021/12/07/1:07
 */
public interface UserService {
    public boolean login(User user);

    public boolean register(User user);

    public int getIdByUserNameAndPassword(String username,String password);
}
