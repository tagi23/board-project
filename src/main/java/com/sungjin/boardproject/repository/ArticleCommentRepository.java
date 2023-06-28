package com.sungjin.boardproject.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.sungjin.boardproject.domain.Article;
import com.sungjin.boardproject.domain.ArticleComment;
import com.sungjin.boardproject.domain.QArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource  //detection-strategy을 위한 지정
public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment,Long>,
        QuerydslPredicateExecutor<ArticleComment>,
        QuerydslBinderCustomizer<QArticleComment>
{
    @Override
    default void customize(QuerydslBindings bindings, QArticleComment root){
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.content,root.createAt,root.createBy); //원하는 필드 추가
        // bindings.bind(root.title).first((StringExpression::likeIgnoreCase)); //검색 파라미터는 하나만 받는다 (동일한 값에 한해) like '${value}'
        bindings.bind(root.content).first((StringExpression::containsIgnoreCase)); //검색 파라미터는 하나만 받는다 (동일한 값에 한해) like '%{value}%'
        bindings.bind(root.createAt).first((DateTimeExpression::eq)); //DateTimeExpression 날짜
        bindings.bind(root.createBy).first((StringExpression::containsIgnoreCase));
    }
}
