package com.sungjin.boardproject.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createAt"),
        @Index(columnList = "createBy")
})
@EntityListeners(AuditingEntityListener.class) //auditing을 사용하려면  @EntityListeners 적용해야함
@Entity
public class Article{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //자동으로 Auto Increment를 걸어주기를 위함
    private Long id;

    @Setter @Column(nullable = false) private String title; //제목  null이 안들어감
    @Setter @Column(nullable = false, length = 10000) private String content; // 본문

    @Setter private String hashtag; //해시태그

    @ToString.Exclude  //ToString 끊기 (댓글 정보는 굳이)
    @OrderBy("id") //정렬
    @OneToMany(mappedBy = "article" , cascade = CascadeType.ALL) //article 테이블로부터 왔다는걸 표시  실무에서는 양방향 바인딩을 일부로 푼 경우가 많음
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();
    //auditing
    @CreatedDate @Column(nullable = false) private LocalDateTime createAt; //생성일  최초의insert할때 넣어줌
    @CreatedBy @Column(nullable = false, length = 100) private String createBy; //생성자
    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt; //수정일     실시간으로 작성 한걸 넣어줌
    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy; //수정자

    //모든 jpa엔티티들은 하이버네이트를 기준으로 기본 생성자를 가지고 있어야함
    protected Article() {}

    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    //Article factory 형식으로 만듦
    public static Article of(String title, String content , String hashtag){  //펙토리 메소드 형식 (디자인타입) new를 안쓰기 위해
        return new Article(title ,content , hashtag);
    }

    //동등성 검사
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return id !=null && id.equals(article.id); //영속성 검사
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
