package me.whiteship.demojpa3.post;

import java.util.List;

public interface PostCustomRepository<T> {

    List<Post> findMyPost();

    public void delete(T entity);

}
