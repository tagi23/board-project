package com.sungjin.boardproject.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.sungjin.boardproject.domain.Article;
import com.sungjin.boardproject.domain.QArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource //detection-strategy을 위한 지정
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>,  //Article에 필드에 있는 모든 검색 기능을 추가 (기본검색)
        QuerydslBinderCustomizer<QArticle> {

        @Override
        default void customize(QuerydslBindings bindings, QArticle root) {  //검색에 대한 세부적인 구축 구축
                bindings.excludeUnlistedProperties(true);
                bindings.including(root.title,root.content,root.hashtag,root.createAt,root.createBy); //원하는 필드 추가
               // bindings.bind(root.title).first((StringExpression::likeIgnoreCase)); //검색 파라미터는 하나만 받는다 (동일한 값에 한해) like '${value}'
                bindings.bind(root.title).first((StringExpression::containsIgnoreCase));
                bindings.bind(root.content).first((StringExpression::containsIgnoreCase)); //검색 파라미터는 하나만 받는다 (동일한 값에 한해) like '%{value}%'
                bindings.bind(root.hashtag).first((StringExpression::containsIgnoreCase));
                bindings.bind(root.createAt).first((DateTimeExpression::eq)); //DateTimeExpression 날짜
                bindings.bind(root.createBy).first((StringExpression::containsIgnoreCase));
        }
}
