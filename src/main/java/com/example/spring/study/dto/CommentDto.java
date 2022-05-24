package com.example.spring.study.dto;


import com.example.spring.study.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@Setter
@NoArgsConstructor
@ToString
@Getter
public class CommentDto {
    private Long id;
    @JsonProperty("article_id")
    private Long articleId;
    private String nickname;
    private String body;

    public static CommentDto createCommentDto(Comment i) {
        return new CommentDto(i.getId(),i.getArticle().getId(),i.getNickname(),i.getBody());
    }
}
