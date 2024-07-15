package com.example.writeBackCachePractice.comment.cache.service;

import com.example.writeBackCachePractice.comment.cache.entity.CommentCache;
import com.example.writeBackCachePractice.comment.cache.repository.CommentCacheRepository;
import com.example.writeBackCachePractice.comment.entity.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentCacheService {

    private final CommentCacheRepository commentCacheRepository;

    public Long saveComment(Comment comment) {
        return commentCacheRepository.save(new CommentCache(comment));
    }

    public Optional<CommentCache> getComment(Long id) {
        return commentCacheRepository.findById(id);
    }

    public List<CommentCache> getAllComment() {
        return commentCacheRepository.findAll();
    }

    public void updateComment(Long id, String content) {
        CommentCache commentCache = getComment(id).orElseThrow();
        commentCache.setContent(content);
        deleteComment(id);
        commentCacheRepository.save(commentCache);
    }

    public void deleteComment(Long id) {
        commentCacheRepository.deleteById(id);
    }

    public void deleteAll() {
        commentCacheRepository.deleteAll();
    }
}
