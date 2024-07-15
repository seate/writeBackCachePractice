package com.example.writeBackCachePractice.comment.common.service;

import com.example.writeBackCachePractice.comment.cache.entity.CommentCache;
import com.example.writeBackCachePractice.comment.cache.service.CommentCacheService;
import com.example.writeBackCachePractice.comment.common.dto.request.CreateCommentRequestDTO;
import com.example.writeBackCachePractice.comment.entity.entity.Comment;
import com.example.writeBackCachePractice.comment.entity.service.CommentEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentEntityService commentEntityService;

    private final CommentCacheService commentCacheService;
    
    public Long saveComment(CreateCommentRequestDTO createCommentRequestDTO) {
        Comment comment = createCommentRequestDTO.toEntity();
        if (comment.getParentId() != null) getComment(comment.getParentId());

        comment.setId(commentEntityService.saveComment(comment));

        return commentCacheService.saveComment(comment);
    }

    public Comment getComment(Long id) {
        Optional<CommentCache> commentCache = commentCacheService.getComment(id);
        return (commentCache.isPresent()) ? commentCache.get().toEntity() : commentEntityService.getComment(id);
    }

    public List<Comment> getAllComment() {
        return commentEntityService.getAllComment();
    }


    public void updateComment(Long id, String newContent) {
        try {
            commentCacheService.updateComment(id, newContent);
        } catch (NoSuchElementException e) {
            commentEntityService.updateComment(id, newContent);
            Comment comment = commentEntityService.getComment(id);
            comment.setContent(newContent);

            commentCacheService.saveComment(comment);
        }
    }

    public void deleteComment(Long id) {
        commentEntityService.deleteComment(id);
    }
}
