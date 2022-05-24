package com.example.spring.study.service;

import com.example.spring.study.dto.ArticleForm;
import com.example.spring.study.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest  // 해당클래스는 스프링부트와 연동되어 테스팅된다.
class ArticleServiceTest {

    @Autowired ArticleService articleService;

    @Test
    void index() {
        // 예상
        Article a = new Article(1L,"가가가가","1111");
        Article b = new Article(2L,"나나나나","2222");
        Article c = new Article(3L,"다다다다","3333");
        Article d = new Article(4L,"라라라라","4444");
        List<Article> expected = new ArrayList<>(Arrays.asList(a,b,c,d));
        // 실제
        List<Article> articles =  articleService.index();

        // 비교
        assertEquals(expected.toString(),articles.toString());
    }

    @Test
    void show_성공____존재하는_id_입력() {
        //예상

        Long id = 1L;
        Article expected = new Article(id,"가가가가","1111");

        //실제
        Article article = articleService.show(id);
        //비교
        assertEquals(expected.toString(),article.toString());


    }

    @Test
    void show_실패____존재하지않는_id_입력(){
        //예상

        Long id = 100L;
        Article expected = null;

        //실제
        Article article = articleService.show(id);
        //비교
        assertEquals(expected,article);
    }

    @Test
    @Transactional
    void create_성공___title과_content만_있는_dto_입력() {
        //예상
        String title = "마마마마";
        String content = "5555";
        ArticleForm dto = new ArticleForm(null,title,content);
        Article expected = new Article(5L,title,content);
        //실제
        Article article = articleService.create(dto);
        //비교
        assertEquals(expected.toString(),article.toString());
    }
    @Test
    @Transactional
    void create_실패____id가_포함된_dto가_입력(){
        String title = "마마마마";
        String content = "5555";
        ArticleForm dto = new ArticleForm(5L,title,content);
        Article expected = null;
        //실제
        Article article = articleService.create(dto);
        //비교
        assertEquals(expected,article);
    }

    @Test
    @Transactional
    void update__성공___존재하는_id와_title_content가_있는_dto_입력() {
        //예시
        Long id = 1L;
        String title = "나나나아앙";
        String content = "123123";
        ArticleForm dto = new ArticleForm(id,title,content);
        Article expected = new Article(id,title,content);
        //실제
        Article articles = articleService.update(id,dto);
        //비교
        assertEquals(expected.toString(),articles.toString());
    }

    @Test
    @Transactional
    void update__실패__존재하지_않는_id_dto_입력() {
        //예시
        Long id = 5L;
        String title = "나나나아앙";
        String content = "123123";
        ArticleForm dto = new ArticleForm(id,title,content);
        Article expected = null;
        //실제
        Article articles = articleService.update(id,dto);
        //비교
        assertEquals(expected,articles);
    }

    @Test
    @Transactional
    void delete__성공___존재하는_id_입력() {
        // 예상
        Long id = 1L;
        Article expected = new Article(1L,"가가가가","1111");
        //실제
        Article article = articleService.delete(id);
        //비교
        assertEquals(expected.toString(),article.toString());

    }

    @Test
    @Transactional
    void delete__실패___존재하지_않는_id_입력(){
        // 예상
        Long id = -1L;
        Article expected = null;
        //실제
        Article article = articleService.delete(id);
        //비교
        assertEquals(expected,article);

    }

}