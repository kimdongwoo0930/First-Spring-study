package com.example.spring.study.repository;

import com.example.spring.study.entity.Article;
import com.example.spring.study.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest  // JPA와 연동된 테스트
class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        /* Case 1: 4번 게시글의 모든 댓글 조회 */
        {
            // 입력데이터 준비
            Long articleId = 4L;
            //실제 수행
            List<Comment> comment = commentRepository.findByArticleId(articleId);
            // 예상하기
            Article article = new Article(4L,"당신의 영화","ㄷ");
            Comment a = new Comment(1L, article, "Park", "굳 윌 헌팅");
            Comment b = new Comment(2L, article, "Kim", "아이 엠 샘");
            Comment c = new Comment(3L, article, "Choi", "쇼생크의 탈출");
            List<Comment> expected = Arrays.asList(a, b, c);

            // 검증
            assertEquals(expected.toString(),comment.toString(),"4번 글의 모든 댓글 출력");
        }
        /* Case 2: 1번 게시글의 모든 댓글 조회 */
        {
            // 입력데이터 준비
            Long articleId = 1L;
            //실제 수행
            List<Comment> comment = commentRepository.findByArticleId(articleId);
            // 예상하기
            Article article = new Article(1L,"가가가가","1111");
            List<Comment> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(),comment.toString(),"1번 글의 모든 댓글 출력");
        }
        /* Case 3: 9번 게시글의 모든 댓글 조회 */
        {
            // 입력데이터 준비
            Long articleId = 9L;
            //실제 수행
            List<Comment> comment = commentRepository.findByArticleId(articleId);
            // 예상하기
            Article article = new Article(9L,"가가가가","1111");
            List<Comment> expected = Arrays.asList();

            // 검증
            assertEquals(expected,comment,"1번 글의 모든 댓글 출력");
        }
        /* Case 4: 9999번 게시글의 모든 댓글 조회 */
        {
            // 입력데이터 준비
            Long articleId = 9999L;
            //실제 수행
            List<Comment> comment = commentRepository.findByArticleId(articleId);
            // 예상하기
            Article article = new Article(9999L,"가가가가","1111");
            List<Comment> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(),comment.toString(),"1번 글의 모든 댓글 출력");
        }
        /* Case 5: -1번 게시글의 모든 댓글 조회 */
        {

        }

    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        /* Case 1: "Park"의 게시글의 모든 댓글 조회 */
        {
            //입력 데이터를 준비
            String nickname = "Park";
            // 실제 수행
            List<Comment> comment = commentRepository.findByNickname(nickname);
            // 예상하기
            Comment a = new Comment(1L, new Article(4L, "당신의 영화", "ㄷ"), nickname, "굳 윌 헌팅");
            Comment b = new Comment(4L, new Article(5L, "당신의 소울", "대"), nickname, "치킨");
            Comment c = new Comment(7L, new Article(6L, "당신 취미", "댓"), nickname, "조깅");
            List<Comment> expected = Arrays.asList(a,b,c);
            // 검증

            assertEquals(comment.toString(),expected.toString(),"park의 모든댓글 출력");
        }
        /* Case 2: "Kim"의 게시글의 모든 댓글 조회 */
        {
            //입력 데이터를 준비
            String nickname = "Kim";
            // 실제 수행
            List<Comment> comment = commentRepository.findByNickname(nickname);
            // 예상하기
            // kim의 코멘트 추가 --생략--
            // 검증

            //assertEquals(comment.toString(),expected.toString(),"kim" 의 모든댓글 출력");
        }
        /* Case 3: null 의 게시글의 모든 댓글 조회 */
        {
            //입력 데이터를 준비
            String nickname = null;
            // 실제 수행
            List<Comment> comment = commentRepository.findByNickname(nickname);
            // 예상하기
            List<Comment> expected = Arrays.asList();
            // 검증

            assertEquals(comment.toString(),expected.toString(),"null의 모든댓글 출력");
        }
        /* Case 4: ""의 게시글의 모든 댓글 조회 */
        {
            //입력 데이터를 준비
            String nickname = "";
            // 실제 수행
            List<Comment> comment = commentRepository.findByNickname(nickname);
            // 예상하기
            List<Comment> expected = Arrays.asList();
            // 검증

            assertEquals(comment.toString(),expected.toString(),"' '의 모든댓글 출력");
        }
    }
}