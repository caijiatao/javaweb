package com.bnuz.javaweb.util;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by cai on 2017/11/7.
 */
public class UploadUtil {
    private final static String TARGET_DIR = "/";

    public static String getUploadPath(HttpServletRequest request){
        String targetPath = request.getServletContext().getRealPath(TARGET_DIR);
        return targetPath;
    }
    public static byte[] getBytesFromInputStream(InputStream inputStream){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] data = null;
        int b = 0;//用于接受文件读出的每个数据
        try {
            while((b = inputStream.read()) != -1){
                byteArrayOutputStream.write(b);
            }
            //读取完后转化成byte[]数组
            data = byteArrayOutputStream.toByteArray();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
