package com.sungjin.boardproject.controller;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//        /articles
//        /articles/{article-id}
//        /articles/search
//        /articles/search-hashtag
@RequestMapping("/articles")
@Controller
public class ArticleController {

    @GetMapping
    public String articles(ModelMap map){
        map.addAttribute("articles", List.of());  //테스트용 model 전달
        return "articles/index";    //뷰로 가는 컨트롤러
    }

    @GetMapping("/{articleId}")
    public String article(@PathVariable Long articleId, ModelMap map){
        map.addAttribute("article", "article");  // TODO:구현할 때 여기에 실제 데이터를 넣어야함
        map.addAttribute("articleComments", List.of());  //테스트용 model 전달
        return "articles/detail";    //뷰로 가는 컨트롤러
    }
}

