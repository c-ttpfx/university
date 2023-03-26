package cn.stock_forecast.domain;

import java.sql.Time;
import java.util.Date;

/**
 * @Author: 程梁
 * @Date: 2021/12/05/12:44
 */
public class Stock {
    private String id;
    private String name;
    private double openPrice;
    private double closePrice;
    private double currentPrice;
    private double highestPrice;
    private double lowestPrice;
    private long transactionNumber;
    private double transactionPrice;
    private String fluctuationRange;
    private String amplitude;
    private String turnover;
    private String date;

    public String getFluctuationRange() {
        return fluctuationRange;
    }

    public void setFluctuationRange(String fluctuationRange) {
        this.fluctuationRange = fluctuationRange;
    }

    public String getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(String amplitude) {
        this.amplitude = amplitude;
    }

    public String getTurnover() {
        return turnover;
    }

    public void setTurnover(String turnover) {
        this.turnover = turnover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getClosePrice() {
        return closePrice;
    }



    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(double highestPrice) {
        this.highestPrice = highestPrice;
    }

    public double getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(double lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public long getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(long transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public double getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(double transactionPrice) {
        this.transactionPrice = transactionPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", openPrice=" + openPrice +
                ", closePrice=" + closePrice +
                ", currentPrice=" + currentPrice +
                ", highestPrice=" + highestPrice +
                ", lowestPrice=" + lowestPrice +
                ", transactionNumber=" + transactionNumber +
                ", transactionPrice=" + transactionPrice +
                ", fluctuationRange='" + fluctuationRange + '\'' +
                ", amplitude='" + amplitude + '\'' +
                ", turnover='" + turnover + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public Stock(String id, String name, double openPrice, double closePrice, double currentPrice, double highestPrice, double lowestPrice, long transactionNumber, double transactionPrice, String fluctuationRange, String amplitude, String turnover, String date) {
        this.id = id;
        this.name = name;
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.currentPrice = currentPrice;
        this.highestPrice = highestPrice;
        this.lowestPrice = lowestPrice;
        this.transactionNumber = transactionNumber;
        this.transactionPrice = transactionPrice;
        this.fluctuationRange = fluctuationRange;
        this.amplitude = amplitude;
        this.turnover = turnover;
        this.date = date;
    }

    public Stock() {
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
