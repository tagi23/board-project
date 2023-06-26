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
import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createAt"),
        @Index(columnList = "createBy")
})
//@EntityListeners(AuditingEntityListener.class)
@Entity
public class ArticleComment extends AuditingFields{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //자동으로 Auto Increment를 걸어주기를 위함(이벤트가 발생할때 처리하는 메소드)
    private Long id;

    @Setter @ManyToOne(optional = false) private Article article;  //게시글 (ID) 연관관계
    @Setter @Column(nullable = false,length = 500) private String content; // 본문

//    @CreatedDate @Column(nullable = false) private LocalDateTime createAt; //생성일  최초의insert할때 넣어줌
//    @CreatedBy @Column(nullable = false, length = 100) private String createBy; //생성자
//    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt; //수정일     실시간으로 작성 한걸 넣어줌
//    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy; //수정자

    protected ArticleComment() {}

    private ArticleComment(Article article, String content) {
        this.article = article;
        this.content = content;
    }

    public static  ArticleComment of(Article article,String content){
        return new ArticleComment(article,content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment)) return false;
        ArticleComment that = (ArticleComment) o;
        return id!=null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        System.out.printf("test");
        return Objects.hash(id);
    }
}
