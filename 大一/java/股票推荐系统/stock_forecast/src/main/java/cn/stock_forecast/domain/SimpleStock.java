package cn.stock_forecast.domain;

/**
 * @Author: 程梁
 * @Date: 2021/12/05/23:46
 */
public class SimpleStock {
    private String day;
    private double open;
    private double high;
    private double low;
    private double close;
    private long volume;

    @Override
    public String toString() {
        return "SimpleStock{" +
                "day='" + day + '\'' +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", volume=" + volume +
                '}';
    }
    public String getJson(){
        return "['"+day+"',"+open+","+high+","+low+","+close+"]";
    }
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public SimpleStock(String day, double open, double high, double low, double close, long volume) {
        this.day = day;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    public SimpleStock() {
    }
}
