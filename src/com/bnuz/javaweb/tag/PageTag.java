package com.bnuz.javaweb.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class PageTag extends TagSupport {
    //分页属性实体
    private Pager pager;

    //构造分页字符串
    StringBuffer pageStr;

    @Override
    public int doStartTag() throws JspException {
        //获得jsp输出流
        JspWriter jspWriter = pageContext.getOut();
        pageStr = new StringBuffer();
        System.out.println(pager.toString());
        try {
            //首页
            constructPage(1,InnerHTML.FirstPage.toString());
            //上一页
            int prePageNum = pager.getCurrentPage() > 1 ? pager.getCurrentPage() - 1 : pager.getCurrentPage();
            constructPage(prePageNum, InnerHTML.PrePage.toString());
            //中间页数
            for(int i = 1;i <= pager.getTotalPage() ; i++){
                constructPage(i,i+"");
            }
            //下一页
            int nextPageNum = pager.getCurrentPage() < pager.getTotalPage() ? pager.getCurrentPage() + 1 : pager.getCurrentPage();
            constructPage(nextPageNum,InnerHTML.NextPage.toString());
            //末页
            constructPage(pager.getTotalPage(),InnerHTML.LastPage.toString());
            System.out.println(pageStr.toString());
            //写入到jsp页面中
            jspWriter.write(pageStr.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }

    /**
     * @param pageNum   要跳到的页数
     * @param innerHTMl 跳转中间的html内容
     */
    public void constructPage(int pageNum, String innerHTMl) {
        //分页前缀和后缀
        String prePage = "<a href=\"" + pager.getLinkUrl() + "?currentPage=";

        String midPage = "\">";
        String afterPage = "</a>";
        //构造出跳页超链接 <a href="articleList.do?currentPage=pageNum">innerHTML</a>
        pageStr.append(prePage);
        pageStr.append(pageNum);
        pageStr.append(midPage);
        pageStr.append(innerHTMl);
        pageStr.append(afterPage);
    }
}
