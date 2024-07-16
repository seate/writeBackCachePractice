package com.example.writeBackCachePractice;

import com.example.writeBackCachePractice.comment.cache.service.CommentCacheService;
import com.example.writeBackCachePractice.comment.common.dto.request.CreateCommentRequestDTO;
import com.example.writeBackCachePractice.comment.common.service.CommentService;
import com.example.writeBackCachePractice.comment.entity.entity.Comment;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final CommentService commentService;

        private final CommentCacheService commentCacheService;


        public void dbInit1() {
            commentCacheService.deleteAll();

            IntStream.range(0, 1000).forEach(i -> {
                CreateCommentRequestDTO createCommentRequestDTO = new CreateCommentRequestDTO(1L, 1L, "this is content!!!", null);
                Comment comment = createCommentRequestDTO.toEntity();
                comment.setId(commentService.saveComment(createCommentRequestDTO));

                commentCacheService.saveComment(comment);
            });
        }
    }
}
