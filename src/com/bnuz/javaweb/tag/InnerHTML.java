package com.bnuz.javaweb.tag;

public enum InnerHTML {
    PrePage("上一页"),
    NextPage("下一页"),
    FirstPage("首页"),
    LastPage("末页");

    String html;
    InnerHTML(String html){
        this.html = html;
    }

    @Override
    public String toString() {
        return html;
    }

}
