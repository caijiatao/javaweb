package com.bnuz.javaweb.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author caijiatao
 * @data 2017/12/27 上午11:11
 */

/*
servlet的工具包
 */
public class ServletUtil {

    public static Method getMethod(Class clazz,HttpServletRequest request , HttpServletResponse response){
        String methodName = request.getServletPath().substring(1, request.getServletPath().length() - 3);
        Method method = null;
        try {
            method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return method;
    }
}
