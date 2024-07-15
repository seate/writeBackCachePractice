package com.example.writeBackCachePractice.comment.entity.entity;

import com.example.writeBackCachePractice.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
public class Comment extends BaseTimeEntity {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String content;

    @Column
    private Long parentId;

    public Comment(Long id, Long postId, Long userId, String content, Long parentId) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.parentId = parentId;
    }
}

