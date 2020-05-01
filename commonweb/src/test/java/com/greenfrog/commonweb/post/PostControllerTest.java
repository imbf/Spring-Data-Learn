package com.greenfrog.commonweb.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PostRepository postRepository;

    @Test
    public void getPost() throws Exception {
        Post post = new Post();
        post.setTitle("JPA");
        postRepository.save(post);

        mockMvc.perform(get("/posts/" + post.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("JPA"));

    }

    @Test
    public void getPosts() throws Exception {
        Post post = new Post();
        post.setTitle("JPA");
        postRepository.save(post);

        mockMvc.perform(get("/posts/")
                    .param("page", "3")
                    .param("size", "10")
                    .param("sort", "created,desc")
                    .param("sort", "title"))
                .andDo(print()) // json안의 내용에 대해서 잘 알고 싶으니까 print()를 꼭 사용해 준다.
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title", is("JPA")));
    }

    private void createPosts() {
        int postsCount = 100;
        while(postsCount > 0 ) {
            Post post = new Post();
            post.setTitle("JPA");
            postRepository.save(post);
            postsCount--;
        }
    }
}