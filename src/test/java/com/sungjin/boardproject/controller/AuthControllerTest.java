package com.sungjin.boardproject.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 인증")
@WebMvcTest
public class AuthControllerTest {
    private  final MockMvc mvc;

    public AuthControllerTest(@Autowired MockMvc mvc){  //생성자 주입기법
        this.mvc = mvc;
    }

    @DisplayName("[view][GET] 로그인 페이지 - 정상호출")
    @Test
    public void givenNothing_whenTryingToLogin_thenReturnsLoginView() throws Exception {
        //Given

        //when & then
        mvc.perform(get("/login"))
                .andExpect(status().isOk())  //200 ok인가?
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));  //컨텐트 타입이 무엇인지 /이그젝트 매칭이 옳지않음 utf8 땜에
    }
}
