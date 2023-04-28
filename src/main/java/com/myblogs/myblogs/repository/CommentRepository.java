package com.myblogs.myblogs.repository;

import com.myblogs.myblogs.entity.Comment;
import com.myblogs.myblogs.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByPostId(long postId);
}
