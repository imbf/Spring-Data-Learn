package com.greenfrog.commonweb.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Test
    public void getComment() {
        Post post = new Post();
        post.setTitle("jpa");
        Post savedPost = postRepository.save(post);

        Comment comment = new Comment();
        comment.setComment("Spring data jpa projection");
        comment.setPost(savedPost);
        comment.setPost(savedPost);
        comment.setUp(10);
        comment.setDown(1);
        commentRepository.save(comment);

        commentRepository.findByPost_Id(savedPost.getId(), CommentOnly.class).forEach(commentSummary -> {
            System.out.println("================");
            System.out.println(commentSummary.getComment());
        });
    }

    @Test
    public void qbe() {
        Comment probe = new Comment();
        probe.setBest(true);;

        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withIgnorePaths("up","down");

        Example<Comment> example = Example.of(probe, exampleMatcher);

        commentRepository.findAll(example);

    }


}