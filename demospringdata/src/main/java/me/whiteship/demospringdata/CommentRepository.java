package me.whiteship.demospringdata;

import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;

public interface CommentRepository extends MyRepository<Comment, Long> {

    @Async
    ListenableFuture<List<Comment>> findByCommentContainsIgnoreCase(String keyword, Pageable pageable);

}
