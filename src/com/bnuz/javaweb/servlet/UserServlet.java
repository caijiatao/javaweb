package com.bnuz.javaweb.servlet;


import com.bnuz.javaweb.bean.Student;
import com.bnuz.javaweb.service.UserService;
import com.bnuz.javaweb.service.impl.UserServiceImpl;
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
import java.util.List;

/**
 * Created by cai on 2017/11/1.
 */

@WebServlet(name = "User", urlPatterns = {"/login.do", "/register.do", "/queryStudent.do","/editStudentInfo.do","/deleteUser.do"})
@MultipartConfig
public class UserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        Method method = ServletUtil.getMethod(this.getClass(), request, response);
        try {
            method.invoke(this, request, response);
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
     * 记录用户登陆状态
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws ServletException servlet异常
     * @throws IOException 输入输出异常
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        Student student = userService.verifyStudentInfo(name, password);
        if (student == null) {
            //如果用户不存在则还是在login页面
            requestDispatcher = request.getRequestDispatcher("login.jsp");
        }

        request.getSession().setAttribute("student", student);//登陆成功设置session
        requestDispatcher.forward(request, response);
    }

    /**
     * 查询学生
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws ServletException servlet异常
     * @throws IOException 输入输出异常
     */
    public void queryStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        List<Student> students = userService.selectStudent(name);
        request.setAttribute("students", students);
        request.getRequestDispatcher("listStudent.jsp").forward(request, response);
    }

    /**
     * 学生注册
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws ServletException servlet异常
     * @throws IOException 输入输出异常
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String school = request.getParameter("school");
        if (userService.addStudent(name, password, school)) {
            //插入成功返回登陆界面
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            //插入失败提示错误
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    /**
     * 修改学生信息
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws ServletException servlet异常
     * @throws IOException 输入输出异常
     */
    public void editStudentInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student student = (Student) request.getSession().getAttribute("student");
        if(userService.modifyStudentInfo(student.getId(),request.getParameter("name"),request.getParameter("school"),request.getParameter("password"))){
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }else{
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }

    }

    /**
     * 删除用户
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws ServletException servlet异常
     * @throws IOException 输入输出异常
     */
    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(userService.deleteStudent(Integer.parseInt(request.getParameter("studentId").toString()))){
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }
        else{
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
    }
}