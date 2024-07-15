package com.example.writeBackCachePractice.comment.common.service;

import com.example.writeBackCachePractice.comment.common.dto.request.CreateCommentRequestDTO;
import com.example.writeBackCachePractice.comment.entity.entity.Comment;
import com.example.writeBackCachePractice.comment.entity.service.CommentEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentEntityService commentEntityService;    
    
    public Long saveComment(CreateCommentRequestDTO createCommentRequestDTO) {
        return commentEntityService.saveComment(createCommentRequestDTO.toEntity());
    }

    public Comment getComment(Long id) {
        return commentEntityService.getComment(id);
    }

    public List<Comment> getAllComment() {
        return commentEntityService.getAllComment();
    }


    public void updateComment(Long id, String content) {
        commentEntityService.updateComment(id, content);
    }

    public void deleteComment(Long id) {
        commentEntityService.deleteComment(id);
    }
}
