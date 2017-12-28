package com.bnuz.javaweb.dao;

import com.bnuz.javaweb.tag.Pager;
import com.bnuz.javaweb.tag.PagerHelper;
import com.bnuz.javaweb.vo.ArticleDetail;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author caijiatao
 * @data 2017/12/27 下午12:32
 */
public class ArticleDao extends BaseDao {
    /**
     * 查找对应id的文章详细信息
     *
     * @param articleId
     * @return 查询文章列表list
     */
    public List queryArticleDetail(int articleId, Integer currentPage, Integer pageSize, HttpServletRequest request) {
        Connection connection = dbHelper.getConnection();
        int totalPage = 0;
        int totalSize = 0;
        String countSql = dbHelper.generateCountSql("T_Student_Article");

        List<ArticleDetail> articleDetailList = null;
        String sql = "SELECT " +
                "T_Student_Article.id , T_Student_Article.picture ,T_Student_Article.studentId, " +
                "T_Student_Article.content,s.`name`,T_Student_Article.title " +
                "FROM T_Student_Article " +
                "LEFT JOIN T_Student s ON s.id = T_Student_Article.studentId ";
        try {
            if(articleId != 0){
                //取出特定文章,跟语句加限制条件id
                sql = dbHelper.generateCountConditionSql(sql,"T_Student_Article.id");
                articleDetailList = dbHelper.executeQuery(sql,ArticleDetail.class,connection,articleId);
            }
            else{
                //取出总页数结果集
                ResultSet resultSet = dbHelper.executeQuery(countSql, connection);
                while (resultSet.next()) {
                    //还有一个结果取第一列
                    totalSize = resultSet.getInt(1);
                }
                //分页实体
                Pager pager = PagerHelper.getPager(totalSize, pageSize, currentPage, "articleList.do", request);
                //生成带limit的sql
                sql = PagerHelper.generateLimitCondition(sql);
                //查询出文章列表
                articleDetailList = dbHelper.executeQuery(sql, ArticleDetail.class, connection, pager.getStart(), pager.getPageSize());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleDetailList;
    }

    /**
     * 添加文章
     *
     * @param title 文章标题
     * @param studentId 学生id
     * @param content 文章内容
     */
    public boolean addArticle(String title, Integer studentId, String content, String picture) {
        boolean result = true;
        String sql = "INSERT INTO T_Student_Article (studentId,title,content,picture) values (?,?,?,?)";
        result = dbHelper.executeUpdateSql(sql, studentId, title, content, picture);
        return result;
    }

    /**
     * 删除对应文章的id
     * @param articleId 文章id
     */
    public boolean deleteArticle(int articleId){
        String sql = "DELETE FROM T_Student_Article WHERE id = ?";
        return dbHelper.executeUpdateSql(sql,articleId);
    }

    /**
     * 修改文章内容
     * @return update结果
     */
    public boolean modifyArticle(int articleId,String content){
        String sql = "UPDATE T_Student_Article SET content = ? WHERE id = ?";
        return dbHelper.executeUpdateSql(sql,content,articleId);
    }

    /**
     * 通过学生ID来查询文章
     * @param studentId
     * @return
     */
    public List<ArticleDetail> queryArticleByStudentId(Integer studentId){
        List<ArticleDetail> articleDetails = new ArrayList<>();
        return articleDetails;
    }
}
