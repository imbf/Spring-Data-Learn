package me.whiteship.demojpa3.post;

import org.springframework.context.ApplicationListener;

public class PostListener implements ApplicationListener<PostPublishedEvent> {

    @Override
    public void onApplicationEvent(PostPublishedEvent postPublishedEvent) {
        System.out.println("========================");
        System.out.println(postPublishedEvent.getPost().getTitle() + " is Published");
        System.out.println("========================");
    }
}
