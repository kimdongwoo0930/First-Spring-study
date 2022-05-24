package com.example.spring.study.entity;

import com.example.spring.study.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne  // 해당 댓글 엔티티 여러개가 하나의 Article에 연관
    @JoinColumn(name = "article_id")  // 대상정보의 컬럼에 대표값 저장
    private Article article;

    @Column
    private String nickname;
    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Article article) {
        if(dto.getId() != null) throw new IllegalArgumentException("실패 id가 없어야합니다");
        if(dto.getArticleId() != article.getId()) throw new IllegalArgumentException("게시글의 아이디가 잘못되었습니다.");
        return new Comment(dto.getId(),article,dto.getNickname(),dto.getBody());
    }
}
