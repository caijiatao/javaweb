package com.bnuz.javaweb.service;

import com.bnuz.javaweb.vo.ArticleDetail;

import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 * @author caijiatao
 * @data 2017/12/27 下午4:45
 */
public interface ArticleService {

    /**
     * 发布文章
     *
     * @param studentId 学生id
     * @param title 标题
     * @param content 内容
     * @param picture 图片
     * @return 发布文章成功或失败
     */
    public boolean publishArticle(Integer studentId, String title, String content, Part picture,HttpServletRequest request);

    /**
     * 通过学生id查询文章
     *
     * @param studentId 学生id
     * @return 文章列表
     */
    public List<ArticleDetail> queryArticleByStudentId(Integer studentId);

    /**
     * 查询某篇文章的细节
     *
     * @param id 文章id ， id==0的时候则查询全部文章
     * @return 文章列表
     */
    public List<ArticleDetail> queryArticleDetail(Integer id, Integer currentPage, Integer pageSize, HttpServletRequest request);

    /**
     * 删除文章
     *
     * @param articleId 某篇文章
     * @return 删除文章成功返回true
     */
    public boolean deleteArticle(int articleId);

    /**
     * 修改文章
     * @param articleId 文章id
     * @param content 修改后的内容
     * @return 返回修改成功或者失败，成功为true
     */
    public boolean modifyArticle(int articleId, String content);
}
