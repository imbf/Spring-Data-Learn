package me.whiteship.demojpa3.post;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostRepositoryTestConfig {

    @Bean // Bean을 등록하는 방
    public PostListener postListener() {
        return new PostListener();
    }
}
