package com.sungjin.boardproject.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@ToString
@EntityListeners(AuditingEntityListener.class) //auditing을 사용하려면  @EntityListeners 적용해야함
@MappedSuperclass
public abstract class AuditingFields {

    //auditing
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) //parsing을 위해서 설정
    @CreatedDate
    @Column(nullable = false, updatable = false)  //nullable = false ddl생성시 notnull 제약 조건이 붙는다. updatable = false 이 필드는 업데이트가 불가하다
    private LocalDateTime createAt; //생성일  최초의insert할때 넣어줌

    @CreatedBy
    @Column(nullable = false, updatable = false ,length = 100)
    private String createBy; //생성자

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt; //수정일     실시간으로 작성 한걸 넣어줌

    @LastModifiedBy
    @Column(nullable = false, length = 100)
    private String modifiedBy; //수정자
}
