package com.example.spring.study.dto;

import com.example.spring.study.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {

    private Long id;

    private String title;
    private String content;

    /*
    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }
 //대신해서 AllARgsConstructor


    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
*/ // 대신해서 ToString
    public Article toEntitiy() {
        return new Article(id,title,content);
    }
}
