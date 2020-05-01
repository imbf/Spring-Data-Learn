package me.whiteship.demospringdata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void crud() throws ExecutionException, InterruptedException {
        this.createComment(100, "Spring Data JPA");
        this.createComment(40, "Hibernate Spring");
        this.createComment(33, "SPRING JPA");

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "likeCount"));

        ListenableFuture<List<Comment>> future =
                commentRepository.findByCommentContainsIgnoreCase("Spring", pageRequest);
        System.out.println("=================");
        System.out.println("is done?" + future.isDone());

        future.addCallback(new ListenableFutureCallback<List<Comment>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println(throwable);
            }

            @Override
            public void onSuccess(@Nullable List<Comment> comments) {
                System.out.println("====================");
                comments.forEach(System.out::println);
            }
        });
    }

    private void createComment(int likeCount, String comment) {
        Comment newcomment = new Comment();
        newcomment.setLikeCount(likeCount);
        newcomment.setComment(comment);
        commentRepository.save(newcomment);

    }

}