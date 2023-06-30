package com.sungjin.boardproject.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 게시글")
@WebMvcTest(ArticleController.class) //해당 컨트롤러만
class ArticleControllerTest {
    private  final MockMvc mvc;

    public ArticleControllerTest(@Autowired MockMvc mvc){  //생성자 주입기법
        this.mvc = mvc;
    }


    @DisplayName("[view][GET] 게시글 리스트 (게시판) 페이지 - 정상호출")
    @Test
    public void givenNothing_whenRequestingArticlesView_thenReturnsArticlesView() throws Exception {
        //Given

        //when & then
        mvc.perform(get("/articles"))
                .andExpect(status().isOk())  //200 ok인가?
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))  //컨텐트 타입이 무엇인지 /이그젝트 매칭이 옳지않음 utf8 땜에
                .andExpect(view().name("articles/index")) //뷰 네임에 대한 검사
                .andExpect(model().attributeExists("articles")); //model attribute에 "articles" 이라는 키가 있는지

    }

    @Disabled("구현 중")
    @DisplayName("[view][GET] 게시글 상세 페이지 - 정상호출")
    @Test
    public void givenNothing_whenRequestingArticleView_thenReturnsArticleView() throws Exception {
        //Given

        //when & then
        mvc.perform(get("/articles/1"))
                .andExpect(status().isOk())  //200 ok인가?
                .andExpect(content().contentType(MediaType.TEXT_HTML))  //컨텐트 타입이 무엇인지
                .andExpect(view().name("articles/detail"))
                .andExpect(model().attributeExists("article")) //model attribute에 "articles" 이라는 키가 있는지
                .andExpect(model().attributeExists("articleComments"));  //게시글 페이지는 댓글도 보여야함

    }

    @Disabled("구현 중")
    @DisplayName("[view][GET] 게시글 검색 페이지 - 정상호출")
    @Test
    public void givenNothing_whenRequestingArticleSearchView_thenReturnsArticleSearchView() throws Exception {
        //Given

        //when & then
        mvc.perform(get("/articles/search"))
                .andExpect(status().isOk())  //200 ok인가?
                .andExpect(content().contentType(MediaType.TEXT_HTML))  //컨텐트 타입이 무엇인지
                .andExpect(model().attributeExists("articles/search"));
    }

    @Disabled("구현 중")
    @DisplayName("[view][GET] 게시글 해시태그 검색 페이지 - 정상호출")
    @Test
    public void givenNothing_whenRequestingArticleHashtagView_thenReturnsArticleHashtagView() throws Exception {
        //Given

        //when & then
        mvc.perform(get("/articles/search-Hashtag"))   //경로
                .andExpect(status().isOk())  //200 ok인가?
                .andExpect(content().contentType(MediaType.TEXT_HTML))  //컨텐트 타입이 무엇인지
                .andExpect(model().attributeExists("articles/search-Hashtag"));
    }

}
