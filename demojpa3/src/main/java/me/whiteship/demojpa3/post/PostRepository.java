package me.whiteship.demojpa3.post;

import me.whiteship.demojpa3.MyRepository;
import me.whiteship.demojpa3.SimpleMyRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

//public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository<Post>{
public interface PostRepository extends MyRepository<Post, Long>, QuerydslPredicateExecutor<Post> {

}
