package com.bnuz.javaweb.service.impl;

import com.bnuz.javaweb.dao.ArticleDao;
import com.bnuz.javaweb.service.ArticleService;
import com.bnuz.javaweb.util.UploadUtil;
import com.bnuz.javaweb.vo.ArticleDetail;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 * @author caijiatao
 * @data 2017/12/27 下午4:47
 */
public class ArticleServiceImpl implements ArticleService {
    private ArticleDao articleDao = new ArticleDao();

    @Override
    public boolean publishArticle(Integer studentId, String title, String content, Part pictures, HttpServletRequest request) {
        //存储论文封面路径
        String picture = insertPicture(pictures, UploadUtil.getUploadPath(request));
        return articleDao.addArticle(title, studentId, content, picture);
    }

    @Override
    public List<ArticleDetail> queryArticleByStudentId(Integer studentId) {
        return null;
    }

    @Override
    public List<ArticleDetail> queryArticleDetail(Integer id, Integer currentPage, Integer pageSize, HttpServletRequest request) {
        return articleDao.queryArticleDetail(id, currentPage, pageSize, request);
    }

    /**
     * 上传文章封面图片
     *
     * @param picture
     * @param filePath
     * @return
     */
    public String insertPicture(Part picture, String filePath) {
        //图片路径
        String fileName = "";
        fileName = "img/" + System.currentTimeMillis() + picture.getSubmittedFileName();
        if (picture.getSubmittedFileName() != null) {
            //有文件名，上传的是文件进行写入本地磁盘
            try {
                String dir = filePath + "img/";
                File dirFile = new File(dir);
                if (!dirFile.exists()) {
                    dirFile.mkdir();
                }
                //文件名+时间戳构成图片存放的地方
                String file = filePath + fileName;
                File image = new File(file);
                if (!image.exists()) {
                    //文件不存在则创建
                    image.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(image);
                byte[] bytes = UploadUtil.getBytesFromInputStream(picture.getInputStream());
                fileOutputStream.write(bytes);
                fileOutputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

    @Override
    public boolean modifyArticle(int articleId, String content) {
        return articleDao.modifyArticle(articleId,content);
    }

    @Override
    public boolean deleteArticle(int articleId) {
        return articleDao.deleteArticle(articleId);
    }


}
