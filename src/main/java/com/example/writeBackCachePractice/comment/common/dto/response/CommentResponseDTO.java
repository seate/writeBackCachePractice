package com.example.writeBackCachePractice.comment.common.dto.response;

import com.example.writeBackCachePractice.comment.entity.entity.Comment;
import lombok.Data;

@Data
public class CommentResponseDTO {

    private Long id;

    private Long postId;

    private Long userId;

    private String content;

    public CommentResponseDTO(Comment comment) {
        this.id = comment.getId();
        this.postId = comment.getPostId();
        this.userId = comment.getUserId();
        this.content = comment.getContent();
    }
}
