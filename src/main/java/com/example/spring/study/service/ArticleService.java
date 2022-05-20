package com.example.spring.study.service;


import com.example.spring.study.dto.ArticleForm;
import com.example.spring.study.entity.Article;
import com.example.spring.study.repository.ArticleRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service // 서버스 선언 (서비스 객체를 스프링부트에 선언)
@Slf4j

public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }


    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntitiy();
        if(article.getId() != null)
            return null;
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        // 1. 수정용 엔티티
        Article article = dto.toEntitiy();
        // 2. 대상엔티티를 조회
        Article target = articleRepository.findById(id).orElse(null);
        // 3. 잘못된 요청 처리(대상이없거나 id가 다른경우)
        if(target == null || id != article.getId()){
            // 400, 잘못된 요청
            return null;
        }

        // 4. 업데이트 정상 응답
        target.patch(article);

        Article updated = articleRepository.save(target);
        return updated;
    }

    public Article delete(Long id) {
        // 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        //대상 삭제
        if(target == null){
            return null;
        }
        articleRepository.delete(target);
        return target;

    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // dto 묶음을 entity 묶음으로 변환
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntitiy())
                .collect(Collectors.toList());
        // entity 묶음을 DB로 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
        // 강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("결제 실패!")
        );
        log.info(articleList.toString());
        // 결과값 반환
        return articleList;
    }
}
