package com.sungjin.boardproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

//JpaAuditing을 활용하여 생성자나 생성일 수정일 수정자와 같은 엔티티들을 자동으로 생성되게 할것
@EnableJpaAuditing
@Configuration
public class JpaConfig {
    @Bean
    public AuditorAware<String> auditorAware(){//JpaAuditing 할때마다 사람의 이름은 아래와 같이 설정한걸로 들어감
        return () -> Optional.of("sj");//TODO: 스프링 시큐리티로 인증 기능을 붙이게 될 때, 수정
    }

}
