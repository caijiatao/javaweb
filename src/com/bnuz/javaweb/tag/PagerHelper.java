package com.bnuz.javaweb.tag;

import javax.servlet.http.HttpServletRequest;

/*
用于控制分页实体
 */
public class PagerHelper {
    /**
     *
     * @param totalRows 总记录数
     * @param linkUrl 要链接的url
     * @param request 请求的request
     * @return 分页实体
     */
    public static Pager getPager(int totalRows,Integer pageSize,Integer currentPage,String linkUrl,HttpServletRequest request){
        currentPage = currentPage == null ? 1 : currentPage;
        pageSize = pageSize == null ? 5 : pageSize;
        //构造出分页累
        Pager pager  = new Pager(totalRows,currentPage,pageSize);
        //设置要跳转的路径
        pager.setLinkUrl(linkUrl);
        request.setAttribute("pager",pager);
        request.setAttribute("totalPage",pager.getTotalPage());
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("pageSize", pageSize);
        return pager;
    }

    /**
     * 生成分页限制条件
     * @param sql 要生成限制的sql
     * @return 生成好限制的sql
     */
    public static String generateLimitCondition(String sql){
        return sql.concat(" LIMIT ?,? ");
    }
}
