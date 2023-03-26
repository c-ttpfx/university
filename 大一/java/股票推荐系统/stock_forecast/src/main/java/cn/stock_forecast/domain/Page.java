package cn.stock_forecast.domain;

import java.util.List;

/**
 * 我亦无他,唯手熟尔
 *
 * @Author: 程梁
 * @Date: 2021/10/23/14:32
 * @Description: 为人所不为, 能人所不能
 */
public class Page<T> {
    private int totalCount;
    private int totalPage;
    private List<T> list;
    private int currentPage;
    private int rows;

    public Page() {
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public Page(int totalCount, int totalPage, List list, int currentPage, int rows) {
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.list = list;
        this.currentPage = currentPage;
        this.rows = rows;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
