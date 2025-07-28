package com.dizzy.demoblogstests.repositories;

import com.dizzy.demoblogstests.entities.Blog;
import com.dizzy.demoblogstests.entities.BlogPost;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BlogPostRepository extends CrudRepository<BlogPost, Long> {
    List<BlogPost> findAllByBlog(Blog blog);
}
