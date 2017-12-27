package com.bnuz.javaweb.bean;

/**
 * @author caijiatao
 * @data 2017/12/25 下午10:32
 */
public class Article {
    //文章id
    private Integer id;
    //发布该文章的学生id
    private Integer studentId;
    //文章标题
    private String title;
    //文章内容
    private String content;
    //文章封面路径
    private String picture;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
