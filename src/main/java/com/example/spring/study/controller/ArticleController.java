package com.example.spring.study.controller;

import com.example.spring.study.dto.ArticleForm;
import com.example.spring.study.entity.Article;
import com.example.spring.study.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j   // 로깅을 위한 골뱅이
public class ArticleController {
    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결
    private ArticleRepository articleRepository;

    @GetMapping("/articles")
    public String index(Model model){
        // 1. 모든 Article 가져오기
//        List<Article> articleEntityList = (List<Article>)articleRepository.findAll();
        List<Article> articleEntityList = articleRepository.findAll();
        // 2. 가져온 Article 묶음을 뷰로 전달
        model.addAttribute("articleList",articleEntityList);
        // 3. 뷰 페이지 설정
        return "articles/index";
    }



    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public  String createAcrticle(ArticleForm form){
        log.info(form.toString());

        //1. Dto를 Entity로 변환
        Article article = form.toEntitiy();
        log.info(article.toString());


        //2. repository에게 Entity를 Db안에 저장하게
        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = "+id);

        // 1: id로 데이터를 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 아이디에 있는 값을 가져오는데 없으면 null로 반환

        // 2: 가져온 데이터 모델에 등록하기
        model.addAttribute("article",articleEntity);

        // 3: 보여줄 페이지 설정
        return "articles/show";
    }
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id,Model model){
        //수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 모델 데이터 등록
        model.addAttribute("article",articleEntity);


        //뷰페이지 설정
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){

        // 1. DTO를 엔티티로 변환
        Article articleEntity = form.toEntitiy();

        // 2. 엔티티를 DB저장
        // 2-1 : DB에서 기존 데이터를 가져온다.
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        // 2-2 :  기존 데이터값을 갱신한다.
        if (target != null){
            articleRepository.save(articleEntity);
        }

        // 3. 수정 결과 페이지 리다이렉트 한다.
        return "redirect:/articles/" + articleEntity.getId();
    }

}
