package com.bnuz.javaweb.dao;

import com.bnuz.javaweb.jdbc.DBHelper;
import com.bnuz.javaweb.tag.Pager;
import com.bnuz.javaweb.tag.PagerHelper;
import com.bnuz.javaweb.vo.ArticleDetail;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author caijiatao
 * @data 2017/12/27 下午12:32
 */
public class ArticleDao extends BaseDao {
    /**
     * 查找对应id的文章详细信息
     *
     * @param id
     * @return 查询文章列表list
     */
    public List queryArticleDetail(int id, Integer currentPage, Integer pageSize, HttpServletRequest request) {
        Connection connection = dbHelper.getConnection();
        int totalPage = 0;
        int totalSize = 0;
        String countSql = dbHelper.generateCountSql("T_Student_Article");

        List<ArticleDetail> articleDetailList = null;
        String sql = "SELECT " +
                "T_Student_Article.id , T_Student_Article.picture ,T_Student_Article.studentId, " +
                "T_Student_Article.content,T_Student_Article.publishDate,s.`name`,T_Student_Article.title " +
                "FROM T_Student_Article " +
                "LEFT JOIN T_Student s ON s.id = article.studentId ";
        try {
            //取出总页数结果集
            ResultSet resultSet = dbHelper.executeQuery(countSql, connection);
            while (resultSet.next()) {
                //还有一个结果取第一列
                totalSize = resultSet.getInt(1);
            }
            //分页实体
            Pager pager = PagerHelper.getPager(totalSize, pageSize, currentPage, "articleList.do", request);
            //生成带limit的sql
            PagerHelper.generateLimitCondition(sql);
            //查询出文章列表
            articleDetailList = dbHelper.executeQuery(sql,ArticleDetail.class,connection,pager.getStart(),pager.getPageSize());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleDetailList;
    }
}
