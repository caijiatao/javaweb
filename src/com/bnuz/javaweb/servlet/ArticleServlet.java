package com.bnuz.javaweb.servlet;

import com.bnuz.javaweb.util.ServletUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author caijiatao
 * @data 2017/12/27 上午11:09
 */

@WebServlet(name = "Article",urlPatterns = {"/addArticle.do","/articleList.do"})
@MultipartConfig
public class ArticleServlet extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        Method method = ServletUtil.getMethod(this.getClass(),request,response);
        try {
            method.invoke(this,request,response);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * 查询文章
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void articleList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String articleId = request.getParameter("articleId");
        Integer id = 0;//默认查询下全部文章
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("articleList.jsp");
        Integer currentPage = request.getParameter("currentPage") == null ? null : Integer.parseInt(request.getParameter("currentPage"));
        Integer pageSize = request.getParameter("pageSize") == null ? null : Integer.parseInt(request.getParameter("pageSize"));
        if (articleId != null) {
            id = Integer.parseInt(articleId);//articleid不为null证明查询下具体某篇文章
        }
        List<ArticleDetail> articleDetailList = userService.queryArticleDetail(id, currentPage, pageSize, request);
        if (id == 0) {
            //转发到全部文章页面
            request.setAttribute("articleDetailList", articleDetailList);
        } else {
            if (articleDetailList.size() > 0)
                request.setAttribute("articleDetail", articleDetailList.get(0));
            requestDispatcher = request.getRequestDispatcher("articleDetail.jsp");
        }
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("pageSize", pageSize);
        requestDispatcher.forward(request, response);
    }
}
