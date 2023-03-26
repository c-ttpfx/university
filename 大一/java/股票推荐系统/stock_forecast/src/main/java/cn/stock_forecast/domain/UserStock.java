package cn.stock_forecast.domain;

/**
 * @Author: 程梁
 * @Date: 2021/12/07/15:57
 */
public class UserStock {
    private int uid;
    private String stock_id;
    private String stock_name;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getStock_id() {
        return stock_id;
    }

    public void setStock_id(String stock_id) {
        this.stock_id = stock_id;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    @Override
    public String toString() {
        return "UserStock{" +
                "uid=" + uid +
                ", stock_id='" + stock_id + '\'' +
                ", stock_name='" + stock_name + '\'' +
                '}';
    }

    public UserStock() {
    }

    public UserStock(int uid, String stock_id, String stock_name) {
        this.uid = uid;
        this.stock_id = stock_id;
        this.stock_name = stock_name;
    }
}
