package com.example.writeBackCachePractice.comment.entity.service;

import com.example.writeBackCachePractice.comment.entity.entity.Comment;
import com.example.writeBackCachePractice.comment.entity.repository.CommentEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentEntityService {

    private final CommentEntityRepository commentEntityRepository;

    public Long saveComment(Comment comment) {
        return commentEntityRepository.save(comment).getId();
    }

    public void saveAllComments(List<Comment> comments) {
        commentEntityRepository.saveAll(comments);
    }

    public Comment getComment(Long id) {
        return commentEntityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
    }

    public List<Comment> getAllComment() {
        return commentEntityRepository.findAll();
    }

    public void updateComment(Long id, String content) {
        Comment comment = getComment(id);
        comment.setContent(content);
        saveComment(comment);
    }

    public void deleteComment(Long id) {
        commentEntityRepository.deleteById(id);
    }
}
