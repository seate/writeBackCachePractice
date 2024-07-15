package com.example.writeBackCachePractice.comment.common.dto.request;

import com.example.writeBackCachePractice.comment.entity.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequestDTO {

    private Long postId;

    private Long userId;

    private String content;

    private Long parentId;

    public Comment toEntity() {
        return Comment.builder()
                .postId(postId)
                .userId(userId)
                .content(content)
                .parentId(parentId)
                .build();
    }

}
