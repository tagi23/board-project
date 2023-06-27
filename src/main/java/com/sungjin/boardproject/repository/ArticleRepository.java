package com.sungjin.boardproject.repository;

import com.sungjin.boardproject.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource //detection-strategy을 위한 지정
public interface ArticleRepository extends JpaRepository<Article,Long> {
}
