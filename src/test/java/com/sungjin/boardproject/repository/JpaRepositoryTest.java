package com.sungjin.boardproject.repository;

import com.sungjin.boardproject.config.JpaConfig;
import com.sungjin.boardproject.domain.Article;
import org.hibernate.sql.Delete;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)  //테스트 상태에서 불러오지않고 설정돼있는 db를 씀 (test.database.replace: none으로 모든 테스트 지정가능)
@ActiveProfiles("testdb")  //yaml 파일에서 지정한 테스트 지정
@DisplayName("Jpa 연결 테스트")
@Import(JpaConfig.class)  //Auditing을 위한 jpaconfig 임포트
@DataJpaTest
class JpaRepositoryTest {
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(@Autowired ArticleRepository articleRepository, @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorksFine(){
        //given

        //when
        List<Article> articles = articleRepository.findAll();

        //then
        assertThat(articles)
                .isNotNull()
                .hasSize(122);
    }

    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorksFine(){
        //given
        long previousCount = articleRepository.count();  //카운트
        Article article = Article.of("new article" , "new content" , "#spring");

        //when
        Article savedArticle = articleRepository.save(article);

        //then
        assertThat(articleRepository.count()).isEqualTo(previousCount+1);
    }

    @DisplayName("update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorksFine(){
        //given
        Article article = articleRepository.findById(1L).orElseThrow();
        String updatedHashTag = "#srsr";
        article.setHashtag(updatedHashTag);

        //when
        Article savedArticle = articleRepository.saveAndFlush(article);


        //then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag",updatedHashTag);
    }

    @DisplayName("delete 테스트")
    @Test
    void givenTestData_whenDeleting_thenWorksFine(){
        //given
        Article article = articleRepository.findById(1L).orElseThrow();  //1번 id 가져오기
        long previousCount = articleRepository.count();  //카운트
        long priviousArticleCommentCount = articleCommentRepository.count(); //댓글 카운트
        int deletedCommtentsSize = article.getArticleComments().size();  // 지워진 댓글 카운트용

        //when
        articleRepository.delete(article);   //삭제


        //then
        assertThat(articleRepository.count()).isEqualTo(previousCount-1);
        assertThat(articleCommentRepository.count()).isEqualTo(priviousArticleCommentCount-deletedCommtentsSize);
    }
}
