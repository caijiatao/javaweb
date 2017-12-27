package com.bnuz.javaweb.tag;

/*
分页信息实体类
 */
public class Pager {
    //记录总行数
    private int totalRow;
    //总页数
    private int totalPage;
    //当前页面
    private int currentPage;
    //每一个的大小
    private int pageSize;
    //起始记录
    private int start;
    //要跳转的url
    private String linkUrl;

    public Pager(){}
    public Pager(int totalRow,Integer currentPage,Integer pageSize) {
        //初始化数据构造
        this.pageSize = pageSize == null ? 5 : pageSize;
        this.currentPage = currentPage == null ? 1 : currentPage;
        this.totalRow = totalRow;
        //计算出总页数
        totalPage = (int)Math.ceil(totalRow/(pageSize*1.0));
        //如果小于1则给总页数为1
        totalPage = totalPage < 1 ? 1 : totalPage;
        //如果当前页数超过总页数，则访问最后一页
        setCurrentPage(this.currentPage > totalPage ? totalPage : this.currentPage);
        setStart((currentPage-1)*pageSize);
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    @Override
    public String toString() {
        return "Pager{" +
                "totalRow=" + totalRow +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", start=" + start +
                ", linkUrl='" + linkUrl + '\'' +
                '}';
    }
}
