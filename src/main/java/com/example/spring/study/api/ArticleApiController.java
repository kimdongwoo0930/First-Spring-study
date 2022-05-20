package com.example.spring.study.api;


import com.example.spring.study.dto.ArticleForm;
import com.example.spring.study.entity.Article;
import com.example.spring.study.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleApiController {
    @Autowired
    private ArticleRepository articleRepository;

    // GET
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }

    // POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm Dto){  //Request 바디에서 가져와라
        Article article = Dto.toEntitiy();
        return articleRepository.save(article);
    }

    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@RequestBody ArticleForm dto, @PathVariable Long id){
        // 1. 수정용 엔티티
        Article article = dto.toEntitiy();
        // 2. 대상엔티티를 조회
        Article target = articleRepository.findById(id).orElse(null);
        // 3. 잘못된 요청 처리(대상이없거나 id가 다른경우)
        if(target == null || id != article.getId()){
            // 400, 잘못된 요청
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 4. 업데이트 정상 응답
        target.patch(article);

        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        // 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        //대상 삭제
        if(target == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        articleRepository.delete(target);
        //데이터 반환
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
