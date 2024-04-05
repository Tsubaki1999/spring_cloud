package com.stopbullying.community.democommunity.entity;
//封装分页相关的组件
public class Page {

    //当前页面数
    private int current = 1;
    //当前显示上限
    private int limit = 10;
    //帖子数量，用于计算最大页数
    private int rows;
    //查询路径，用于复用分页链接
    private String path;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if(current>=1) {
            this.current = current;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if(limit >= 1 && limit <= 100) {
            this.limit = limit;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if(rows>=0) {
            this.rows = rows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    //获取当前页的起始行
    public int getOffset(){
        return (current-1)*limit;
    }

    //获取总页数
    public int getTotal(){
        if (rows % limit == 0) {
            return rows / limit;
        } else {
            return rows / limit + 1;
        }
    }

    //获取起始页
    public int getFrom() {
        int from = current - 2;
        return from < 1 ? 1 : from;
    }

    //获取终止页
    public int getTo() {
        int to = current + 2;
        int total = getTotal();
        return to > total ? total : to;
    }
}
