package com.example.writeBackCachePractice.comment.entity.repository;


import com.example.writeBackCachePractice.comment.entity.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentEntityRepository extends JpaRepository<Comment, Long> {
}
