package com.bnuz.javaweb.servlet;

import com.bnuz.javaweb.bean.Student;
import com.bnuz.javaweb.service.ArticleService;
import com.bnuz.javaweb.service.impl.ArticleServiceImpl;
import com.bnuz.javaweb.util.ServletUtil;
import com.bnuz.javaweb.vo.ArticleDetail;

import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author caijiatao
 * @data 2017/12/27 上午11:09
 */

@WebServlet(name = "Article",urlPatterns = {"/addArticle.do","/articleList.do","/publishArticle.do","/deleteArticle.do","/modifyArticle.do"})
@MultipartConfig
public class ArticleServlet extends HttpServlet{

    private ArticleService articleService = new ArticleServiceImpl();

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
     * @param request 请求对象
     * @param response 响应对象
     * @throws ServletException servlet异常
     * @throws IOException 输入输出异常
     */
    private void articleList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String articleId = request.getParameter("articleId");
        Integer id = 0;//默认查询下全部文章
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("listArticle.jsp");
        Integer currentPage = request.getParameter("currentPage") == null ? null : Integer.parseInt(request.getParameter("currentPage"));
        Integer pageSize = request.getParameter("pageSize") == null ? null : Integer.parseInt(request.getParameter("pageSize"));
        if (articleId != null) {
            id = Integer.parseInt(articleId);//articleid不为null证明查询下具体某篇文章
        }
        List<ArticleDetail> articleDetailList = articleService.queryArticleDetail(id, currentPage, pageSize, request);
        if (id == 0) {
            //转发到全部文章页面
            request.setAttribute("articleDetailList", articleDetailList);
        } else {
            if (articleDetailList.size() > 0){
                request.setAttribute("articleDetail", articleDetailList.get(0));
                requestDispatcher = request.getRequestDispatcher("editArticle.jsp");
            }

        }

        requestDispatcher.forward(request, response);
    }

    /**
     * @param request 请求对象
     * @param response 响应对象
     * @throws ServletException servlet异常
     * @throws IOException 输入输出异常
     */
    private void publishArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student student = (Student) request.getSession().getAttribute("student");
        Integer studentId = student != null ? student.getId() : 0;
        //获得文件
        Part picture = request.getPart("picture");
        boolean publishArticleResult = articleService.publishArticle(studentId, request.getParameter("title"), request.getParameter("content"),picture,request);
        if (publishArticleResult) {
            request.getRequestDispatcher("success.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    /**
     * @param request 请求对象
     * @param response 响应对象
     * @throws ServletException servlet异常
     * @throws IOException 输入输出异常
     */
    public void deleteArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        articleService.deleteArticle(articleId);
        request.setAttribute("articleDetailList", articleService.queryArticleDetail(0, null, null, request));
    }

    /**
     * @param request 请求对象
     * @param response 响应对象
     * @throws ServletException servlet异常
     * @throws IOException 输入输出异常
     */
    public void modifyArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int articleId = request.getParameter("articleId") == null ? 0 : Integer.parseInt(request.getParameter("articleId"));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("editArticle.jsp");
        if (articleId == 0) {
            requestDispatcher = request.getRequestDispatcher("error.jsp");//没有传文章id
        }
        articleService.modifyArticle(articleId, request.getParameter("content"));
        requestDispatcher.forward(request, response);
    }
}
