package com.example.writeBackCachePractice.comment.cache.entity;

import com.example.writeBackCachePractice.comment.entity.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@RedisHash(value = "comment", timeToLive = 900000)
public class CommentCache implements Serializable {

    private Long id;

    private Long postId;

    private Long userId;

    private String content;

    private Long parentId;


    public CommentCache(Comment comment) {
        this.id = comment.getId();
        this.postId = comment.getPostId();
        this.userId = comment.getUserId();
        this.content = comment.getContent();
        this.parentId = comment.getParentId();
    }

    public Comment toEntity() {
        return Comment.builder()
                .id(id)
                .postId(postId)
                .userId(userId)
                .content(content)
                .parentId(parentId)
                .build();
    }
}
