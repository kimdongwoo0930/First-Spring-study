package com.example.spring.study.service;

import com.example.spring.study.dto.CommentDto;
import com.example.spring.study.entity.Article;
import com.example.spring.study.entity.Comment;
import com.example.spring.study.repository.ArticleRepository;
import com.example.spring.study.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        // 댓글 목록 조회
        //List<Comment> comments = commentRepository.findByArticleId(articleId);
        // 변환 : 엔티티 -> DTO
        /*
        List<CommentDto> dtos = new ArrayList<>();
        for(Comment i : comments) {
            CommentDto dto = CommentDto.createCommentDto(i);
            dtos.add(dto);
        }
        */

        // 반환
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }
    @Transactional
    public CommentDto Create(CommentDto dto, Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("실패"));

        Comment comment = Comment.createComment(dto,article);

        Comment create = commentRepository.save(comment);

        return CommentDto.createCommentDto(create);



        //댓글
    }
}
