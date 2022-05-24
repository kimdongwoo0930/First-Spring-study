package com.example.spring.study.api;

import com.example.spring.study.dto.CommentDto;
import com.example.spring.study.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired private CommentService commentService;

    //댓글 목록 조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
        //서비스에서 위임
        List<CommentDto> dtos = commentService.comments(articleId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    //댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> Create(@RequestBody CommentDto dto, @PathVariable Long articleId){
        CommentDto dtos = commentService.Create(dto,articleId);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    // 댓글 수정

    // 댓글 삭제
}
