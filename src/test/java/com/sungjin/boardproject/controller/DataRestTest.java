package com.sungjin.boardproject.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @WebMvcTest 얘는 슬라이스형태의 유닛테스트라 test가 안됨(data rest의 autoconfiguration 을 안읽음) 그러므로 통합테스트로 진행
@Disabled("Spring Data REST 통합테스트는 불필요하므로 제외시킴")
@DisplayName("Data Rest - API테스트")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest
public class DataRestTest {
    private  final MockMvc mvc;

    public DataRestTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[api] 게시글 리스트 조회")
    @Test
    void  givenNothing_whenRequestArticles_thenReturnArticlesJsonRepository() throws Exception {
        //Given

        //When & then
        mvc.perform(get("/api/articles"))//mockmvcget을 추출
                .andExpect(status().isOk())   //행결과를 검증하는 MockMvcResultMatchers에서 제공하는 ResultMatcher를 지정 (status , header , cookie 등등)
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json"))) //hal이 없기에 valueof로 직접지정
                .andDo(print());

    }

    @DisplayName("[api] 게시글 단건 조회")
    @Test
    void  givenNothing_whenRequestArticle_thenReturnArticleJsonRepository() throws Exception {
        //Given

        //When & then
        mvc.perform(get("/api/articles/1"))//mockmvcget을 추출
                .andExpect(status().isOk())   //행결과를 검증하는 MockMvcResultMatchers에서 제공하는 ResultMatcher를 지정 (status , header , cookie 등등)
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json"))) //hal이 없기에 valueof로 직접지정
                .andDo(print());

    }
    @DisplayName("[api] 게시글 -> 댓글 리스트 조회")
    @Test
    void  givenNothing_whenRequestArticleCommentFromArticle_thenReturnArticleCommentsJsonRepository() throws Exception {
        //Given

        //When & then
        mvc.perform(get("/api/articles/1/articleComments"))//mockmvcget을 추출
                .andExpect(status().isOk())   //행결과를 검증하는 MockMvcResultMatchers에서 제공하는 ResultMatcher를 지정 (status , header , cookie 등등)
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json"))) //hal이 없기에 valueof로 직접지정
                .andDo(print());

    }

    @DisplayName("[api]댓글 리스트 조회")
    @Test
    void  givenNothing_whenRequestArticleComments_thenReturnArticleCommentsJsonRepository() throws Exception {
        //Given

        //When & then
        mvc.perform(get("/api/articleComments"))//mockmvcget을 추출
                .andExpect(status().isOk())   //행결과를 검증하는 MockMvcResultMatchers에서 제공하는 ResultMatcher를 지정 (status , header , cookie 등등)
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json"))) //hal이 없기에 valueof로 직접지정
                .andDo(print());

    }

    @DisplayName("[api]댓글 단건 조회")
    @Test
    void  givenNothing_whenRequestArticleComment_thenReturnArticleCommentJsonRepository() throws Exception {
        //Given

        //When & then
        mvc.perform(get("/api/articleComments/1"))//mockmvcget을 추출
                .andExpect(status().isOk())   //행결과를 검증하는 MockMvcResultMatchers에서 제공하는 ResultMatcher를 지정 (status , header , cookie 등등)
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json"))) //hal이 없기에 valueof로 직접지정
                .andDo(print());

    }
}
